package de.mariocst.bh.config.configdata;

import de.mariocst.bh.BuildHub;
import de.mariocst.bh.config.configs.DiscordConfig;

import java.util.Objects;

public class DiscordConfigData {
    private final String reportUrl;
    private final String serverInfoUrl;
    private final String description;
    private final String title;

    private static DiscordConfigData discordConfigData;

    public DiscordConfigData() {
        discordConfigData = this;

        DiscordConfig config = BuildHub.getInstance().getDiscordConfig();

        if (config.getConfig().contains("reportUrl")) {
            this.reportUrl = Objects.requireNonNull(config.getConfig().getString("reportUrl")).replaceAll("'", "");
        }
        else {
            this.reportUrl = "";
        }

        if (config.getConfig().contains("serverInfoUrl")) {
            this.serverInfoUrl = Objects.requireNonNull(config.getConfig().getString("serverInfoUrl")).replaceAll("'", "");
        }
        else {
            this.serverInfoUrl = "";
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

    public String getReportUrl() {
        return reportUrl;
    }

    public String getServerInfoUrl() {
        return serverInfoUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void save() {
        DiscordConfig config = BuildHub.getInstance().getDiscordConfig();

        config.getConfig().set("reportUrl", this.reportUrl);
        config.getConfig().set("serverInfoUrl", this.serverInfoUrl);
        config.getConfig().set("description", this.description);
        config.getConfig().set("title", this.title);
    }
}
