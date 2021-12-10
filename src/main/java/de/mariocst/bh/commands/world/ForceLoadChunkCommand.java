package de.mariocst.bh.commands.world;

/*import com.sk89q.worldedit.IncompleteRegionException;
*import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.math.BlockVector2;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.session.SessionManager;
import com.sk89q.worldedit.util.formatting.text.TextComponent;
import com.sk89q.worldedit.world.World;*/
import de.mariocst.bh.BuildHub;
import org.bukkit.Chunk;
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

public class ForceLoadChunkCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            BuildHub.getInstance().log("Bitte führe den Command InGame aus!");
            return false;
        }

        if (player.hasPermission("mario.forceloadchunk") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            if (args.length == 0) {
                if (player.getLocation().getChunk().isForceLoaded()) {
                    player.getLocation().getChunk().setForceLoaded(false);
                    player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Chunk bei X: " + player.getLocation().getChunk().getX() + " und Z: " + player.getLocation().getChunk().getZ() + " ist nun nicht mehr force loaded!");
                }
                else {
                    player.getLocation().getChunk().setForceLoaded(true);
                    player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Chunk bei X: " + player.getLocation().getChunk().getX() + " und Z: " + player.getLocation().getChunk().getZ() + " ist nun force loaded!");
                }
            }
            else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("region")) {
                    player.sendMessage(BuildHub.getInstance().getPrefix() + "Aufgrund des Minecraft 1.18 Updates ist dieses Feature nicht enthalten, bis WorldEdit auf der 1.18 ist.");

                    /*BukkitPlayer bukkitPlayer = BukkitAdapter.adapt(player);
                    *SessionManager manager = WorldEdit.getInstance().getSessionManager();
                    LocalSession localSession = manager.get(bukkitPlayer);
                    Region region;

                    World selectionWorld = localSession.getSelectionWorld();
                    try {
                        if (selectionWorld == null) throw new IncompleteRegionException();
                        region = localSession.getSelection(selectionWorld);
                    } catch (IncompleteRegionException ex) {
                        bukkitPlayer.printError(TextComponent.of("Bitte markiere erst eine Region."));
                        return false;
                    }

                    switch (args[1].toLowerCase()) {
                        case "true" -> {
                            for (BlockVector2 blockVector2 : region.getChunks()) {
                                Chunk chunk = player.getWorld().getChunkAt(blockVector2.getX(), blockVector2.getZ());
                                if (!chunk.isForceLoaded()) {
                                    chunk.setForceLoaded(true);
                                    player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Chunk bei X: " + chunk.getX() + " und Z: " + chunk.getZ() + " ist nun force loaded!");
                                }
                            }
                        }
                        case "false" -> {
                            for (BlockVector2 blockVector2 : region.getChunks()) {
                                Chunk chunk = player.getWorld().getChunkAt(blockVector2.getX(), blockVector2.getZ());
                                if (chunk.isForceLoaded()) {
                                    chunk.setForceLoaded(true);
                                    player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Chunk bei X: " + chunk.getX() + " und Z: " + chunk.getZ() + " ist nun nicht mehr force loaded!");
                                }
                            }
                        }
                        default -> {
                            player.sendMessage(BuildHub.getInstance().getPrefix() + "/forceloadchunk region <true|false>");
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                        }
                    }*/
                }
                else {
                    player.sendMessage(BuildHub.getInstance().getPrefix() + "/forceloadchunk region <true|false>");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                }
            }
            else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("radius")) {
                    try {
                        int radius = Integer.parseInt(args[1]);

                        switch (args[2].toLowerCase()) {
                            case "true" -> {
                                for (int i = 0; i <= radius; i++) {
                                    Chunk chunk = player.getWorld().getChunkAt(player.getChunk().getX() + i, player.getChunk().getZ());
                                    if (!chunk.isForceLoaded()) {
                                        chunk.setForceLoaded(true);
                                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Chunk bei X: " + chunk.getX() + " und Z: " + chunk.getZ() + " ist nun force loaded!");
                                    }

                                    chunk = player.getWorld().getChunkAt(player.getChunk().getX() - i, player.getChunk().getZ());
                                    if (!chunk.isForceLoaded()) {
                                        chunk.setForceLoaded(true);
                                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Chunk bei X: " + chunk.getX() + " und Z: " + chunk.getZ() + " ist nun force loaded!");
                                    }

                                    chunk = player.getWorld().getChunkAt(player.getChunk().getX(), player.getChunk().getZ() + i);
                                    if (!chunk.isForceLoaded()) {
                                        chunk.setForceLoaded(true);
                                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Chunk bei X: " + chunk.getX() + " und Z: " + chunk.getZ() + " ist nun force loaded!");
                                    }

                                    chunk = player.getWorld().getChunkAt(player.getChunk().getX(), player.getChunk().getZ() - i);
                                    if (!chunk.isForceLoaded()) {
                                        chunk.setForceLoaded(true);
                                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Chunk bei X: " + chunk.getX() + " und Z: " + chunk.getZ() + " ist nun force loaded!");
                                    }

                                    chunk = player.getWorld().getChunkAt(player.getChunk().getX() + i, player.getChunk().getZ() + i);
                                    if (!chunk.isForceLoaded()) {
                                        chunk.setForceLoaded(true);
                                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Chunk bei X: " + chunk.getX() + " und Z: " + chunk.getZ() + " ist nun force loaded!");
                                    }

                                    chunk = player.getWorld().getChunkAt(player.getChunk().getX() + i, player.getChunk().getZ() - i);
                                    if (!chunk.isForceLoaded()) {
                                        chunk.setForceLoaded(true);
                                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Chunk bei X: " + chunk.getX() + " und Z: " + chunk.getZ() + " ist nun force loaded!");
                                    }

                                    chunk = player.getWorld().getChunkAt(player.getChunk().getX() - i, player.getChunk().getZ() + i);
                                    if (!chunk.isForceLoaded()) {
                                        chunk.setForceLoaded(true);
                                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Chunk bei X: " + chunk.getX() + " und Z: " + chunk.getZ() + " ist nun force loaded!");
                                    }

                                    chunk = player.getWorld().getChunkAt(player.getChunk().getX() - i, player.getChunk().getZ() - i);
                                    if (!chunk.isForceLoaded()) {
                                        chunk.setForceLoaded(true);
                                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Chunk bei X: " + chunk.getX() + " und Z: " + chunk.getZ() + " ist nun force loaded!");
                                    }
                                }
                            }
                            case "false" -> {
                                for (int i = 0; i <= radius; i++) {
                                    Chunk chunk = player.getWorld().getChunkAt(player.getChunk().getX() + i, player.getChunk().getZ());
                                    if (chunk.isForceLoaded()) {
                                        chunk.setForceLoaded(false);
                                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Chunk bei X: " + chunk.getX() + " und Z: " + chunk.getZ() + " ist nun nicht mehr force loaded!");
                                    }

                                    chunk = player.getWorld().getChunkAt(player.getChunk().getX() - i, player.getChunk().getZ());
                                    if (chunk.isForceLoaded()) {
                                        chunk.setForceLoaded(false);
                                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Chunk bei X: " + chunk.getX() + " und Z: " + chunk.getZ() + " ist nun nicht mehr force loaded!");
                                    }

                                    chunk = player.getWorld().getChunkAt(player.getChunk().getX(), player.getChunk().getZ() + i);
                                    if (chunk.isForceLoaded()) {
                                        chunk.setForceLoaded(false);
                                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Chunk bei X: " + chunk.getX() + " und Z: " + chunk.getZ() + " ist nun nicht mehr force loaded!");
                                    }

                                    chunk = player.getWorld().getChunkAt(player.getChunk().getX(), player.getChunk().getZ() - i);
                                    if (chunk.isForceLoaded()) {
                                        chunk.setForceLoaded(false);
                                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Chunk bei X: " + chunk.getX() + " und Z: " + chunk.getZ() + " ist nun nicht mehr force loaded!");
                                    }

                                    chunk = player.getWorld().getChunkAt(player.getChunk().getX() + i, player.getChunk().getZ() + i);
                                    if (chunk.isForceLoaded()) {
                                        chunk.setForceLoaded(false);
                                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Chunk bei X: " + chunk.getX() + " und Z: " + chunk.getZ() + " ist nun nicht mehr force loaded!");
                                    }

                                    chunk = player.getWorld().getChunkAt(player.getChunk().getX() + i, player.getChunk().getZ() - i);
                                    if (chunk.isForceLoaded()) {
                                        chunk.setForceLoaded(false);
                                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Chunk bei X: " + chunk.getX() + " und Z: " + chunk.getZ() + " ist nun nicht mehr force loaded!");
                                    }

                                    chunk = player.getWorld().getChunkAt(player.getChunk().getX() - i, player.getChunk().getZ() + i);
                                    if (chunk.isForceLoaded()) {
                                        chunk.setForceLoaded(false);
                                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Chunk bei X: " + chunk.getX() + " und Z: " + chunk.getZ() + " ist nun nicht mehr force loaded!");
                                    }

                                    chunk = player.getWorld().getChunkAt(player.getChunk().getX() - i, player.getChunk().getZ() - i);
                                    if (chunk.isForceLoaded()) {
                                        chunk.setForceLoaded(false);
                                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Chunk bei X: " + chunk.getX() + " und Z: " + chunk.getZ() + " ist nun nicht mehr force loaded!");
                                    }
                                }
                            }
                            default -> {
                                player.sendMessage(BuildHub.getInstance().getPrefix() + "/forceloadchunk radius <zahl> <true|false>");
                                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                            }
                        }
                    } catch (NumberFormatException e) {
                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Bitte gib eine ganze Zahl ein!");
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    }
                }
                else {
                    player.sendMessage(BuildHub.getInstance().getPrefix() + "/forceloadchunk radius <zahl> <true|false>");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                }
            }
            else {
                player.sendMessage(BuildHub.getInstance().getPrefix() + "/forceloadchunk [radius|region] [zahl|true|false] [true|false]");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
            }
        }
        else {
            player.sendMessage(BuildHub.getInstance().getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        }
        return false;
    }

    private final String[] MODES = { "radius", "region" };
    private final String[] MODES2 = { "1" };
    private final String[] MODES3 = { "true", "false" };

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        final List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            StringUtil.copyPartialMatches(args[0], Arrays.asList(MODES), completions);
            Collections.sort(completions);
        }
        else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("region")) {
                StringUtil.copyPartialMatches(args[1], Arrays.asList(MODES3), completions);
                Collections.sort(completions);
            }
            else if (args[0].equalsIgnoreCase("radius")) {
                StringUtil.copyPartialMatches(args[1], Arrays.asList(MODES2), completions);
                Collections.sort(completions);
            }
        }
        else if (args.length == 3) {
            StringUtil.copyPartialMatches(args[2], Arrays.asList(MODES3), completions);
            Collections.sort(completions);
        }

        return completions;
    }
}
