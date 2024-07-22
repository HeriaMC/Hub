package fr.heriamc.core.utils.ui;

import fr.heriamc.api.HeriaAPI;
import fr.heriamc.core.HeriaCore;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class HeriaMenuManager implements Listener {

    private static final @Getter Map<Player, HeriaMenu> inventory = new HashMap<>();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getInventory();
        ItemStack current = event.getCurrentItem();

        if (event.getCurrentItem() == null) return;

        if (inventory.containsKey(player)) {
            HeriaMenu menu = inventory.get(player);
            if (inv.getName().equals(menu.getInventory().getName())) {
                if(menu.isCancelled())
                    event.setCancelled(true);

                menu.onClick(current, event.getSlot(), event.getClick());
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClose(InventoryCloseEvent event) {
        inventory.remove((Player) event.getPlayer());
    }

    public static void init() {
        Bukkit.getPluginManager().registerEvents(new HeriaMenuManager(), HeriaCore.get().getPlugin());
        Bukkit.getScheduler().runTaskTimer(HeriaCore.get().getPlugin(), new HeriaMenuRunnable(), 0L, 20L);
    }

    public static void open(HeriaMenu menu) {

        Inventory inv = menu.getInventory();
        menu.contents(inv);

        new BukkitRunnable() {

            @Override
            public void run() {
                menu.getPlayer().openInventory(inv);
                inventory.remove(menu.getPlayer());
                inventory.put(menu.getPlayer(), menu);
            }

        }.runTaskLater(HeriaAPI.get().getPlugin(), 1);

    }

}