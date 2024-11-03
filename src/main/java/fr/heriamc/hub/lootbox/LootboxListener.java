package fr.heriamc.hub.lootbox;

import fr.heriamc.hub.HeriaHub;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public record LootboxListener(HeriaHub hub) implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Block clickedBlock = event.getClickedBlock();

        if(clickedBlock == null){
            return;
        }

        Material type = clickedBlock.getType();

        if(type != Material.ENDER_CHEST){
            return;
        }

        event.setCancelled(true);
        hub.getBukkitAPI().getMenuManager().open(new LootboxMainMenu(event.getPlayer(), hub, clickedBlock.getLocation()));
    }
}
