package de.mariocst.bh.commands.setter;

import de.mariocst.bh.BuildHub;
import de.mariocst.bh.config.configdata.Prefix;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetPrefixCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        StringBuilder msg = new StringBuilder();

        if (!(sender instanceof Player player)) {
            if (args.length >= 1) {
                for (String arg : args) {
                    msg.append(arg).append(" ");
                }

                sender.sendMessage(BuildHub.getInstance().getPrefix() + "Der Prefix ist nun: " + msg.toString().replaceAll("&", "§"));
                BuildHub.getInstance().setPrefix(msg.toString().replaceAll("&", "§"));
                Prefix.getPrefixClass().setPrefix(msg.toString().replaceAll("&", "§"));
                BuildHub.getInstance().saveConfigs();
            }
            else {
                sender.sendMessage(BuildHub.getInstance().getPrefix() + "/setprefix <Prefix>");
            }
            return false;
        }

        if (player.hasPermission("mario.prefix") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            if (args.length >= 1) {
                for (String arg : args) {
                    msg.append(arg).append(" ");
                }

                player.sendMessage(BuildHub.getInstance().getPrefix() + "Der Prefix ist nun: " + msg.toString().replaceAll("&", "§"));
                BuildHub.getInstance().setPrefix(msg.toString().replaceAll("&", "§"));
                Prefix.getPrefixClass().setPrefix(msg.toString().replaceAll("&", "§"));
                BuildHub.getInstance().saveConfigs();
            }
            else {
                player.sendMessage(BuildHub.getInstance().getPrefix() + "/setprefix <Prefix>");
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
