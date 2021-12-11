package de.mariocst.bh.listener;

import de.mariocst.bh.config.configdata.DeathData;
import de.mariocst.bh.config.configdata.StatusData;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

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
