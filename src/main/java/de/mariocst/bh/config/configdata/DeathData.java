package de.mariocst.bh.config.configdata;

import de.mariocst.bh.BuildHub;
import de.mariocst.bh.config.configs.DeathConfig;
import de.mariocst.bh.scoreboard.DeathScoreboard;
import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.UUID;

public class DeathData {
    private static DeathData deathData;

    private final HashMap<UUID, Integer> deaths = new HashMap<>();
    private final boolean shouldDisplayDeaths, shouldDisplayScoreboard, animateScoreboard;

    public DeathData() {
        deathData = this;

        DeathConfig config = BuildHub.getInstance().getDeathConfig();

        if (config.getConfig().contains("deaths")) {
            for (String string : config.getConfig().getStringList("deaths")) {
                UUID uuid = UUID.fromString(string);

                this.deaths.put(uuid, config.getConfig().getInt("deaths." + uuid));
            }
        }

        if (config.getConfig().contains("shouldDisplayDeaths")) {
            this.shouldDisplayDeaths = config.getConfig().getBoolean("shouldDisplayDeaths");
        }
        else {
            this.shouldDisplayDeaths = true;
        }

        for (Player player : BuildHub.getInstance().getServer().getOnlinePlayers()) {
            player.displayName(Component.text(getStatus(player))
                    .append(player.name()));

            player.playerListName(Component.text(getStatus(player))
                    .append(player.name()));

            if (this.shouldDisplayDeaths) {
                player.displayName(player.displayName()
                        .append(Component.text("\nTode: §e" + DeathData.getDeathData().getDeaths(player))));

                player.playerListName(player.playerListName()
                        .append(Component.text(" §e" + DeathData.getDeathData().getDeaths(player))));
            }
        }

        if (config.getConfig().contains("shouldDisplayScoreboard")) {
            this.shouldDisplayScoreboard = config.getConfig().getBoolean("shouldDisplayScoreboard");
        }
        else {
            this.shouldDisplayScoreboard = true;
        }

        if (this.shouldDisplayScoreboard) {
            for (Player player : BuildHub.getInstance().getServer().getOnlinePlayers()) {
                player.sendMessage(BuildHub.getInstance().getPrefix() + "Das Plugin wurde reloaded. Wenn ihr keine Berechtigungen für /scoreboard habt, müsst ihr rejoinen.");
            }
        }

        if (config.getConfig().contains("animateScoreboard")) {
            this.animateScoreboard = config.getConfig().getBoolean("animateScoreboard");
        }
        else {
            this.animateScoreboard = true;
        }
    }

    public boolean shouldDisplayDeaths() {
        return shouldDisplayDeaths;
    }

    public boolean shouldDisplayScoreboard() {
        return shouldDisplayScoreboard;
    }

    public boolean animateScoreboard() {
        return animateScoreboard;
    }

    public void addDeath(Player player) {
        DeathConfig config = BuildHub.getInstance().getDeathConfig();

        if (config.getConfig().contains("deaths." + player.getUniqueId())) {
            this.deaths.put(player.getUniqueId(), config.getConfig().getInt("deaths." + player.getUniqueId()) + 1);
        }
        else {
            this.deaths.put(player.getUniqueId(), 1);
        }
    }

    public void resetDeaths(Player player) {
        this.deaths.put(player.getUniqueId(), 0);
        this.save();

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

    public void resetDeaths(OfflinePlayer player) {
        this.deaths.put(player.getUniqueId(), 0);
        this.save();
    }

    public int getDeaths(Player player) {
        DeathConfig config = BuildHub.getInstance().getDeathConfig();

        if (config.getConfig().contains("deaths." + player.getUniqueId())) {
            return config.getConfig().getInt("deaths." + player.getUniqueId());
        }
        else {
            return 0;
        }
    }

    public int getDeaths(OfflinePlayer player) {
        DeathConfig config = BuildHub.getInstance().getDeathConfig();

        if (config.getConfig().contains("deaths." + player.getUniqueId())) {
            return config.getConfig().getInt("deaths." + player.getUniqueId());
        }
        else {
            return 0;
        }
    }

    public static DeathData getDeathData() {
        return deathData;
    }

    public void save() {
        DeathConfig config = BuildHub.getInstance().getDeathConfig();

        config.getConfig().set("shouldDisplayDeaths", this.shouldDisplayDeaths);
        config.getConfig().set("shouldDisplayScoreboard", this.shouldDisplayScoreboard);
        config.getConfig().set("animateScoreboard", this.animateScoreboard);

        if (!this.deaths.isEmpty()) {
            for (UUID uuid : this.deaths.keySet()) {
                config.getConfig().set("deaths." + uuid, this.deaths.get(uuid));
            }
        }
    }
}
