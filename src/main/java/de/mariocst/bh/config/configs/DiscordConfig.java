package de.mariocst.bh.config.configs;

import de.mariocst.bh.BuildHub;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class DiscordConfig {
    private final File file;
    private final YamlConfiguration config;

    public DiscordConfig() {
        File dir = new File("./plugins/BuildHub");

        this.file = new File(dir, "discord.yml");

        if (!file.exists()) {
            try (InputStream in = BuildHub.getInstance().getResource("discord.yml")) {
                assert in != null;
                Files.copy(in, file.toPath());
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
