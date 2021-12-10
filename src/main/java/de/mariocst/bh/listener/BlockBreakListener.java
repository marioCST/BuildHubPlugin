package de.mariocst.bh.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakListener implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (event.getBlock().getType() == Material.SPAWNER) {
            if (player.getInventory().getItemInMainHand().getType() == Material.DIAMOND_PICKAXE || player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_PICKAXE) {
                event.setExpToDrop(0);
                player.getInventory().addItem(new ItemStack(Material.SPAWNER, 1));
            }
        }
    }
}
