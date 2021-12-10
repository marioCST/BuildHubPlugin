package de.mariocst.bh.commands.setter;

import de.mariocst.bh.BuildHub;
import de.mariocst.bh.config.configdata.DiscordLink;
import de.mariocst.bh.config.configdata.WebLink;
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

public class SetLinkCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        StringBuilder msg = new StringBuilder();

        if (!(sender instanceof Player player)) {
            if (args.length >= 2) {
                switch (args[0].toLowerCase()) {
                    case "discord" -> {
                        for (int i = 1; i < args.length; i++) {
                            msg.append(args[i]).append(" ");
                        }

                        DiscordLink.getDiscordLink().setLink(msg.toString());
                        sender.sendMessage(BuildHub.getInstance().getPrefix() + "Der Discord Link ist nun: §a" + msg);
                        BuildHub.getInstance().saveConfigs();
                    }
                    case "web" -> {
                        for (int i = 1; i < args.length; i++) {
                            msg.append(args[i]).append(" ");
                        }

                        WebLink.getWebLink().setLink(msg.toString());
                        sender.sendMessage(BuildHub.getInstance().getPrefix() + "Der Web Link ist nun: §a" + msg);
                        BuildHub.getInstance().saveConfigs();
                    }
                    default -> sender.sendMessage("§cUsage: §e/setlink <discord|web> <Link>");
                }
            }
            else {
                BuildHub.getInstance().log("§cUsage: §e/setlink <discord|web> <Link>");
            }
            return false;
        }

        if (player.hasPermission("mario.setlink") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            if (args.length >= 2) {
                switch (args[0].toLowerCase()) {
                    case "discord" -> {
                        for (int i = 1; i < args.length; i++) {
                            msg.append(args[i]).append(" ");
                        }

                        DiscordLink.getDiscordLink().setLink(msg.toString());
                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Discord Link ist nun: §a" + msg);
                        BuildHub.getInstance().saveConfigs();
                    }
                    case "web" -> {
                        for (int i = 1; i < args.length; i++) {
                            msg.append(args[i]).append(" ");
                        }

                        WebLink.getWebLink().setLink(msg.toString());
                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Web Link ist nun: §a" + msg);
                        BuildHub.getInstance().saveConfigs();
                    }
                    default -> player.sendMessage("§cUsage: §e/setlink <discord|web> <Link>");
                }
            }
            else {
                player.sendMessage("§cUsage: §e/setlink <discord|web> <Link>");
            }
        }
        else {
            player.sendMessage(BuildHub.getInstance().getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        }
        return false;
    }

    private final String[] MODES = { "discord", "web" };

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        final List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            StringUtil.copyPartialMatches(args[0], Arrays.asList(MODES), completions);
            Collections.sort(completions);
        }
        return completions;
    }
}
