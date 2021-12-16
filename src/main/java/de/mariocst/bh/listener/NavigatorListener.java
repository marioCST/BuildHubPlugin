package de.mariocst.bh.listener;

import de.mariocst.bh.BuildHub;
import de.mariocst.bh.config.configdata.BaseData;
import de.mariocst.bh.exceptions.BaseNotFoundException;
import net.kyori.adventure.text.Component;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.Objects;

public class NavigatorListener implements Listener {
    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();

        if (player.getOpenInventory().title().equals(Component.text("§cNavigator"))) event.setCancelled(true);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (player.getOpenInventory().title().equals(Component.text("§cNavigator"))) {
            if (event.getCurrentItem() == null || event.getClickedInventory() == player.getInventory()) return;

            event.setCancelled(true);

            if (Objects.equals(event.getCurrentItem().getItemMeta().displayName(), Component.text("§bBase"))) {
                try {
                    player.teleport(BaseData.getBaseData().getBase(player));
                    player.sendMessage(BuildHub.getInstance().getPrefix() + "Du wurdest zu deiner Base teleportiert!");
                }
                catch (BaseNotFoundException e) {
                    player.sendMessage(BuildHub.getInstance().getPrefix() + "Du hast noch keine Base gesetzt!");
                }

                player.closeInventory();
            }
            else if (Objects.equals(event.getCurrentItem().getItemMeta().displayName(), Component.text("§2Spawn"))) {
                if (BuildHub.getInstance().getServer().getWorld("world") != null) {
                    World world = BuildHub.getInstance().getServer().getWorld("world");
                    player.teleport(Objects.requireNonNull(world).getSpawnLocation());
                    player.sendMessage(BuildHub.getInstance().getPrefix() + "Du wurdest zum Spawn teleportiert!");
                }
                else {
                    if (player.isOp()) {
                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Es gibt aus irgendeinem Grund die Welt mit dem Namen \"world\" nicht...");
                    }
                }

                player.closeInventory();
            }
            else if (Objects.equals(event.getCurrentItem().getItemMeta().displayName(), Component.text("§cNether"))) {
                if (BuildHub.getInstance().getServer().getWorld("world_nether") != null) {
                    World nether = BuildHub.getInstance().getServer().getWorld("world_nether");
                    player.teleport(Objects.requireNonNull(nether).getSpawnLocation());
                    player.sendMessage(BuildHub.getInstance().getPrefix() + "Du wurdest in den Nether teleportiert!");
                }
                else {
                    if (player.isOp()) {
                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Es gibt aus irgendeinem Grund die Welt mit dem Namen \"world_nether\" nicht...");
                    }
                }

                player.closeInventory();
            }
            else if (Objects.equals(event.getCurrentItem().getItemMeta().displayName(), Component.text("§eEnd"))) {
                if (BuildHub.getInstance().getServer().getWorld("world_the_end") != null) {
                    World end = BuildHub.getInstance().getServer().getWorld("world_the_end");
                    player.teleport(Objects.requireNonNull(end).getSpawnLocation());
                    player.sendMessage(BuildHub.getInstance().getPrefix() + "Du wurdest ins End teleportiert!");
                }
                else {
                    if (player.isOp()) {
                        player.sendMessage(BuildHub.getInstance().getPrefix() + "Es gibt aus irgendeinem Grund die Welt mit dem Namen \"world_the_end\" nicht...");
                    }
                }

                player.closeInventory();
            }
            else if (Objects.equals(event.getCurrentItem().getItemMeta().displayName(), Component.text("§cSchließen"))) {
                player.closeInventory();
            }
        }
    }
}
