package de.mariocst.bh.commands.server;

import de.mariocst.bh.BuildHub;
import de.mariocst.bh.config.configdata.CoordsData;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CoordsCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(BuildHub.getInstance().getPrefix() + "Bitte führe den Command InGame aus!");
            return true;
        }

        if (player.hasPermission("mario.coords") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            CoordsData data = CoordsData.getCoordsData();

            try {
                if (args.length == 2) {
                    switch (args[0].toLowerCase()) {
                        case "debug" -> {
                            player.sendMessage(data.getCoordsLoc().keySet().toString());
                        }
                        case "get" -> {
                            if (data.getCoordsLoc().containsKey(player.getUniqueId().toString())) {
                                if (data.getCoordsLoc().get(player.getUniqueId().toString()).containsKey(args[1])) {
                                    player.sendMessage(BuildHub.getInstance().getPrefix() + args[1] +
                                            ": X: " + data.getCoordsLoc().get(player.getUniqueId().toString()).get(args[1]).get("x") +
                                            " Y: " + data.getCoordsLoc().get(player.getUniqueId().toString()).get(args[1]).get("y") +
                                            " Z: " + data.getCoordsLoc().get(player.getUniqueId().toString()).get(args[1]).get("z"));
                                }
                                else {
                                    player.sendMessage(BuildHub.getInstance().getPrefix() + "§cUnbekannte Coords: " + args[1]);
                                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);

                                    player.sendMessage(BuildHub.getInstance().getPrefix() + "Gespeicherte Coords:");

                                    for (String names : data.getCoordsLoc().get(player.getUniqueId().toString()).keySet()) {
                                        player.sendMessage(BuildHub.getInstance().getPrefix() + names);
                                    }
                                }
                            }
                            else {
                                player.sendMessage(BuildHub.getInstance().getPrefix() + "Du hast noch keine Coords! Setze dir welche mit /coords set <Name>!");
                                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                            }
                        }
                        case "set" -> {
                            if (data.getCoordsLoc().containsKey(player.getUniqueId().toString())) {
                                if (data.getCoordsLoc().get(player.getUniqueId().toString()).containsKey(args[1])) {
                                    player.sendMessage(BuildHub.getInstance().getPrefix() + "Coords existiert bereits: " + args[1]);
                                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                                }
                                else {
                                    data.addCoords(player, args[1],player.getLocation());

                                    player.sendMessage(BuildHub.getInstance().getPrefix() + "Coords " + args[1] + " bei " + player.getLocation().getX() + " " + player.getLocation().getY() + " " + player.getLocation().getZ() + " gespeichert!");
                                    data.save();
                                }
                            }
                            else {
                                data.addCoords(player, args[1], player.getLocation());

                                player.sendMessage(BuildHub.getInstance().getPrefix() + "Coords " + args[1] + " bei " + player.getLocation().getX() + " " + player.getLocation().getY() + " " + player.getLocation().getZ() + " gespeichert!");
                                data.save();
                            }
                        }
                        case "remove", "del" -> {
                            if (data.getCoordsLoc().containsKey(player.getUniqueId().toString())) {
                                if (data.getCoordsLoc().get(player.getUniqueId().toString()).containsKey(args[1])) {
                                    data.removeCoords(player, args[1]);

                                    player.sendMessage(BuildHub.getInstance().getPrefix() + "Coords " + args[1] + " gelöscht!");
                                    data.save();
                                }
                                else {
                                    player.sendMessage(BuildHub.getInstance().getPrefix() + "Coords existiert nicht: " + args[1]);
                                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                                }
                            }
                            else {
                                player.sendMessage(BuildHub.getInstance().getPrefix() + "Du hast noch keine Coords! Setze dir welche mit /coords set <Name>!");
                                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                            }
                        }
                    }
                }
                else {
                    if (data.getCoordsLoc().containsKey(player.getUniqueId().toString())) {
                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Gespeicherte Coords:");

                        for (String names : data.getCoordsLoc().get(player.getUniqueId().toString()).keySet()) {
                            player.sendMessage(BuildHub.getInstance().getPrefix() + names);
                        }
                    }
                    else {
                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Du besitzt noch keine Coords! Setze dir welche mit /coords set <Name>!");
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                if (data.getCoordsLoc().containsKey(player.getUniqueId().toString())) {
                    player.sendMessage(BuildHub.getInstance().getPrefix() + "Gespeicherte Coords:");

                    for (String names : data.getCoordsLoc().get(player.getUniqueId().toString()).keySet()) {
                        player.sendMessage(BuildHub.getInstance().getPrefix() + names);
                    }
                }
                else {
                    player.sendMessage(BuildHub.getInstance().getPrefix() + "Du besitzt noch keine Coords! Setze dir welche mit /coords set <Name>!");
                }
            }
        }
        else {
            player.sendMessage(BuildHub.getInstance().getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        }
        return false;
    }

    private final String[] MODES = { "get", "set", "remove", "del" };

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        final List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            StringUtil.copyPartialMatches(args[0], Arrays.asList(MODES), completions);
            Collections.sort(completions);
        }
        if (args.length == 2 && CoordsData.getCoordsData().getCoordsLoc().containsKey(((Player) sender).getUniqueId().toString())) {
            StringUtil.copyPartialMatches(args[1], CoordsData.getCoordsData().getCoordsLoc().get(((Player) sender).getUniqueId().toString()).keySet(), completions);
            Collections.sort(completions);
        }
        return completions;
    }
}
