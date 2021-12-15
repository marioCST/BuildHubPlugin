package de.mariocst.bh.commands.player;

import de.mariocst.bh.BuildHub;
import de.mariocst.bh.config.configdata.DeathData;
import de.mariocst.bh.config.configdata.StatusData;
import net.kyori.adventure.text.Component;
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

public class StatusCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(BuildHub.getInstance().getPrefix() + "Bitte führe den Command InGame aus!");
            return true;
        }

        if (player.hasPermission("mario.status") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            try {
                if (args.length == 1) {
                    switch (args[0].toLowerCase()) {
                        case "aufnahme" -> {
                            player.displayName(Component.text("§0[§4Aufnahme§0]").append(Component.text(" ")).append(player.name()));
                            player.playerListName(Component.text("§0[§4Aufnahme§0]").append(Component.text(" ")).append(player.name()));

                            if (DeathData.getDeathData().shouldDisplayDeaths()) {
                                player.setCustomName(player.getName() + "\nTode: §e" + DeathData.getDeathData().getDeaths(player));

                                player.playerListName(player.playerListName()
                                        .append(Component.text(" §e" + DeathData.getDeathData().getDeaths(player))));
                            }

                            player.sendMessage(BuildHub.getInstance().getPrefix() + "Dein Status ist nun auf §a\"Aufnahme\"§f!");
                            StatusData.getStatusData().saveStatus(player, "aufnahme");
                            BuildHub.getInstance().saveConfigs();
                        }
                        case "bauen" -> {
                            player.displayName(Component.text("§0[§1Bauen§0]").append(Component.text(" ")).append(player.name()));
                            player.playerListName(Component.text("§0[§1Bauen§0]").append(Component.text(" ")).append(player.name()));

                            if (DeathData.getDeathData().shouldDisplayDeaths()) {
                                player.setCustomName(player.getName() + "\nTode: §e" + DeathData.getDeathData().getDeaths(player));

                                player.playerListName(player.playerListName()
                                        .append(Component.text(" §e" + DeathData.getDeathData().getDeaths(player))));
                            }

                            player.sendMessage(BuildHub.getInstance().getPrefix() + "Dein Status ist nun auf §a\"Bauen\"§f!");
                            StatusData.getStatusData().saveStatus(player, "bauen");
                            BuildHub.getInstance().saveConfigs();
                        }
                        case "hilfe" -> {
                            player.displayName(Component.text("§0[§eHilfe§0]").append(Component.text(" ")).append(player.name()));
                            player.playerListName(Component.text("§0[§eHilfe§0]").append(Component.text(" ")).append(player.name()));

                            if (DeathData.getDeathData().shouldDisplayDeaths()) {
                                player.setCustomName(player.getName() + "\nTode: §e" + DeathData.getDeathData().getDeaths(player));

                                player.playerListName(player.playerListName()
                                        .append(Component.text(" §e" + DeathData.getDeathData().getDeaths(player))));
                            }

                            player.sendMessage(BuildHub.getInstance().getPrefix() + "Dein Status ist nun auf §a\"Hilfe\"§f!");
                            StatusData.getStatusData().saveStatus(player, "hilfe");
                            BuildHub.getInstance().saveConfigs();
                        }
                        case "live" -> {
                            player.displayName(Component.text("§0[§cLive§0]").append(Component.text(" ")).append(player.name()));
                            player.playerListName(Component.text("§0[§cLive§0]").append(Component.text(" ")).append(player.name()));

                            if (DeathData.getDeathData().shouldDisplayDeaths()) {
                                player.setCustomName(player.getName() + "\nTode: §e" + DeathData.getDeathData().getDeaths(player));

                                player.playerListName(player.playerListName()
                                        .append(Component.text(" §e" + DeathData.getDeathData().getDeaths(player))));
                            }

                            player.sendMessage(BuildHub.getInstance().getPrefix() + "Dein Status ist nun auf §a\"Live\"§f!");
                            StatusData.getStatusData().saveStatus(player, "live");
                            BuildHub.getInstance().saveConfigs();
                        }
                        case "redstone" -> {
                            player.displayName(Component.text("§0[§4Redstone§0]").append(Component.text(" ")).append(player.name()));
                            player.playerListName(Component.text("§0[§4Redstone§0]").append(Component.text(" ")).append(player.name()));

                            if (DeathData.getDeathData().shouldDisplayDeaths()) {
                                player.setCustomName(player.getName() + "\nTode: §e" + DeathData.getDeathData().getDeaths(player));

                                player.playerListName(player.playerListName()
                                        .append(Component.text(" §e" + DeathData.getDeathData().getDeaths(player))));
                            }

                            player.sendMessage(BuildHub.getInstance().getPrefix() + "Dein Status ist nun auf §a\"Redstone\"§f!");
                            StatusData.getStatusData().saveStatus(player, "redstone");
                            BuildHub.getInstance().saveConfigs();
                        }
                        case "verzweifelt" -> {
                            player.displayName(Component.text("§0[§6Verzweifelt§0]").append(Component.text(" ")).append(player.name()));
                            player.playerListName(Component.text("§0[§6Verzweifelt§0]").append(Component.text(" ")).append(player.name()));

                            if (DeathData.getDeathData().shouldDisplayDeaths()) {
                                player.setCustomName(player.getName() + "\nTode: §e" + DeathData.getDeathData().getDeaths(player));

                                player.playerListName(player.playerListName()
                                        .append(Component.text(" §e" + DeathData.getDeathData().getDeaths(player))));
                            }

                            player.sendMessage(BuildHub.getInstance().getPrefix() + "Dein Status ist nun auf §a\"Verzweifelt\"§f!");
                            StatusData.getStatusData().saveStatus(player, "verzweifelt");
                            BuildHub.getInstance().saveConfigs();
                        }
                        case "reset" -> {
                            player.displayName(player.name());
                            player.playerListName(player.name());

                            if (DeathData.getDeathData().shouldDisplayDeaths()) {
                                player.setCustomName(player.getName() + "\nTode: §e" + DeathData.getDeathData().getDeaths(player));

                                player.playerListName(player.playerListName()
                                        .append(Component.text(" §e" + DeathData.getDeathData().getDeaths(player))));
                            }

                            player.sendMessage(BuildHub.getInstance().getPrefix() + "Dein Status ist nun zurückgesetzt worden!");
                            StatusData.getStatusData().resetStatus(player);
                            BuildHub.getInstance().saveConfigs();
                        }
                        default -> {
                            player.sendMessage(BuildHub.getInstance().getPrefix() + "/status <Status>");
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
                        }
                    }
                }
                else {
                    player.sendMessage(BuildHub.getInstance().getPrefix() + "/status <Status>");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage(BuildHub.getInstance().getPrefix() + "/status <Status>");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
            }
        }
        else {
            player.sendMessage(BuildHub.getInstance().getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
        }
        return false;
    }

    private final String[] STATUS = { "aufnahme", "bauen", "hilfe", "live", "redstone", "verzweifelt", "reset" };

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        final List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            StringUtil.copyPartialMatches(args[0], Arrays.asList(STATUS), completions);
            Collections.sort(completions);
        }
        else if (args.length == 2) {
            final List<String> names = new ArrayList<>();

            for (Player player : BuildHub.getInstance().getServer().getOnlinePlayers()) {
                names.add(player.getName());
            }

            StringUtil.copyPartialMatches(args[1], names, completions);
            Collections.sort(completions);
        }
        return completions;
    }
}
