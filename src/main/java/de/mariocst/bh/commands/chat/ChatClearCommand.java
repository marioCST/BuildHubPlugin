package de.mariocst.bh.commands.chat;

import de.mariocst.bh.BuildHub;
import net.kyori.adventure.text.Component;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ChatClearCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            for (Player cleared : BuildHub.getInstance().getServer().getOnlinePlayers()) {
                if (!cleared.hasPermission("mario.chatclear.bypass") && !cleared.hasPermission("mario.*") && !cleared.hasPermission("*") && !cleared.isOp()) {
                    for (int i = 0; i <= 200; i++) {
                        cleared.sendMessage(Component.text("   "));
                    }
                }
            }

            BuildHub.getInstance().getServer().broadcast(Component.text(BuildHub.getInstance().getPrefix() + "Die Konsole hat den Chat gecleart!"));
            return false;
        }

        if (player.hasPermission("mario.chatclear") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            for (Player cleared : BuildHub.getInstance().getServer().getOnlinePlayers()) {
                if (!cleared.hasPermission("mario.chatclear.bypass") && !cleared.hasPermission("mario.*") && !cleared.hasPermission("*") && !cleared.isOp()) {
                    for (int i = 0; i <= 200; i++) {
                        cleared.sendMessage(Component.text("   "));
                    }
                }
            }

            BuildHub.getInstance().getServer().broadcast(Component.text(BuildHub.getInstance().getPrefix() + player.getName() + " hat den Chat gecleart!"));
        }
        else {
            player.sendMessage(BuildHub.getInstance().getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        }
        return false;
    }
}
