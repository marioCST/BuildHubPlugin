package de.mariocst.bh.config.configdata;

import de.mariocst.bh.BuildHub;
import de.mariocst.bh.config.configs.StatusConfig;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.UUID;

public class StatusData {
    private static StatusData statusData;

    private final HashMap<UUID, String> status = new HashMap<>();

    public StatusData() {
        statusData = this;

        StatusConfig config = BuildHub.getInstance().getStatusConfig();

        if (config.getConfig().contains("status")) {
            for (String string : config.getConfig().getStringList("status")) {
                UUID uuid = UUID.fromString(string);

                this.status.put(uuid, config.getConfig().getString("status." + uuid));
            }
        }
    }

    public void saveStatus(Player player, String status) {
        this.status.put(player.getUniqueId(), status);
        this.save();
    }

    public void resetStatus(Player player) {
        this.status.put(player.getUniqueId(), null);
        this.save();
    }

    public String getStatus(Player player) {
        StatusConfig config = BuildHub.getInstance().getStatusConfig();

        if (config.getConfig().contains("status." + player.getUniqueId())) {
            return config.getConfig().getString("status." + player.getUniqueId());
        }
        else {
            return null;
        }
    }

    public static StatusData getStatusData() {
        return statusData;
    }

    public void save() {
        StatusConfig config = BuildHub.getInstance().getStatusConfig();

        if (!this.status.isEmpty()) {
            for (UUID uuid : this.status.keySet()) {
                config.getConfig().set("status." + uuid, this.status.get(uuid));
            }
        }
    }
}
