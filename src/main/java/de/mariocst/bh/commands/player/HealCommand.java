package de.mariocst.bh.commands.player;

import de.mariocst.bh.BuildHub;
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

public class HealCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player player)) {
            try {
                if (args.length == 1) {
                    Player t = BuildHub.getInstance().getServer().getPlayer(args[0]);

                    try {
                        if (t != null) {
                            t.setHealth(20.0);
                            t.sendMessage(BuildHub.getInstance().getPrefix() + "Du wurdest geheilt!");
                            sender.sendMessage(BuildHub.getInstance().getPrefix() + "Der Spieler " + t.getName() + " wurde geheilt!");
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
                    sender.sendMessage(BuildHub.getInstance().getPrefix() + "/heal <Spieler>");
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(BuildHub.getInstance().getPrefix() + "/heal <Spieler>");
            }
            return true;
        }

        if (player.hasPermission("mario.heal") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            try {
                if (args.length == 0) {
                    player.setHealth(20.0);
                    player.sendMessage(BuildHub.getInstance().getPrefix() + "Du wurdest geheilt!");
                }
                else if (args.length == 1) {
                    if (!player.hasPermission("mario.heal.other") && !player.hasPermission("mario.*") && !player.hasPermission("*") && !player.isOp()) {
                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Keine Rechte!");
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                        return true;
                    }

                    Player t = BuildHub.getInstance().getServer().getPlayer(args[0]);

                    try {
                        if (t != null) {
                            t.setHealth(20.0);
                            t.sendMessage(BuildHub.getInstance().getPrefix() + "Du wurdest geheilt!");
                            player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Spieler " + t.getName() + " wurde geheilt!");
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
                    player.sendMessage(BuildHub.getInstance().getPrefix() + "/heal [Spieler]");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage(BuildHub.getInstance().getPrefix() + "/heal [Spieler]");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
            }
        } else {
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

            StringUtil.copyPartialMatches(args[0], names, completions);
            Collections.sort(completions);
        }
        return completions;
    }
}
