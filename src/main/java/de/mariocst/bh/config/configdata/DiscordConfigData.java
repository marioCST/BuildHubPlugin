package de.mariocst.bh.config.configdata;

import de.mariocst.bh.BuildHub;
import de.mariocst.bh.config.configs.DiscordConfig;

import java.util.Objects;

public class DiscordConfigData {
    private String url;
    private String description;
    private String title;

    private static DiscordConfigData discordConfigData;

    public DiscordConfigData() {
        discordConfigData = this;

        DiscordConfig config = BuildHub.getInstance().getDiscordConfig();

        if (config.getConfig().contains("webhook")) {
            this.url = Objects.requireNonNull(config.getConfig().getString("webhook")).replaceAll("'", "");
        }
        else {
            this.url = "";
        }

        if (config.getConfig().contains("description")) {
            this.description = config.getConfig().getString("description");
        }
        else {
            this.description = "";
        }

        if (config.getConfig().contains("title")) {
            this.title = config.getConfig().getString("title");
        }
        else {
            this.title = "";
        }
    }

    public static DiscordConfigData getDiscordConfigData() {
        return discordConfigData;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void save() {
        DiscordConfig config = BuildHub.getInstance().getDiscordConfig();

        config.getConfig().set("webhook", this.url);
        config.getConfig().set("description", this.description);
        config.getConfig().set("title", this.title);
    }
}
