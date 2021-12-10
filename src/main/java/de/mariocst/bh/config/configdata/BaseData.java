package de.mariocst.bh.config.configdata;

import de.mariocst.bh.BuildHub;
import de.mariocst.bh.config.configs.BaseConfig;
import de.mariocst.bh.exceptions.BaseNotFoundException;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class BaseData {
    private static BaseData baseData;

    private final HashMap<UUID, String> worldName = new HashMap<>();
    private final HashMap<UUID, Double> x = new HashMap<>();
    private final HashMap<UUID, Double> y = new HashMap<>();
    private final HashMap<UUID, Double> z = new HashMap<>();
    private final HashMap<UUID, Float> yaw = new HashMap<>();
    private final HashMap<UUID, Float> pitch = new HashMap<>();

    public BaseData() {
        baseData = this;

        BaseConfig config = BuildHub.getInstance().getBaseConfig();

        if (config.getConfig().contains("base")) {
            for (String string : config.getConfig().getStringList("base")) {
                UUID uuid = UUID.fromString(string);

                this.worldName.put(uuid, config.getConfig().getString("base." + uuid + ".worldName"));
                this.x.put(uuid, config.getConfig().getDouble("base." + uuid + ".x"));
                this.y.put(uuid, config.getConfig().getDouble("base." + uuid + ".y"));
                this.z.put(uuid, config.getConfig().getDouble("base." + uuid + ".z"));
                this.yaw.put(uuid, (float) config.getConfig().getDouble("base." + uuid + ".yaw"));
                this.pitch.put(uuid, (float) config.getConfig().getDouble("base." + uuid + ".pitch"));
            }
        }
    }

    public static BaseData getBaseData() {
        return baseData;
    }

    public Location getBase(Player player) throws BaseNotFoundException {
        BaseConfig config = BuildHub.getInstance().getBaseConfig();

        if (config.getConfig().contains("base." + player.getUniqueId() + ".worldName") &&
                config.getConfig().contains("base." + player.getUniqueId() + ".x") &&
                config.getConfig().contains("base." + player.getUniqueId() + ".y") &&
                config.getConfig().contains("base." + player.getUniqueId() + ".z") &&
                config.getConfig().contains("base." + player.getUniqueId() + ".yaw") &&
                config.getConfig().contains("base." + player.getUniqueId() + ".pitch")) {
            return new Location(
                    BuildHub.getInstance().getServer().getWorld(Objects.requireNonNull(config.getConfig().getString("base." + player.getUniqueId() + ".worldName"))),
                    config.getConfig().getDouble("base." + player.getUniqueId() + ".x"),
                    config.getConfig().getDouble("base." + player.getUniqueId() + ".y"),
                    config.getConfig().getDouble("base." + player.getUniqueId() + ".z"),
                    (float) config.getConfig().getDouble("base." + player.getUniqueId() + ".yaw"),
                    (float) config.getConfig().getDouble("base." + player.getUniqueId() + ".pitch")
            );
        }
        else {
            throw new BaseNotFoundException();
        }
    }

    public Location getBase(OfflinePlayer player) throws BaseNotFoundException {
        BaseConfig config = BuildHub.getInstance().getBaseConfig();

        if (config.getConfig().contains("base." + player.getUniqueId() + ".worldName") &&
                config.getConfig().contains("base." + player.getUniqueId() + ".x") &&
                config.getConfig().contains("base." + player.getUniqueId() + ".y") &&
                config.getConfig().contains("base." + player.getUniqueId() + ".z") &&
                config.getConfig().contains("base." + player.getUniqueId() + ".yaw") &&
                config.getConfig().contains("base." + player.getUniqueId() + ".pitch")) {
            return new Location(
                    BuildHub.getInstance().getServer().getWorld(Objects.requireNonNull(config.getConfig().getString("base." + player.getUniqueId() + ".worldName"))),
                    config.getConfig().getDouble("base." + player.getUniqueId() + ".x"),
                    config.getConfig().getDouble("base." + player.getUniqueId() + ".y"),
                    config.getConfig().getDouble("base." + player.getUniqueId() + ".z"),
                    (float) config.getConfig().getDouble("base." + player.getUniqueId() + ".yaw"),
                    (float) config.getConfig().getDouble("base." + player.getUniqueId() + ".pitch")
            );
        }
        else {
            throw new BaseNotFoundException();
        }
    }

    public void setBase(Player player) {
        this.worldName.put(player.getUniqueId(), player.getWorld().getName());
        this.x.put(player.getUniqueId(), player.getLocation().getX());
        this.y.put(player.getUniqueId(), player.getLocation().getY());
        this.z.put(player.getUniqueId(), player.getLocation().getZ());
        this.yaw.put(player.getUniqueId(), player.getLocation().getYaw());
        this.pitch.put(player.getUniqueId(), player.getLocation().getPitch());
    }

    public void save() {
        BaseConfig config = BuildHub.getInstance().getBaseConfig();

        if (!this.worldName.isEmpty()) {
            for (UUID uuid : this.worldName.keySet()) {
                config.getConfig().set("base." + uuid + ".worldName", this.worldName.get(uuid));
            }
        }

        if (!this.x.isEmpty()) {
            for (UUID uuid : this.x.keySet()) {
                config.getConfig().set("base." + uuid + ".x", this.x.get(uuid));
            }
        }

        if (!this.y.isEmpty()) {
            for (UUID uuid : this.y.keySet()) {
                config.getConfig().set("base." + uuid + ".y", this.y.get(uuid));
            }
        }

        if (!this.z.isEmpty()) {
            for (UUID uuid : this.z.keySet()) {
                config.getConfig().set("base." + uuid + ".z", this.z.get(uuid));
            }
        }

        if (!this.yaw.isEmpty()) {
            for (UUID uuid : this.yaw.keySet()) {
                config.getConfig().set("base." + uuid + ".yaw", this.yaw.get(uuid));
            }
        }

        if (!this.pitch.isEmpty()) {
            for (UUID uuid : this.pitch.keySet()) {
                config.getConfig().set("base." + uuid + ".pitch", this.pitch.get(uuid));
            }
        }
    }
}
