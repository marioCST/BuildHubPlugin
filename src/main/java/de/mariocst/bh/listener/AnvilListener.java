package de.mariocst.bh.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AnvilListener implements Listener {
    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        if (event.getInventory().getFirstItem() == null || event.getInventory().getSecondItem() == null) return;
        if (event.getInventory().getFirstItem().getType() != event.getInventory().getSecondItem().getType()) return;

        ItemStack stack = new ItemStack(event.getInventory().getFirstItem().getType(), 1);
        ItemMeta meta = stack.getItemMeta();

        event.getInventory().getFirstItem().getEnchantments().keySet().forEach(e -> {
            if (event.getInventory().getSecondItem().getEnchantments().containsKey(e)) {
                if (event.getInventory().getFirstItem().getEnchantmentLevel(e) == event.getInventory().getSecondItem().getEnchantmentLevel(e)) {
                    if (event.getInventory().getFirstItem().getEnchantmentLevel(e) <= 10) {
                        meta.addEnchant(e, event.getInventory().getFirstItem().getEnchantmentLevel(e) + 1, true);
                    }
                }
            }
            else {
                meta.addEnchant(e, event.getInventory().getFirstItem().getEnchantmentLevel(e), true);
            }
        });

        event.getInventory().getSecondItem().getEnchantments().keySet().forEach(e -> {
            if (!meta.getEnchants().containsKey(e)) {
                meta.addEnchant(e, event.getInventory().getSecondItem().getEnchantmentLevel(e), true);
            }
        });

        stack.setItemMeta(meta);

        event.setResult(stack);
    }
}
