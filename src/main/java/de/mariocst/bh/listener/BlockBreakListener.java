package de.mariocst.bh.listener;

import de.mariocst.bh.BuildHub;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class BlockBreakListener implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (event.getBlock().getType() == Material.SPAWNER) {
            if (player.getInventory().getItemInMainHand().getType() == Material.DIAMOND_PICKAXE || player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_PICKAXE) {
                CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getState();
                ItemStack stack = new ItemStack(Objects.requireNonNull(Material.getMaterial(spawner.getSpawnedType() + "_SPAWN_EGG")), 1);

                event.setExpToDrop(0);
                if (spawner.getSpawnedType() == EntityType.PIG) {
                    player.sendMessage(BuildHub.getInstance().getPrefix() + "Damit man nicht dupen kann, kann man keine Pig Spawn Eggs bekommen!");
                }
                else {
                    player.getInventory().addItem(stack);
                }
                player.getInventory().addItem(new ItemStack(Material.SPAWNER, 1));
            }
        }
    }
}
