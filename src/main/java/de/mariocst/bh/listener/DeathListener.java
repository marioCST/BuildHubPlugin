package de.mariocst.bh.listener;

import de.mariocst.bh.BuildHub;
import de.mariocst.bh.config.configdata.DeathData;
import de.mariocst.bh.config.configdata.StatusData;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class DeathListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        ItemStack stack = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) stack.getItemMeta();

        meta.setPlayerProfile(player.getPlayerProfile());
        stack.setItemMeta(meta);

        player.getWorld().dropItem(player.getLocation(), stack);
    }

    @EventHandler
    public void onDeaths(PlayerDeathEvent event) {
        Player player = event.getPlayer();

        DeathData.getDeathData().addDeath(player);
        BuildHub.getInstance().saveConfigs();

        if (StatusData.getStatusData().getStatus(player) != null) {
            player.displayName(Component.text(this.getStatus(player))
                    .append(Component.text(" "))
                    .append(player.name())
                    .append(Component.text("\nTode: §e" + DeathData.getDeathData().getDeaths(player))));

            player.playerListName(Component.text(this.getStatus(player))
                    .append(Component.text(" "))
                    .append(player.name())
                    .append(Component.text(" §e" + DeathData.getDeathData().getDeaths(player))));
        }
        else {
            player.displayName(player.name()
                    .append(Component.text("\nTode: §e" + DeathData.getDeathData().getDeaths(player))));

            player.playerListName(player.name()
                    .append(Component.text(" §e" + DeathData.getDeathData().getDeaths(player))));
        }
    }

    private String getStatus(Player player) {
        return StatusData.getStatusData().getStatus(player)
                .replaceAll("aufnahme", "§0[§4Aufnahme§0]")
                .replaceAll("bauen", "§0[§1Bauen§0]")
                .replaceAll("hilfe", "§0[§eHilfe§0]")
                .replaceAll("live", "§0[§cLive§0]")
                .replaceAll("redstone", "§0[§4Redstone§0]")
                .replaceAll("verzweifelt", "§0[§6Verzweifelt§0]");
    }
}
