package de.mariocst.bh.config.configdata;

import de.mariocst.bh.BuildHub;
import de.mariocst.bh.config.configs.Config;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Spawn {
    private static Spawn spawn;

    private String worldName;
    private double x, y, z;
    private float yaw, pitch;

    public Spawn() {
        spawn = this;

        Config config = BuildHub.getInstance().getConfiguration();

        if (config.getConfig().contains("spawn.worldName")) {
            this.worldName = config.getConfig().getString("spawn.worldName");
        }
        else {
            this.worldName = "world";
        }

        if (config.getConfig().contains("spawn.x")) {
            this.x = config.getConfig().getDouble("spawn.x");
        }
        else {
            this.x = 0;
        }

        if (config.getConfig().contains("spawn.y")) {
            this.y = config.getConfig().getDouble("spawn.y");
        }
        else {
            this.y = 0;
        }

        if (config.getConfig().contains("spawn.z")) {
            this.z = config.getConfig().getDouble("spawn.z");
        }
        else {
            this.z = 0;
        }

        if (config.getConfig().contains("spawn.yaw")) {
            this.yaw = (float) config.getConfig().getDouble("spawn.yaw");
        }
        else {
            this.yaw = 0;
        }

        if (config.getConfig().contains("spawn.pitch")) {
            this.pitch = (float) config.getConfig().getDouble("spawn.pitch");
        }
        else {
            this.pitch = 0;
        }
    }

    public static Spawn getSpawn() {
        return spawn;
    }

    public Location getSpawnLocation() {
        return new Location(BuildHub.getInstance().getServer().getWorld(this.worldName), this.x, this.y, this.z, this.yaw, this.pitch);
    }

    public void setSpawnLocation(Player player) {
        this.worldName = player.getWorld().getName();
        this.x = player.getLocation().getX();
        this.y = player.getLocation().getY();
        this.z = player.getLocation().getZ();
        this.yaw = player.getLocation().getYaw();
        this.pitch = player.getLocation().getPitch();
    }

    public void save() {
        Config config = BuildHub.getInstance().getConfiguration();

        config.getConfig().set("spawn.worldName", this.worldName);
        config.getConfig().set("spawn.x", this.x);
        config.getConfig().set("spawn.y", this.y);
        config.getConfig().set("spawn.z", this.z);
        config.getConfig().set("spawn.yaw", this.yaw);
        config.getConfig().set("spawn.pitch", this.pitch);
    }
}
