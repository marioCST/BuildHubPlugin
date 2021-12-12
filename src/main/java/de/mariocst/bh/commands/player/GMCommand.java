package de.mariocst.bh.commands.player;

import de.mariocst.bh.BuildHub;
import org.bukkit.GameMode;
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

public class GMCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            String msg = String.join(" ", args);

            boolean b = msg.equals("0") || msg.equalsIgnoreCase("survival") || msg.equalsIgnoreCase("su");
            boolean b1 = msg.equals("1") || msg.equalsIgnoreCase("creative") || msg.equalsIgnoreCase("c");
            boolean b2 = msg.equals("2") || msg.equalsIgnoreCase("adventure") || msg.equalsIgnoreCase("a");
            boolean b3 = msg.equals("3") || msg.equalsIgnoreCase("spectator") || msg.equalsIgnoreCase("sp");

            try {
                if (args.length == 2) {
                    Player t = BuildHub.getInstance().getServer().getPlayer(args[1]);

                    try {
                        if (t != null) {
                            if (b) {
                                t.setGameMode(GameMode.SURVIVAL);
                                t.sendMessage(BuildHub.getInstance().getPrefix() + "Dein Gamemode wurde auf Survival gestellt!");
                                sender.sendMessage(BuildHub.getInstance().getPrefix() + "Der Gamemode von " + t.getName() + " ist auf Survival gesetzt worden!");
                            }
                            else if (b1) {
                                t.setGameMode(GameMode.CREATIVE);
                                t.sendMessage(BuildHub.getInstance().getPrefix() + "Dein Gamemode wurde auf Creative gestellt!");
                                sender.sendMessage(BuildHub.getInstance().getPrefix() + "Der Gamemode von " + t.getName() + " ist auf Creative gesetzt worden!");
                            }
                            else if (b2) {
                                t.setGameMode(GameMode.ADVENTURE);
                                t.sendMessage(BuildHub.getInstance().getPrefix() + "Dein Gamemode wurde auf Adventure gestellt!");
                                sender.sendMessage(BuildHub.getInstance().getPrefix() + "Der Gamemode von " + t.getName() + " ist auf Adventure gesetzt worden!");
                            }
                            else if (b3) {
                                t.setGameMode(GameMode.SPECTATOR);
                                t.sendMessage(BuildHub.getInstance().getPrefix() + "Dein Gamemode wurde auf Spectator gestellt!");
                                sender.sendMessage(BuildHub.getInstance().getPrefix() + "Der Gamemode von " + t.getName() + " ist auf Spectator gesetzt worden!");
                            }
                            else {
                                sender.sendMessage(BuildHub.getInstance().getPrefix() + "Bitte gib einen gültigen Gamemode ein!");
                            }
                        }
                        else {
                            sender.sendMessage(BuildHub.getInstance().getPrefix() + "Der Spieler " + args[1] + " existiert nicht!");
                        }
                    }
                    catch (NullPointerException e) {
                        sender.sendMessage(BuildHub.getInstance().getPrefix() + "Der Spieler " + args[1] + " existiert nicht!");
                    }
                }
                else {
                    sender.sendMessage(BuildHub.getInstance().getPrefix() + "/gm 0|1|2|3");
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(BuildHub.getInstance().getPrefix() + "/gm 0|1|2|3");
            }
            return false;
        }

        if (player.hasPermission("mario.gm") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            String msg = String.join(" ", args);

            boolean b = msg.equals("0") || msg.equalsIgnoreCase("survival") || msg.equalsIgnoreCase("su");
            boolean b1 = msg.equals("1") || msg.equalsIgnoreCase("creative") || msg.equalsIgnoreCase("c");
            boolean b2 = msg.equals("2") || msg.equalsIgnoreCase("adventure") || msg.equalsIgnoreCase("a");
            boolean b3 = msg.equals("3") || msg.equalsIgnoreCase("spectator") || msg.equalsIgnoreCase("sp");

            try {
                if (args.length == 1) {
                    if (b) {
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Dein Gamemode wurde auf Survival gestellt!");
                    }
                    else if (b1) {
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Dein Gamemode wurde auf Creative gestellt!");
                    }
                    else if (b2) {
                        player.setGameMode(GameMode.ADVENTURE);
                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Dein Gamemode wurde auf Adventure gestellt!");
                    }
                    else if (b3) {
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Dein Gamemode wurde auf Spectator gestellt!");
                    }
                    else {
                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Bitte gib einen gültigen Gamemode ein!");
                    }
                }
                else if (args.length == 2) {
                    Player t = BuildHub.getInstance().getServer().getPlayer(args[1]);

                    try {
                        if (t != null) {
                            if (b) {
                                t.setGameMode(GameMode.SURVIVAL);
                                t.sendMessage(BuildHub.getInstance().getPrefix() + "Dein Gamemode wurde auf Survival gestellt!");
                                player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Gamemode von " + t.getName() + " ist auf Survival gesetzt worden!");
                            }
                            else if (b1) {
                                t.setGameMode(GameMode.CREATIVE);
                                t.sendMessage(BuildHub.getInstance().getPrefix() + "Dein Gamemode wurde auf Creative gestellt!");
                                player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Gamemode von " + t.getName() + " ist auf Creative gesetzt worden!");
                            }
                            else if (b2) {
                                t.setGameMode(GameMode.ADVENTURE);
                                t.sendMessage(BuildHub.getInstance().getPrefix() + "Dein Gamemode wurde auf Adventure gestellt!");
                                player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Gamemode von " + t.getName() + " ist auf Adventure gesetzt worden!");
                            }
                            else if (b3) {
                                t.setGameMode(GameMode.SPECTATOR);
                                t.sendMessage(BuildHub.getInstance().getPrefix() + "Dein Gamemode wurde auf Spectator gestellt!");
                                player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Gamemode von " + t.getName() + " ist auf Spectator gesetzt worden!");
                            }
                            else {
                                player.sendMessage(BuildHub.getInstance().getPrefix() + "Bitte gib einen gültigen Gamemode ein!");
                            }
                        }
                        else {
                            player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Spieler " + args[1] + " existiert nicht!");
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                        }
                    }
                    catch (NullPointerException e) {
                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Spieler " + args[1] + " existiert nicht!");
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    }
                }
                else {
                    player.sendMessage(BuildHub.getInstance().getPrefix() + "/gm 0|1|2|3");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage(BuildHub.getInstance().getPrefix() + "/gm 0|1|2|3");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
            }
        }
        else {
            player.sendMessage(BuildHub.getInstance().getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        }
        return false;
    }

    private final String[] GAMEMODES = { "0", "1", "2", "3", "survival", "creative", "adventure", "spectator", "su", "c", "a", "sp" };

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        final List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            StringUtil.copyPartialMatches(args[0], Arrays.asList(GAMEMODES), completions);
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
