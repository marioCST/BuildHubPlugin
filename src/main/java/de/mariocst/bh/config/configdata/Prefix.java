package de.mariocst.bh.config.configdata;

import de.mariocst.bh.BuildHub;
import de.mariocst.bh.config.configs.Config;

public class Prefix {
    private String prefix;

    private static Prefix prefixClass;

    public Prefix() {
        prefixClass = this;

        Config config = BuildHub.getInstance().getConfiguration();

        if (config.getConfig().contains("prefix")) {
            this.prefix = config.getConfig().getString("prefix");
        }
        else {
            this.prefix = "§8[§1Build§2Hub§8] §f";
        }

        BuildHub.getInstance().setPrefix(this.prefix);
    }

    public static Prefix getPrefixClass() {
        return prefixClass;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void save() {
        Config config = BuildHub.getInstance().getConfiguration();

        config.getConfig().set("prefix", this.prefix);
    }
}
