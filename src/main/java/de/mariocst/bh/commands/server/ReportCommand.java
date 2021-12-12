package de.mariocst.bh.commands.server;

import de.mariocst.bh.BuildHub;
import de.mariocst.bh.config.configdata.DiscordConfigData;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReportCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            BuildHub.getInstance().log("Du brauchst doch niemanden zu reporten. Ban ihn einfach!");
            return true;
        }

        if (player.hasPermission("mario.report") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            try {
                if (args.length >= 2) {
                    try {
                        Player t = player.getServer().getPlayer(args[0]);
                        OfflinePlayer oT = BuildHub.getInstance().getServer().getOfflinePlayerIfCached(args[0]);

                        if (t != null) {
                            StringBuilder msg = new StringBuilder();
                            for (int i = 1; i < args.length; i++) {
                                msg.append(args[i]).append(" ");
                            }

                            int staffOnline = 0;

                            for (Player staff : BuildHub.getInstance().getServer().getOnlinePlayers()) {
                                if (staff.hasPermission("mario.staff") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                                    staffOnline++;

                                    staff.sendMessage(BuildHub.getInstance().getPrefix() + "Der Spieler " + player.getName() + " hat §a" + t.getName() + " §ffür §a" + msg + "§freportet!");
                                }
                            }

                            player.sendMessage(BuildHub.getInstance().getPrefix() + "Du hast den Spieler §a" + t.getName() + " §ffür §a" + msg + "§ferfolgreich reportet!");

                            if (staffOnline == 0)  player.sendMessage(BuildHub.getInstance().getPrefix() + "§cEs ist kein Teammitglied Online!");

                            if (!DiscordConfigData.getDiscordConfigData().getUrl().equals("")) {
                                try {
                                    BuildHub.getInstance().sendReport(player, t, msg.toString());
                                    player.sendMessage(BuildHub.getInstance().getPrefix() + "Dein Report wurde erfolgreich an das Discord Team gesendet!");
                                }
                                catch (IOException e) {
                                    player.sendMessage(BuildHub.getInstance().getPrefix() + "Dein Report konnte leider nicht an das Discord Team gesendet werden, weil ein Fehler aufgetreten ist.");
                                }
                            }
                        }
                        else if (oT != null) {
                            StringBuilder msg = new StringBuilder();
                            for (int i = 1; i < args.length; i++) {
                                msg.append(args[i]).append(" ");
                            }

                            int staffOnline = 0;

                            for (Player staff : BuildHub.getInstance().getServer().getOnlinePlayers()) {
                                if (staff.hasPermission("mario.staff") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
                                    staffOnline++;

                                    staff.sendMessage(BuildHub.getInstance().getPrefix() + "Der Spieler " + player.getName() + " hat §a" + oT.getName() + " §ffür §a" + msg + "§freportet!");
                                }
                            }

                            player.sendMessage(BuildHub.getInstance().getPrefix() + "Du hast den Spieler §a" + oT.getName() + " §ffür §a" + msg + "§ferfolgreich reportet!");

                            if (staffOnline == 0)  player.sendMessage(BuildHub.getInstance().getPrefix() + "§cEs ist kein Teammitglied Online!");

                            if (!DiscordConfigData.getDiscordConfigData().getUrl().equals("")) {
                                try {
                                    BuildHub.getInstance().sendReport(player, oT, msg.toString());
                                    player.sendMessage(BuildHub.getInstance().getPrefix() + "Dein Report wurde erfolgreich an das Discord Team gesendet!");
                                }
                                catch (IOException e) {
                                    player.sendMessage(BuildHub.getInstance().getPrefix() + "Dein Report konnte leider nicht an das Discord Team gesendet werden, weil ein Fehler aufgetreten ist.");
                                }
                            }
                        }
                        else {
                            player.sendMessage("Der Spieler " + args[0] + " existiert nicht!");
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                        }
                    }
                    catch (NullPointerException e) {
                        
                        player.sendMessage("Der Spieler " + args[0] + " existiert nicht!");
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    }
                }
                else {
                    player.sendMessage(BuildHub.getInstance().getPrefix() + "/report <Spieler> <Message>");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage(BuildHub.getInstance().getPrefix() + "/report <Spieler> <Message>");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
            }
        }
        else {
            player.sendMessage(BuildHub.getInstance().getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        final List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            final List<String> names = new ArrayList<>();

            for (Player player : BuildHub.getInstance().getServer().getOnlinePlayers()) {
                names.add(player.getName());
            }

            for (OfflinePlayer offlinePlayer : BuildHub.getInstance().getServer().getOfflinePlayers()) {
                names.add(offlinePlayer.getName());
            }

            StringUtil.copyPartialMatches(args[0], names, completions);
            Collections.sort(completions);
        }
        return completions;
    }
}
