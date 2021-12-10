package de.mariocst.bh.config.configdata;

import de.mariocst.bh.BuildHub;
import de.mariocst.bh.config.configs.CoordsConfig;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class CoordsData {
    private static CoordsData coordsData;
    private final CoordsConfig config = BuildHub.getInstance().getCoordsConfig();

    private final Map<String, Map<String, Map<String, Double>>> coordsLoc = new HashMap<>();

    public CoordsData() {
        coordsData = this;

        if (config.getConfig().contains("coords")) {
            if (!Objects.requireNonNull(config.getConfig().getStringList("coords")).isEmpty()) {
                for (String playerUUID : config.getConfig().getStringList("coords")) {
                    Map<String, Map<String, Double>> locs = new HashMap<>();

                    for (String coords : config.getConfig().getStringList("coords." + playerUUID)) {
                        Map<String, Double> pos = new HashMap<>();
                        for (String coordinate : config.getConfig().getStringList("coords." + playerUUID + "." + coords)) {
                            switch (coordinate) {
                                case "x" -> pos.put("x", config.getConfig().getDouble("coords." + playerUUID + "." + coords + ".x"));
                                case "y" -> pos.put("y", config.getConfig().getDouble("coords." + playerUUID + "." + coords + ".y"));
                                case "z" -> pos.put("z", config.getConfig().getDouble("coords." + playerUUID + "." + coords + ".z"));
                                default -> BuildHub.getInstance().warn("WARNUNG! Coordinate Config beinhaltet keine XYZ Koordinaten bei " + playerUUID + "!");
                            }
                        }
                        locs.put(coords, pos);
                    }

                    coordsLoc.put(playerUUID, locs);
                }
            }
            else {
                Map<String, Map<String, Double>> nullLoc = new HashMap<>();
                Map<String, Double> nullPos = new HashMap<>();
                nullPos.put("x", 0.0);
                nullPos.put("y", 0.0);
                nullPos.put("z", 0.0);
                nullLoc.put("np", nullPos);
                coordsLoc.put("np", nullLoc);
            }
        }
        else {
            Map<String, Map<String, Double>> nullLoc = new HashMap<>();
            Map<String, Double> nullPos = new HashMap<>();
            nullPos.put("x", 0.0);
            nullPos.put("y", 0.0);
            nullPos.put("z", 0.0);
            nullLoc.put("np", nullPos);
            coordsLoc.put("np", nullLoc);
        }
    }

    public static CoordsData getCoordsData() {
        return coordsData;
    }

    public Map<String, Map<String, Map<String, Double>>> getCoordsLoc() {
        return coordsLoc;
    }

    public void addCoords(Player player, String name, Location location) {
        Map<String, Map<String, Double>> map = new HashMap<>();
        Map<String, Double> loc = new HashMap<>();

        loc.put("x", location.getX());
        loc.put("y", location.getY());
        loc.put("z", location.getZ());
        map.put(name, loc);

        this.coordsLoc.put(player.getUniqueId().toString(), map);
    }

    public void removeCoords(Player player, String name) {
        this.coordsLoc.get(player.getUniqueId().toString()).remove(name);
    }

    public void save() {
        this.config.getConfig().set("coords", coordsLoc);
    }
}
