package de.mariocst.bh.listener;

import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
import de.mariocst.bh.BuildHub;
import de.mariocst.bh.utils.FakePlayerProfile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ServerListPingListener implements Listener {
    @EventHandler
    public void onPing(PaperServerListPingEvent event) {
        event.setVersion("Minecraft 1.18.x");

        int players = 0;

        for (Player ignored : BuildHub.getInstance().getServer().getOnlinePlayers()) {
            players++;
        }

        event.setNumPlayers(players);

        event.getPlayerSample().clear();
        event.getPlayerSample().add(new FakePlayerProfile("\u00A71\u00A7lBuild\u00A72\u00A7lHub"));
        event.getPlayerSample().add(new FakePlayerProfile("\u00A7b\u00A7l1.18.x"));
    }
}
