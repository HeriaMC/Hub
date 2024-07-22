package fr.heriamc.hub.listeners;

import fr.heriamc.hub.HeriaHub;
import fr.heriamc.hub.HubItem;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class HubListeners implements Listener {

    private final HeriaHub hub;

    public HubListeners(HeriaHub hub) {
        this.hub = hub;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        event.setJoinMessage("");

        player.setGameMode(GameMode.ADVENTURE);
        player.setHealth(20.0);
        player.setExp(0);
        player.setLevel(0);
        player.setFoodLevel(20);
        player.getInventory().clear();

        for (HubItem item : HubItem.values()) {
            PlayerInventory inventory = player.getInventory();
            inventory.setItem(item.getSlot(), item.getItemStack());
        }

        this.hub.getScoreboardManager().onLogin(player);

    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        event.setQuitMessage("");
        this.hub.getScoreboardManager().onLogout(event.getPlayer());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        ItemStack stack = event.getItem();

        if(stack == null || !stack.hasItemMeta()){
            return;
        }

        HubItem item = HubItem.fromStack(stack);

        if(item == null){
            return;
        }

        item.getEventTrigger().accept(this.hub, event);
        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event){
        event.setCancelled(true);
    }
}
