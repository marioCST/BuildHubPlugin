package de.mariocst.bh.commands.player;

import de.mariocst.bh.BuildHub;
import de.mariocst.bh.config.configdata.DeathData;
import de.mariocst.bh.scoreboard.DeathScoreboard;
import net.kyori.adventure.text.Component;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ScoreboardCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(BuildHub.getInstance().getPrefix() + "Bitte führe den Command InGame aus!");
            return true;
        }

        if (player.hasPermission("mario.scoreboard") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            try {
                if (args.length == 1) {
                    if (!DeathData.getDeathData().shouldDisplayScoreboard()) {
                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Das Death Scoreboard ist deaktiviert.");
                        return true;
                    }

                    switch (args[0].toLowerCase()) {
                        case "on" -> {
                            Scoreboard scoreboard = BuildHub.getInstance().getServer().getScoreboardManager().getNewScoreboard();
                            if (DeathData.getDeathData().shouldDisplayScoreboard()) player.setScoreboard(scoreboard);
                            new DeathScoreboard(Component.text("§cTode"), scoreboard);

                            player.sendMessage(BuildHub.getInstance().getPrefix() + "Das Death Scoreboard ist nun aktiviert!");
                        }
                        case "off" -> {
                            player.setScoreboard(BuildHub.getInstance().getServer().getScoreboardManager().getNewScoreboard());

                            player.sendMessage(BuildHub.getInstance().getPrefix() + "Das Death Scoreboard ist nun deaktiviert!");
                        }
                        default -> {
                            player.sendMessage(BuildHub.getInstance().getPrefix() + "/scoreboard <on|off>");
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
                        }
                    }
                }
                else {
                    player.sendMessage(BuildHub.getInstance().getPrefix() + "/scoreboard <on|off>");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage(BuildHub.getInstance().getPrefix() + "/scoreboard <on|off>");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
            }
        }
        else {
            player.sendMessage(BuildHub.getInstance().getPrefix() + "§cKeine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
        }
        return false;
    }

    private final String[] MODES = { "on", "off" };

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
