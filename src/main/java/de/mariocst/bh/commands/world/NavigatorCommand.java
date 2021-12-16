package de.mariocst.bh.commands.world;

import de.mariocst.bh.BuildHub;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

public class NavigatorCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(BuildHub.getInstance().getPrefix() + "Bitte führe den Command InGame aus!");
            return true;
        }

        if (player.hasPermission("mario.nav") || player.hasPermission("mario.*") || player.hasPermission("*") || player.isOp()) {
            player.closeInventory();

            Inventory inventory = BuildHub.getInstance().getServer().createInventory(player, 9, Component.text("§cNavigator"));

            ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta playerHeadMeta = (SkullMeta) playerHead.getItemMeta();

            ItemStack spawn = new ItemStack(Material.GRASS_BLOCK, 1);
            ItemMeta spawnMeta = spawn.getItemMeta();

            ItemStack nether = new ItemStack(Material.NETHERRACK, 1);
            ItemMeta netherMeta = nether.getItemMeta();

            ItemStack end = new ItemStack(Material.END_STONE, 1);
            ItemMeta endMeta = end.getItemMeta();

            ItemStack close = new ItemStack(Material.BARRIER, 1);
            ItemMeta closeMeta = close.getItemMeta();

            ItemStack redGlassPane = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
            ItemMeta redGlassPaneMeta = redGlassPane.getItemMeta();

            playerHeadMeta.setPlayerProfile(player.getPlayerProfile());
            playerHeadMeta.displayName(Component.text("§bBase"));
            playerHead.setItemMeta(playerHeadMeta);

            spawnMeta.displayName(Component.text("§2Spawn"));
            spawn.setItemMeta(spawnMeta);

            netherMeta.displayName(Component.text("§cNether"));
            nether.setItemMeta(netherMeta);

            endMeta.displayName(Component.text("§eEnd"));
            end.setItemMeta(endMeta);

            closeMeta.displayName(Component.text("§cSchließen"));
            close.setItemMeta(closeMeta);

            redGlassPaneMeta.displayName(Component.text(""));
            redGlassPane.setItemMeta(redGlassPaneMeta);

            inventory.setItem(0, playerHead);
            inventory.setItem(2, spawn);
            inventory.setItem(4, nether);
            inventory.setItem(6, end);
            inventory.setItem(8, close);

            for (int i = 0; i <= 8; i++) {
                if (inventory.getItem(i) == null) inventory.setItem(i, redGlassPane);
            }

            player.openInventory(inventory);
        }
        else {
            player.sendMessage(BuildHub.getInstance().getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        }
        return false;
    }
}
