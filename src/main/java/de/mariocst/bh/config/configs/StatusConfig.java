package de.mariocst.bh.config.configs;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class StatusConfig {
    private final File file;
    private final YamlConfiguration config;

    public StatusConfig() {
        File dir = new File("./plugins/BuildHub");

        this.file = new File(dir, "statusConfig.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ignored) { }
        }

        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public File getFile() {
        return file;
    }

    public YamlConfiguration getConfig() {
        return config;
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
