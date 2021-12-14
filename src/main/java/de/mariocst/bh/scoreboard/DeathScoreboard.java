package de.mariocst.bh.scoreboard;

import de.mariocst.bh.BuildHub;
import de.mariocst.bh.config.configdata.DeathData;
import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Objects;

public class DeathScoreboard {
    private final Objective objective;
    private int count;

    public DeathScoreboard(Component displayName, Scoreboard scoreboard) {
        if (scoreboard.getObjective("deaths") != null) {
            Objects.requireNonNull(scoreboard.getObjective("deaths")).unregister();
        }

        objective = scoreboard.registerNewObjective("deaths", "dummy", displayName);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        update();
    }

    public void setScore(int score, String playerName) {
        objective.getScore(playerName).setScore(score);
    }

    public void removeScore(String playerName) {
        objective.getScore(playerName).resetScore();
    }

    private void update() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!DeathData.getDeathData().shouldDisplayScoreboard()) return;

                if (DeathData.getDeathData().animateScoreboard()) {
                    switch (count) {
                        case 0 -> objective.displayName(Component.text("§cT§do§4d§de"));
                        case 1 -> objective.displayName(Component.text("§dT§co§dd§4e"));
                        default -> objective.displayName(Component.text("§cTode"));
                    }
                }
                else {
                    objective.displayName(Component.text("§cTode"));
                }

                for (OfflinePlayer offlinePlayer : BuildHub.getInstance().getServer().getOfflinePlayers()) {
                    if (DeathData.getDeathData().animateScoreboard()) {
                        String[] name = Objects.requireNonNull(offlinePlayer.getName()).split("");
                        String[] name2 = Objects.requireNonNull(offlinePlayer.getName()).split("");

                        switch (count) {
                            case 0 -> {
                                for (int i = 0; i < name.length; i++) {
                                    if (i % 2 == 0) {
                                        name[i] = "§a" + name[i];
                                        name2[i] = "§b" + name2[i];
                                    }
                                    else {
                                        name[i] = "§b" + name[i];
                                        name2[i] = "§a" + name2[i];
                                    }
                                }

                                StringBuilder disName = new StringBuilder();
                                StringBuilder disName2 = new StringBuilder();

                                for (String s : name) {
                                    disName.append(s);
                                }

                                for (String s : name2) {
                                    disName2.append(s);
                                }

                                removeScore(disName2.toString());
                                setScore(DeathData.getDeathData().getDeaths(offlinePlayer), disName.toString());
                            }
                            case 1 -> {
                                for (int i = 0; i < name.length; i++) {
                                    if (i % 2 == 0) {
                                        name[i] = "§b" + name[i];
                                        name2[i] = "§a" + name2[i];
                                    }
                                    else {
                                        name[i] = "§a" + name[i];
                                        name2[i] = "§b" + name2[i];
                                    }
                                }

                                StringBuilder disName = new StringBuilder();
                                StringBuilder disName2 = new StringBuilder();

                                for (String s : name) {
                                    disName.append(s);
                                }

                                for (String s : name2) {
                                    disName2.append(s);
                                }

                                removeScore(disName2.toString());
                                setScore(DeathData.getDeathData().getDeaths(offlinePlayer), disName.toString());
                            }
                        }
                    }
                    else {
                        setScore(DeathData.getDeathData().getDeaths(offlinePlayer), offlinePlayer.getName());
                    }
                }

                count++;
                if (count == 2) count = 0;
            }
        }.runTaskTimer(BuildHub.getInstance(), 10, 10);
    }
}
