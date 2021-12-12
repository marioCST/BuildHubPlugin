package de.mariocst.bh.commands.player;

import de.mariocst.bh.BuildHub;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudoCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            try {
                if (args.length >= 2) {
                    Player t = BuildHub.getInstance().getServer().getPlayer(args[0]);

                    if (t == null) {
                        sender.sendMessage(BuildHub.getInstance().getPrefix() + "Der Spieler " + args[0] + " existiert nicht!");
                        return true;
                    }

                    StringBuilder msg = new StringBuilder();
                    for (int i = 2; i < args.length; i++) {
                        msg.append(args[i]).append(" ");
                    }

                    if (args[1].toLowerCase().startsWith("c:")) {
                        t.chat(msg.toString().replaceAll("c:", ""));
                        return true;
                    }

                    try {
                        sender.sendMessage(BuildHub.getInstance().getPrefix() + "Der Spieler " + t.getName() + " hat den Befehl " + msg + " ausgeführt!");
                        t.chat("/" + msg);
                    }
                    catch (final Exception e) {
                        sender.sendMessage(BuildHub.getInstance().getPrefix() + "Command nicht gefunden: " + msg);
                    }
                }
                else {
                    sender.sendMessage(BuildHub.getInstance().getPrefix() + "/sudo <Spieler> <Befehl>");
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(BuildHub.getInstance().getPrefix() + "/sudo <Spieler> <Befehl>");
            }
            return true;
        }

        if (player.hasPermission("mario.sudo") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            try {
                if (args.length >= 2) {
                    Player t = BuildHub.getInstance().getServer().getPlayer(args[0]);

                    if (t == null) {
                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Spieler " + args[0] + " existiert nicht!");
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
                        return true;
                    }

                    if (t == player) return true;

                    if ((t.hasPermission("mario.sudo.bypass") || t.hasPermission("mario.*") || t.hasPermission("*") || t.isOp()) && !player.getName().equals("marioCST")) {
                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Du kannst mit dem Spieler " + t.getName() + " nichts ausführen!");
                        return true;
                    }

                    StringBuilder msg = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        msg.append(args[i]).append(" ");
                    }

                    if (args[1].toLowerCase().startsWith("c:")) {
                        t.chat(msg.toString().replaceAll("c:", ""));
                        return true;
                    }

                    try {
                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Spieler " + t.getName() + " hat den Befehl " + msg + " ausgeführt!");
                        t.chat("/" + msg);
                    }
                    catch (final Exception e) {
                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Command nicht gefunden: " + msg);
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
                    }
                }
                else {
                    player.sendMessage(BuildHub.getInstance().getPrefix() + "/sudo <Spieler> <Befehl>");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage(BuildHub.getInstance().getPrefix() + "/sudo <Spieler> <Befehl>");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
            }
        }
        else {
            player.sendMessage(BuildHub.getInstance().getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
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

            StringUtil.copyPartialMatches(args[0], names, completions);
            Collections.sort(completions);
        }
        return completions;
    }
}
