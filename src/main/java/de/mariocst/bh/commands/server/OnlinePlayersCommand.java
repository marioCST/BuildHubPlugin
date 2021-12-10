package de.mariocst.bh.commands.server;

import de.mariocst.bh.BuildHub;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class OnlinePlayersCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            if (BuildHub.getInstance().getServer().getOnlinePlayers().isEmpty()) {
                sender.sendMessage(BuildHub.getInstance().getPrefix() + "Niemand ist auf dem Server!");
            }
            else {
                sender.sendMessage(BuildHub.getInstance().getPrefix() + "Online:");

                for (Player online : BuildHub.getInstance().getServer().getOnlinePlayers()) {
                    sender.sendMessage(BuildHub.getInstance().getPrefix() + online.getName());
                }
            }
            return true;
        }

        if (player.hasPermission("mario.onlineplayers") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            int i = 0;

            for (Player online : BuildHub.getInstance().getServer().getOnlinePlayers()) {
                if (online != player) i++;
            }

            if (i == 0) {
                player.sendMessage(BuildHub.getInstance().getPrefix() + "Niemand, außer du, ist auf dem Server!");
                return true;
            }

            player.sendMessage(BuildHub.getInstance().getPrefix() + "Online:");

            for (Player online : BuildHub.getInstance().getServer().getOnlinePlayers()) {
                player.sendMessage(BuildHub.getInstance().getPrefix() + online.getName());
            }
        }
        else {
            player.sendMessage(BuildHub.getInstance().getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        }
        return false;
    }
}
