package de.mariocst.bh.commands.player;

import de.mariocst.bh.BuildHub;
import de.mariocst.bh.config.configdata.DeathData;
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

public class DeathCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            try {
                if (args.length == 2) {
                    Player t = BuildHub.getInstance().getServer().getPlayer(args[1]);
                    OfflinePlayer oT = BuildHub.getInstance().getServer().getOfflinePlayerIfCached(args[1]);

                    if (t != null) {
                        switch (args[0].toLowerCase()) {
                            case "get" -> sender.sendMessage(BuildHub.getInstance().getPrefix() + "Tode von " + args[1] + ": " + DeathData.getDeathData().getDeaths(t));
                            case "reset" -> {
                                DeathData.getDeathData().resetDeaths(t);
                                sender.sendMessage(BuildHub.getInstance().getPrefix() + "Tode von " + args[1] + " zurückgesetzt!");
                                BuildHub.getInstance().saveConfigs();
                            }
                            default -> sender.sendMessage(BuildHub.getInstance().getPrefix() + "/deaths <get|reset> <Spieler>");
                        }
                    }
                    else if (oT != null) {
                        switch (args[0].toLowerCase()) {
                            case "get" -> sender.sendMessage(BuildHub.getInstance().getPrefix() + "Tode von " + args[1] + ": " + DeathData.getDeathData().getDeaths(oT));
                            case "reset" -> {
                                DeathData.getDeathData().resetDeaths(oT);
                                sender.sendMessage(BuildHub.getInstance().getPrefix() + "Tode von " + args[1] + " zurückgesetzt!");
                                BuildHub.getInstance().saveConfigs();
                            }
                            default -> sender.sendMessage(BuildHub.getInstance().getPrefix() + "/deaths <get|reset> <Spieler>");
                        }
                    }
                    else {
                        sender.sendMessage(BuildHub.getInstance().getPrefix() + "Der Spieler " + args[1] + " existiert nicht!");
                    }
                }
                else {
                    sender.sendMessage(BuildHub.getInstance().getPrefix() + "/deaths <get|reset> <Spieler>");
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(BuildHub.getInstance().getPrefix() + "/deaths <get|reset> <Spieler>");
            }
            return true;
        }

        if (player.hasPermission("mario.deaths") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            try {
                if (args.length == 2) {
                    Player t = BuildHub.getInstance().getServer().getPlayer(args[1]);
                    OfflinePlayer oT = BuildHub.getInstance().getServer().getOfflinePlayerIfCached(args[1]);

                    if (t != null) {
                        switch (args[0].toLowerCase()) {
                            case "get" -> player.sendMessage(BuildHub.getInstance().getPrefix() + "Tode von " + args[1] + ": " + DeathData.getDeathData().getDeaths(t));
                            case "reset" -> {
                                DeathData.getDeathData().resetDeaths(t);
                                player.sendMessage(BuildHub.getInstance().getPrefix() + "Tode von " + args[1] + " zurückgesetzt!");
                                BuildHub.getInstance().saveConfigs();
                            }
                            default -> player.sendMessage(BuildHub.getInstance().getPrefix() + "/deaths <get|reset> <Spieler>");
                        }
                    }
                    else if (oT != null) {
                        switch (args[0].toLowerCase()) {
                            case "get" -> player.sendMessage(BuildHub.getInstance().getPrefix() + "Tode von " + args[1] + ": " + DeathData.getDeathData().getDeaths(oT));
                            case "reset" -> {
                                DeathData.getDeathData().resetDeaths(oT);
                                player.sendMessage(BuildHub.getInstance().getPrefix() + "Tode von " + args[1] + " zurückgesetzt!");
                                BuildHub.getInstance().saveConfigs();
                            }
                            default -> player.sendMessage(BuildHub.getInstance().getPrefix() + "/deaths <get|reset> <Spieler>");
                        }
                    }
                    else {
                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Spieler " + args[1] + " existiert nicht!");
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
                    }
                }
                else {
                    player.sendMessage(BuildHub.getInstance().getPrefix() + "/deaths <get|reset> <Spieler>");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage(BuildHub.getInstance().getPrefix() + "/deaths <get|reset> <Spieler>");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
            }
        }
        else {
            player.sendMessage(BuildHub.getInstance().getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
        }
        return false;
    }

    private final String[] MODES = { "get", "reset" };

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
