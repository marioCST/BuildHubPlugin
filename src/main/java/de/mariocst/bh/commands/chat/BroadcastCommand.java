package de.mariocst.bh.commands.chat;

import de.mariocst.bh.BuildHub;
import net.kyori.adventure.text.Component;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BroadcastCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            if (args.length >= 1) {
                StringBuilder msg = new StringBuilder();
                for (String arg : args) {
                    msg.append(arg).append(" ");
                }

                BuildHub.getInstance().getServer().broadcast(Component.text(BuildHub.getInstance().getPrefix()));
                BuildHub.getInstance().getServer().broadcast(Component.text(BuildHub.getInstance().getPrefix() + msg.toString().replaceAll("&", "§")));
                BuildHub.getInstance().getServer().broadcast(Component.text(BuildHub.getInstance().getPrefix()));
            }
            else {
                sender.sendMessage("§cUsage: §e/broadcast <Message>");
            }
            return false;
        }

        if (player.hasPermission("mario.broadcast") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            if (args.length >= 1) {
                StringBuilder msg = new StringBuilder();
                for (String arg : args) {
                    msg.append(arg).append(" ");
                }

                BuildHub.getInstance().getServer().broadcast(Component.text(BuildHub.getInstance().getPrefix()));
                BuildHub.getInstance().getServer().broadcast(Component.text(BuildHub.getInstance().getPrefix() + msg.toString().replaceAll("&", "§")));
                BuildHub.getInstance().getServer().broadcast(Component.text(BuildHub.getInstance().getPrefix()));
            }
            else {
                player.sendMessage(BuildHub.getInstance().getPrefix() + "§cUsage: §e/broadcast <Message>");
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
