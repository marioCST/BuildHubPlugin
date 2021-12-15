package de.mariocst.bh.commands.server;

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

public class MessageCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            try {
                if (args.length >= 2) {
                    try {
                        Player t = BuildHub.getInstance().getServer().getPlayer(args[0]);

                        if (t != null) {
                            StringBuilder msg = new StringBuilder();
                            for (int i = 1; i < args.length; i++) {
                                msg.append(args[i]).append(" ");
                            }

                            t.sendMessage(BuildHub.getInstance().getPrefix() + "§6CONSOLE -> Dir §8» §f" + msg.toString().replaceAll("&", "§"));
                            sender.sendMessage(BuildHub.getInstance().getPrefix() + "§6Du -> " + t.getName() + " §8» §f" + msg.toString().replaceAll("&", "§"));

                            BuildHub.getInstance().lastMessagedPlayer.remove(sender);
                            BuildHub.getInstance().lastMessagedPlayer.put(sender, t);
                        }
                        else {
                            sender.sendMessage(BuildHub.getInstance().getPrefix() + "Der Spieler " + args[0] + " existiert nicht!");
                        }
                    }
                    catch (NullPointerException e) {
                        sender.sendMessage(BuildHub.getInstance().getPrefix() + "Der Spieler " + args[0] + " existiert nicht!");
                    }
                }
                else {
                    sender.sendMessage(BuildHub.getInstance().getPrefix() + "/msg <Spieler> <Nachricht>");
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(BuildHub.getInstance().getPrefix() + "/msg <Spieler> <Nachricht>");
            }
            return true;
        }

        if (player.hasPermission("mario.msg") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            try {
                if (args.length >= 2) {
                    try {
                        if (args[0].equalsIgnoreCase("console")) {
                            StringBuilder msg = new StringBuilder();
                            for (int i = 1; i < args.length; i++) {
                                msg.append(args[i]).append(" ");
                            }

                            CommandSender t = BuildHub.getInstance().getServer().getConsoleSender();

                            t.sendMessage(BuildHub.getInstance().getPrefix() + "§6" + player.getName() + " -> Dir §8» §f" + msg.toString().replaceAll("&", "§"));
                            player.sendMessage(BuildHub.getInstance().getPrefix() + "§6Du -> CONSOLE §8» §f" + msg.toString().replaceAll("&", "§"));

                            BuildHub.getInstance().lastMessagedPlayer.remove(player);
                            BuildHub.getInstance().lastMessagedPlayer.put(player, t);
                            return true;
                        }

                        Player t = BuildHub.getInstance().getServer().getPlayer(args[0]);

                        if (t != null) {
                            StringBuilder msg = new StringBuilder();
                            for (int i = 1; i < args.length; i++) {
                                msg.append(args[i]).append(" ");
                            }

                            t.sendMessage(BuildHub.getInstance().getPrefix() + "§6" + player.getName() + " -> Dir §8» §f" + msg.toString().replaceAll("&", "§"));
                            player.sendMessage(BuildHub.getInstance().getPrefix() + "§6Du -> " + t.getName() + " §8» §f" + msg.toString().replaceAll("&", "§"));

                            BuildHub.getInstance().lastMessagedPlayer.remove(player);
                            BuildHub.getInstance().lastMessagedPlayer.put(player, t);
                        }
                        else {
                            player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Spieler " + args[0] + " existiert nicht!");
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                        }
                    }
                    catch (NullPointerException e) {
                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Spieler " + args[0] + " existiert nicht!");
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    }
                }
                else {
                    player.sendMessage(BuildHub.getInstance().getPrefix() + "/msg <Spieler> <Nachricht>");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage(BuildHub.getInstance().getPrefix() + "/msg <Spieler> <Nachricht>");
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
