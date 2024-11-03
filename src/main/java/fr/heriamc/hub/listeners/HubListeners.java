package fr.heriamc.hub.listeners;

import fr.heriamc.api.user.HeriaPlayer;
import fr.heriamc.api.user.rank.HeriaRank;
import fr.heriamc.bukkit.mod.ModEvent;
import fr.heriamc.hub.HeriaHub;
import fr.heriamc.hub.HubItem;
import fr.heriamc.hub.cosmetics.CosmeticSubType;
import fr.heriamc.hub.cosmetics.CosmeticType;
import fr.heriamc.hub.data.HubPlayer;
import fr.heriamc.hub.scoreboard.HubScoreboard;
import fr.heriamc.hub.utils.NameTag;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
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
    private final Location spawn = new Location(Bukkit.getWorld("world"), -44.5, 48.5, 0.5, 90, 0);

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
        player.setAllowFlight(false);
        player.teleport(this.spawn);

        for (HubItem item : HubItem.values()) {
            PlayerInventory inventory = player.getInventory();
            inventory.setItem(item.getSlot(), item.getItemStack());
        }

        HeriaPlayer heriaPlayer = hub.getBukkitAPI().getApi().getPlayerManager().get(player.getUniqueId());
        NameTag.setNameTag(player, heriaPlayer.getRank().getPrefix(), " ", heriaPlayer.getRank().getTabPriority());

        if(heriaPlayer.getRank().getPower() >= HeriaRank.VIP.getPower()){
            player.setAllowFlight(true);
        }

        this.hub.getNpcManager().displayNpc(player);

        this.hub.getScoreboardManager().onLogin(player, new HubScoreboard(player, hub));

        HubPlayer loaded = this.hub.getPlayerManager().createOrLoad(player.getUniqueId());
        loaded.setName(player.getName());
        this.hub.getPlayerManager().save(loaded);
        this.hub.getPlayerManager().saveInPersistant(loaded);

        HubPlayer hubPlayer = this.hub.getPlayerManager().get(player.getUniqueId());

        this.hub.getCosmeticManager().equipPlayer(player, hubPlayer);
        this.hub.getCosmeticManager().announceArrival(player, hubPlayer);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        event.setQuitMessage("");

        Player player = event.getPlayer();
        this.hub.getScoreboardManager().onLogout(player);

        HubPlayer hubPlayer = this.hub.getPlayerManager().get(player.getUniqueId());
        this.hub.getPlayerManager().saveInPersistant(hubPlayer);
        this.hub.getPlayerManager().remove(hubPlayer);
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
        if(event.getWhoClicked().isOp()){
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event){
        if(event.getWhoClicked().isOp()){
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onMod(ModEvent e){
        Player player = e.getPlayer();

        if(e.isState()){
            this.hub.getCosmeticManager().unEquipPlayer(player);
            return;
        }

        player.teleport(spawn);
        HubPlayer hubPlayer = this.hub.getPlayerManager().get(player.getUniqueId());

        this.hub.getCosmeticManager().equipPlayer(player, hubPlayer);
        this.hub.getCosmeticManager().announceArrival(player, hubPlayer);

        for (HubItem item : HubItem.values()) {
            PlayerInventory inventory = player.getInventory();
            inventory.setItem(item.getSlot(), item.getItemStack());
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        if(event.getPlayer().isOp()){
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event){
        if(event.getEntity().getType() == EntityType.ARMOR_STAND){
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void unHunger(FoodLevelChangeEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onPhysics(BlockPhysicsEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockForm(BlockFromToEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockForm(BlockFormEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockForm(BlockGrowEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockFade(BlockFadeEvent event){
        event.setCancelled(true);
    }

}
