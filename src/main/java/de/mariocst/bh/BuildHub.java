package de.mariocst.bh;

import de.mariocst.bh.commands.chat.*;
import de.mariocst.bh.commands.invsee.*;
import de.mariocst.bh.commands.others.*;
import de.mariocst.bh.commands.player.*;
import de.mariocst.bh.commands.server.*;
import de.mariocst.bh.commands.setter.*;
import de.mariocst.bh.commands.world.*;
import de.mariocst.bh.config.configdata.*;
import de.mariocst.bh.config.configs.*;
import de.mariocst.bh.listener.*;
import de.mariocst.bh.scoreboard.DeathScoreboard;
import de.mariocst.bh.webhook.*;
import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public final class BuildHub extends JavaPlugin {
    private static BuildHub instance;

    private String prefix;

    private BaseConfig baseConfig;
    private Config config;
    private CoordsConfig coordsConfig;
    private DeathConfig deathConfig;
    private DiscordConfig discordConfig;
    private StatusConfig statusConfig;

    private BaseData baseData;
    private CoordsData coordsData;
    private DeathData deathData;
    private DiscordConfigData discordConfigData;
    private DiscordLink discordLink;
    private FireballData fireballData;
    private Prefix prefixData;
    private Spawn spawn;
    private StatusData statusData;
    private WebLink webLink;

    @Override
    public void onLoad() {
        instance = this;

        this.loadConfigs();
    }

    @Override
    public void onEnable() {
        try {
            this.register();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            this.warn("Error beim Initialisieren der Commands! Deaktiviere Plugin...");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.listenerRegistration();

        this.registerScoreboards();

        if (this.discordConfigData.getUrl().equals("")) this.log("Es ist kein Webhook Link angegeben!");

        this.log("BuildHub Plugin geladen!");
    }

    @Override
    public void onDisable() {
        this.saveConfigs();

        this.log("BuildHub Plugin entladen!");
    }

    public void saveConfigs() {
        this.baseData.save();
        this.coordsData.save();
        this.deathData.save();
        this.discordConfigData.save();
        this.discordLink.save();
        this.fireballData.save();
        this.prefixData.save();
        this.spawn.save();
        this.statusData.save();
        this.webLink.save();

        this.baseConfig.save();
        this.config.save();
        this.coordsConfig.save();
        this.deathConfig.save();
        this.discordConfig.save();
        this.statusConfig.save();
    }

    public void loadConfigs() {
        this.baseConfig = new BaseConfig();
        this.config = new Config();
        this.coordsConfig = new CoordsConfig();
        this.deathConfig = new DeathConfig();
        this.discordConfig = new DiscordConfig();
        this.statusConfig = new StatusConfig();

        this.baseData = new BaseData();
        this.coordsData = new CoordsData();
        this.deathData = new DeathData();
        this.discordConfigData = new DiscordConfigData();
        this.discordLink = new DiscordLink();
        this.fireballData = new FireballData();
        this.prefixData = new Prefix();
        this.spawn = new Spawn();
        this.statusData = new StatusData();
        this.webLink = new WebLink();
    }

    private void registerScoreboards() {
        new DeathScoreboard(Component.text("§cTode"), this.getServer().getScoreboardManager().getMainScoreboard());
    }

    private void listenerRegistration() {
        PluginManager pluginManager = this.getServer().getPluginManager();

        pluginManager.registerEvents(new BlockBreakListener(), this);
        pluginManager.registerEvents(new DeathListener(), this);
        pluginManager.registerEvents(new FireballListener(), this);
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new ProtectListener(), this);
        pluginManager.registerEvents(new ServerListPingListener(), this);
    }

    private void register() throws NullPointerException {
        // Chat
        Objects.requireNonNull(this.getCommand("broadcast")).setExecutor(new BroadcastCommand());
        Objects.requireNonNull(this.getCommand("chatclear")).setExecutor(new ChatClearCommand());
        Objects.requireNonNull(this.getCommand("colorcodes")).setExecutor(new ColorCodesCommand());

        // Invsee
        Objects.requireNonNull(this.getCommand("enderinvsee")).setExecutor(new EnderInvseeCommand());
        Objects.requireNonNull(this.getCommand("invsee")).setExecutor(new InvseeCommand());

        // Others
        Objects.requireNonNull(this.getCommand("discord")).setExecutor(new DiscordCommand());
        Objects.requireNonNull(this.getCommand("web")).setExecutor(new WebCommand());

        // Player
        Objects.requireNonNull(this.getCommand("death")).setExecutor(new DeathCommand());
        Objects.requireNonNull(this.getCommand("death")).setTabCompleter(new DeathCommand());
        Objects.requireNonNull(this.getCommand("feed")).setExecutor(new FeedCommand());
        Objects.requireNonNull(this.getCommand("feed")).setTabCompleter(new FeedCommand());
        Objects.requireNonNull(this.getCommand("gm")).setExecutor(new GMCommand());
        Objects.requireNonNull(this.getCommand("gm")).setTabCompleter(new GMCommand());
        Objects.requireNonNull(this.getCommand("heal")).setExecutor(new HealCommand());
        Objects.requireNonNull(this.getCommand("heal")).setTabCompleter(new HealCommand());
        Objects.requireNonNull(this.getCommand("scoreboard")).setExecutor(new ScoreboardCommand());
        Objects.requireNonNull(this.getCommand("scoreboard")).setTabCompleter(new ScoreboardCommand());
        Objects.requireNonNull(this.getCommand("status")).setExecutor(new StatusCommand());
        Objects.requireNonNull(this.getCommand("status")).setTabCompleter(new StatusCommand());
        Objects.requireNonNull(this.getCommand("sudo")).setExecutor(new SudoCommand());
        Objects.requireNonNull(this.getCommand("sudo")).setTabCompleter(new SudoCommand());

        // Server
        Objects.requireNonNull(this.getCommand("base")).setExecutor(new BaseCommand());
        Objects.requireNonNull(this.getCommand("base")).setTabCompleter(new BaseCommand());
        Objects.requireNonNull(this.getCommand("config")).setExecutor(new ConfigCommand());
        Objects.requireNonNull(this.getCommand("config")).setTabCompleter(new ConfigCommand());
        Objects.requireNonNull(this.getCommand("coords")).setExecutor(new CoordsCommand());
        Objects.requireNonNull(this.getCommand("coords")).setTabCompleter(new CoordsCommand());
        Objects.requireNonNull(this.getCommand("kickall")).setExecutor(new KickAllCommand());
        Objects.requireNonNull(this.getCommand("onlineplayers")).setExecutor(new OnlinePlayersCommand());
        Objects.requireNonNull(this.getCommand("report")).setExecutor(new ReportCommand());
        Objects.requireNonNull(this.getCommand("report")).setTabCompleter(new ReportCommand());

        // Setter
        Objects.requireNonNull(this.getCommand("setlink")).setExecutor(new SetLinkCommand());
        Objects.requireNonNull(this.getCommand("setlink")).setTabCompleter(new SetLinkCommand());
        Objects.requireNonNull(this.getCommand("setprefix")).setExecutor(new SetPrefixCommand());

        // World
        Objects.requireNonNull(this.getCommand("day")).setExecutor(new DayCommand());
        Objects.requireNonNull(this.getCommand("forceloadchunk")).setExecutor(new ForceLoadChunkCommand());
        Objects.requireNonNull(this.getCommand("forceloadchunk")).setTabCompleter(new ForceLoadChunkCommand());
        Objects.requireNonNull(this.getCommand("getentities")).setExecutor(new GetEntitesCommand());
        Objects.requireNonNull(this.getCommand("killradius")).setExecutor(new KillRadiusCommand());
        Objects.requireNonNull(this.getCommand("night")).setExecutor(new NightCommand());
        Objects.requireNonNull(this.getCommand("setspawn")).setExecutor(new SetSpawnCommand());
        Objects.requireNonNull(this.getCommand("spawn")).setExecutor(new SpawnCommand());
    }

    public void log(String text) {
        this.getServer().getLogger().info(this.getPrefix() + text);
    }

    public void warn(String text) {
        this.getServer().getLogger().warning(this.getPrefix() + text);
    }

    public void sendReport(Player player, Player reported, String reason) throws IOException {
        DiscordWebhook webhook = new DiscordWebhook(discordConfigData.getUrl());

        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setTitle(discordConfigData.getTitle() + " Report")
                .setDescription(discordConfigData.getDescription())
                .addField("Spieler", player.getName(), false)
                .addField("Reported", reported.getName(), false)
                .addField("Grund", reason, false)
                .setColor(Color.RED));

        try {
            webhook.execute();
        }
        catch (IOException e) {
            this.getServer().getLogger().severe(e.getLocalizedMessage());
        }
    }

    public void sendReport(Player player, OfflinePlayer reported, String reason) throws IOException {
        DiscordWebhook webhook = new DiscordWebhook(discordConfigData.getUrl());

        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setTitle(discordConfigData.getTitle() + " Report")
                .setDescription(discordConfigData.getDescription())
                .addField("Spieler", player.getName(), false)
                .addField("Reported", reported.getName(), false)
                .addField("Grund", reason, false)
                .setColor(Color.RED));

        try {
            webhook.execute();
        }
        catch (IOException e) {
            this.getServer().getLogger().severe(e.getLocalizedMessage());
        }
    }

    public BaseConfig getBaseConfig() {
        return baseConfig;
    }

    public Config getConfiguration() {
        return config;
    }

    public CoordsConfig getCoordsConfig() {
        return coordsConfig;
    }

    public DeathConfig getDeathConfig() {
        return deathConfig;
    }

    public DiscordConfig getDiscordConfig() {
        return discordConfig;
    }

    public StatusConfig getStatusConfig() {
        return statusConfig;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public static BuildHub getInstance() {
        return instance;
    }
}
