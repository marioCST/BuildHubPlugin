package de.mariocst.bh.listener;

import de.mariocst.bh.BuildHub;
import io.papermc.paper.event.player.PlayerItemFrameChangeEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class ProtectListener implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("mario.protect.break") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) return;

        if (!player.getWorld().getName().equals("BH3")) return;

        if (event.getBlock().getX() <= -133 && event.getBlock().getX() >= -214
                && event.getBlock().getZ() <= 153 && event.getBlock().getZ() >= 44) {
            event.setCancelled(true);
            player.sendMessage(BuildHub.getInstance().getPrefix() + "§cDu darfst hier nicht abbauen!");
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("mario.protect.place") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) return;

        if (!player.getWorld().getName().equals("BH3")) return;

        if (event.getBlock().getX() <= -133 && event.getBlock().getX() >= -214
                && event.getBlock().getZ() <= 153 && event.getBlock().getZ() >= 44) {
            event.setCancelled(true);
            player.sendMessage(BuildHub.getInstance().getPrefix() + "§cDu darfst hier nicht bauen!");
        }
    }

    @EventHandler
    public void onItemFrameChange(PlayerItemFrameChangeEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("mario.protect.itemframe") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) return;

        if (!player.getWorld().getName().equals("BH3")) return;

        if (event.getItemFrame().getLocation().getX() <= -133 && event.getItemFrame().getLocation().getX() >= -214
                && event.getItemFrame().getLocation().getZ() <= 153 && event.getItemFrame().getLocation().getZ() >= 44) {
            event.setCancelled(true);
            player.sendMessage(BuildHub.getInstance().getPrefix() + "§cDu darfst hier nicht mit Item Frames interagieren!");
        }
    }
}
