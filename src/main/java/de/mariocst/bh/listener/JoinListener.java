package de.mariocst.bh.listener;

import de.mariocst.bh.BuildHub;
import de.mariocst.bh.config.configdata.DeathData;
import de.mariocst.bh.config.configdata.StatusData;
import de.mariocst.bh.scoreboard.DeathScoreboard;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Scoreboard scoreboard = BuildHub.getInstance().getServer().getScoreboardManager().getNewScoreboard();
        if (DeathData.getDeathData().shouldDisplayScoreboard()) player.setScoreboard(scoreboard);
        new DeathScoreboard(Component.text("§cTode"), scoreboard);

        player.displayName(Component.text(this.getStatus(player))
                .append(player.name()));

        player.playerListName(Component.text(this.getStatus(player))
                .append(player.name()));

        if (DeathData.getDeathData().shouldDisplayDeaths()) {
            player.displayName(player.displayName()
                    .append(Component.text("\nTode: §e" + DeathData.getDeathData().getDeaths(player))));

            player.playerListName(player.playerListName()
                    .append(Component.text(" §e" + DeathData.getDeathData().getDeaths(player))));
        }
    }

    private String getStatus(Player player) {
        return StatusData.getStatusData().getStatus(player)
                .replaceAll("aufnahme", "§0[§4Aufnahme§0] ")
                .replaceAll("bauen", "§0[§1Bauen§0] ")
                .replaceAll("hilfe", "§0[§eHilfe§0] ")
                .replaceAll("live", "§0[§cLive§0] ")
                .replaceAll("redstone", "§0[§4Redstone§0] ")
                .replaceAll("verzweifelt", "§0[§6Verzweifelt§0] ");
    }
}
