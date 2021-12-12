package de.mariocst.bh.commands.server;

import de.mariocst.bh.BuildHub;
import de.mariocst.bh.config.configdata.BaseData;
import de.mariocst.bh.exceptions.BaseNotFoundException;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BaseCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(BuildHub.getInstance().getPrefix() + "Bitte führe den Command InGame aus!");
            return true;
        }

        if (player.hasPermission("mario.base") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            try {
                if (args.length == 1) {
                    switch (args[0].toLowerCase()) {
                        case "set" -> {
                            BaseData.getBaseData().setBase(player);
                            player.sendMessage(BuildHub.getInstance().getPrefix() + "Du hast erfolgreich deine Base gesetzt!" +
                                    " World: " + player.getWorld().getName() +
                                    " X: " + player.getLocation().getX() +
                                    " Y: " + player.getLocation().getY() +
                                    " Z: " + player.getLocation().getZ() +
                                    " Yaw: " + player.getLocation().getYaw() +
                                    " Pitch: " + player.getLocation().getPitch());
                            BuildHub.getInstance().saveConfigs();
                        }
                        case "tp" -> {
                            try {
                                player.teleport(BaseData.getBaseData().getBase(player));
                                player.sendMessage(BuildHub.getInstance().getPrefix() + "Du wurdest zu deiner Base teleportiert!");
                            }
                            catch (BaseNotFoundException e) {
                                player.sendMessage(BuildHub.getInstance().getPrefix() + "Du hast noch keine Base gesetzt!");
                                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                            }
                        }
                        default -> {
                            player.sendMessage(BuildHub.getInstance().getPrefix() + "/base <set|tp>");
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                        }
                    }
                }
                else if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("tp")) {
                        Player t = BuildHub.getInstance().getServer().getPlayer(args[1]);
                        OfflinePlayer oT = BuildHub.getInstance().getServer().getOfflinePlayerIfCached(args[1]);

                        if (t != null) {
                            try {
                                player.teleport(BaseData.getBaseData().getBase(t));
                                player.sendMessage(BuildHub.getInstance().getPrefix() + "Du wurdest zu der Base von " + t.getName() + " teleportiert!");
                            }
                            catch (BaseNotFoundException e) {
                                player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Spieler " + t.getName() + " hat sich noch keine Base gesetzt!");
                                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                            }
                        }
                        else if (oT != null) {
                            try {
                                player.teleport(BaseData.getBaseData().getBase(oT));
                                player.sendMessage(BuildHub.getInstance().getPrefix() + "Du wurdest zu der Base von " + oT.getName() + " teleportiert!");
                            }
                            catch (BaseNotFoundException e) {
                                player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Spieler " + oT.getName() + " hat sich noch keine Base gesetzt!");
                                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                            }
                        }
                        else {
                            player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Spieler " + args[1] + " existiert nicht!");
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                        }
                    }
                    else {
                        player.sendMessage(BuildHub.getInstance().getPrefix() + "/base <tp> <Spieler>");
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    }
                }
                else {
                    player.sendMessage(BuildHub.getInstance().getPrefix() + "/base <set|tp> [Spieler]");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage(BuildHub.getInstance().getPrefix() + "/base <set|tp> [Spieler]");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
            }
        }
        else {
            player.sendMessage(BuildHub.getInstance().getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        }
        return false;
    }

    private final String[] MODES = { "set", "tp" };

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        final List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            StringUtil.copyPartialMatches(args[0], Arrays.asList(MODES), completions);
            Collections.sort(completions);
        }
        else if (args.length == 2) {
            final List<String> names = new ArrayList<>();

            for (Player player : BuildHub.getInstance().getServer().getOnlinePlayers()) {
                names.add(player.getName());
            }

            for (OfflinePlayer offlinePlayer : BuildHub.getInstance().getServer().getOfflinePlayers()) {
                names.add(offlinePlayer.getName());
            }

            StringUtil.copyPartialMatches(args[1], names, completions);
            Collections.sort(completions);
        }
        return completions;
    }
}
