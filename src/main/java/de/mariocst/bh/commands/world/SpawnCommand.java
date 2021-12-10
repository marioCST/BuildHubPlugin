package de.mariocst.bh.commands.world;

import de.mariocst.bh.BuildHub;
import de.mariocst.bh.config.configdata.Spawn;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(BuildHub.getInstance().getPrefix() + "Bitte führe den Command InGame aus!");
            return true;
        }

        if (player.hasPermission("mario.spawn") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            try {
                player.teleport(Spawn.getSpawn().getSpawnLocation());
                player.sendMessage(BuildHub.getInstance().getPrefix() + "Du wurdest zum Spawn teleportiert!");
            }
            catch (NullPointerException e) {
                player.sendMessage(BuildHub.getInstance().getPrefix() + "Irgendetwas ist schief gelaufen!");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
            }
        }
        else {
            player.sendMessage(BuildHub.getInstance().getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        }
        return false;
    }
}
