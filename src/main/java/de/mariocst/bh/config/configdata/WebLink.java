package de.mariocst.bh.config.configdata;

import de.mariocst.bh.BuildHub;
import de.mariocst.bh.config.configs.Config;

public class WebLink {
    private String link;

    private static WebLink webLink;

    public WebLink() {
        webLink = this;

        Config config = BuildHub.getInstance().getConfiguration();

        if (config.getConfig().contains("web")) {
            this.link = config.getConfig().getString("web");
        }
        else {
            this.link = "Not in use";
        }
    }

    public static WebLink getWebLink() {
        return webLink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void save() {
        Config config = BuildHub.getInstance().getConfiguration();

        config.getConfig().set("web", this.link);
    }
}
