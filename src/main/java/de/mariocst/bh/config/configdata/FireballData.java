package de.mariocst.bh.config.configdata;

import de.mariocst.bh.BuildHub;
import de.mariocst.bh.config.configs.Config;

public class FireballData {
    private final double yield, multiplier;

    private static FireballData fireballData;

    public FireballData() {
        fireballData = this;

        Config config = BuildHub.getInstance().getConfiguration();

        if (config.getConfig().contains("yield")) {
            this.yield = config.getConfig().getDouble("yield");
        }
        else {
            this.yield = 1.0;
        }

        if (config.getConfig().contains("multiplier")) {
            this.multiplier = config.getConfig().getDouble("multiplier");
        }
        else {
            this.multiplier = 1.0;
        }
    }

    public static FireballData getFireballData() {
        return fireballData;
    }

    public double getYield() {
        return this.yield;
    }

    public double getMultiplier() {
        return this.multiplier;
    }

    public void save() {
        Config config = BuildHub.getInstance().getConfiguration();

        config.getConfig().set("yield", this.yield);
        config.getConfig().set("multiplier", this.multiplier);
    }
}
