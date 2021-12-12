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

    private void update() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!DeathData.getDeathData().shouldDisplayScoreboard()) return;

                if (DeathData.getDeathData().animateScoreboard()) {
                    switch (count) {
                        case 0 -> objective.displayName(Component.text("§cT§4o§cd§4e"));
                        case 1 -> objective.displayName(Component.text("§4T§co§4d§ce"));
                        default -> objective.displayName(Component.text("§cTode"));
                    }
                }
                else {
                    objective.displayName(Component.text("§cTode"));
                }

                for (OfflinePlayer offlinePlayer : BuildHub.getInstance().getServer().getOfflinePlayers()) {
                    setScore(DeathData.getDeathData().getDeaths(offlinePlayer), offlinePlayer.getName());
                }

                count++;
                if (count == 2) count = 0;
            }
        }.runTaskTimer(BuildHub.getInstance(), 10, 10);
    }
}
