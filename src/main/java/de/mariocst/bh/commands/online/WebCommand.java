package de.mariocst.bh.commands.online;

import de.mariocst.bh.BuildHub;
import de.mariocst.bh.config.configdata.WebLink;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WebCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            BuildHub.getInstance().log("Unser Web: " + WebLink.getWebLink().getLink());
            return false;
        }

        if (player.hasPermission("mario.web") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            player.sendMessage(BuildHub.getInstance().getPrefix() + "Unser Web: §a" + WebLink.getWebLink().getLink());
        }
        else {
            player.sendMessage(BuildHub.getInstance().getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        }
        return false;
    }
}
