package de.mariocst.bh.listener;

import de.mariocst.bh.config.configdata.FireballData;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class FireballListener implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (!player.getName().equals("marioCST")) return;

        if (event.getAction() != Action.RIGHT_CLICK_AIR) return;

        if (player.getInventory().getItemInMainHand().getType() != Material.FIRE_CHARGE) return;

        player.launchProjectile(Fireball.class, player.getLocation().getDirection().multiply(FireballData.getFireballData().getMultiplier())).setYield((float) FireballData.getFireballData().getYield());
    }
}
