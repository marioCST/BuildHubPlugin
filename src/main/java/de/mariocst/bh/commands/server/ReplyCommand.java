package de.mariocst.bh.commands.server;

import de.mariocst.bh.BuildHub;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReplyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            try {
                if (args.length >= 1) {
                    try {
                        if (!BuildHub.getInstance().lastMessagedPlayer.containsKey(sender)) {
                            sender.sendMessage(BuildHub.getInstance().getPrefix() + "Du hast keinen Spieler zum Antworten!");
                            return true;
                        }

                        Player t = (Player) BuildHub.getInstance().lastMessagedPlayer.get(sender);

                        if (t != null) {
                            StringBuilder msg = new StringBuilder();
                            for (String arg : args) {
                                msg.append(arg).append(" ");
                            }

                            t.sendMessage(BuildHub.getInstance().getPrefix() + "§6CONSOLE -> Dir §8» §f" + msg.toString().replaceAll("&", "§"));
                            sender.sendMessage(BuildHub.getInstance().getPrefix() + "§6Du -> " + t.getName() + " §8» §f" + msg.toString().replaceAll("&", "§"));
                        }
                        else {
                            sender.sendMessage(BuildHub.getInstance().getPrefix() + "Der Spieler " + BuildHub.getInstance().lastMessagedPlayer.get(sender).getName() + " existiert aus irgendeinem Grund nicht!");
                        }
                    }
                    catch (NullPointerException e) {
                        sender.sendMessage(BuildHub.getInstance().getPrefix() + "Der Spieler " + BuildHub.getInstance().lastMessagedPlayer.get(sender).getName() + " existiert aus irgendeinem Grund nicht!");
                    }
                }
                else {
                    sender.sendMessage(BuildHub.getInstance().getPrefix() + "/r <Nachricht>");
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(BuildHub.getInstance().getPrefix() + "/r <Nachricht>");
            }
            return true;
        }

        if (player.hasPermission("mario.msg") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            try {
                if (args.length >= 2) {
                    try {
                        if (!BuildHub.getInstance().lastMessagedPlayer.containsKey(player)) {
                            player.sendMessage(BuildHub.getInstance().getPrefix() + "Du hast keinen Spieler zum Antworten!");
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                            return true;
                        }

                        if (BuildHub.getInstance().lastMessagedPlayer.get(player) == BuildHub.getInstance().getServer().getConsoleSender()) {
                            CommandSender t = BuildHub.getInstance().getServer().getConsoleSender();

                            StringBuilder msg = new StringBuilder();
                            for (String arg : args) {
                                msg.append(arg).append(" ");
                            }

                            t.sendMessage(BuildHub.getInstance().getPrefix() + "§6" + player.getName() + " -> Dir §8» §f" + msg.toString().replaceAll("&", "§"));
                            player.sendMessage(BuildHub.getInstance().getPrefix() + "§6Du -> CONSOLE §8» §f" + msg.toString().replaceAll("&", "§"));
                        }

                        Player t = (Player) BuildHub.getInstance().lastMessagedPlayer.get(player);

                        if (t != null) {
                            StringBuilder msg = new StringBuilder();
                            for (String arg : args) {
                                msg.append(arg).append(" ");
                            }

                            t.sendMessage(BuildHub.getInstance().getPrefix() + "§6" + player.getName() + " -> Dir §8» §f" + msg.toString().replaceAll("&", "§"));
                            player.sendMessage(BuildHub.getInstance().getPrefix() + "§6Du -> " + t.getName() + " §8» §f" + msg.toString().replaceAll("&", "§"));
                        }
                        else {
                            player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Spieler " + BuildHub.getInstance().lastMessagedPlayer.get(player).getName() + " existiert aus irgendeinem Grund nicht!");
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                        }
                    }
                    catch (NullPointerException e) {
                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Spieler " + BuildHub.getInstance().lastMessagedPlayer.get(player).getName() + " existiert aus irgendeinem Grund nicht!");
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    }
                }
                else {
                    player.sendMessage(BuildHub.getInstance().getPrefix() + "/r <Nachricht>");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage(BuildHub.getInstance().getPrefix() + "/r <Nachricht>");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
            }
        }
        else {
            player.sendMessage(BuildHub.getInstance().getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        }
        return false;
    }
}
