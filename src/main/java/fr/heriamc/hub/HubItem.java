package fr.heriamc.hub;

import fr.heriamc.hub.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public enum HubItems {

    GAMES(0, new ItemBuilder(Material.COMPASS).setName("§6Menu des jeux").build(), (event -> {
        Player player = event.getPlayer();
        // open main menu
    })),

    SHOP(1, new ItemBuilder(Material.GOLD_INGOT).setName("§eBoutique").build(), event -> {
        Player player = event.getPlayer();
        // open shop menu
    }),

    OPTIONS(7, new ItemBuilder(Material.REDSTONE_COMPARATOR).setName("§cParamètres").build(), event -> {
        Player player = event.getPlayer();
        // open options menu
    }),

    LOBBY_SELECTOR(8, new ItemBuilder(Material.ENDER_PORTAL_FRAME).setName("§5Sélecteur de lobby").build(), event -> {
        Player player = event.getPlayer();
        // open lobby selector menu
    })


    ;

    private final int slot;
    private final ItemStack itemStack;
    private final Consumer<PlayerInteractEvent> eventTrigger;

    HubItems(int slot, ItemStack itemStack, Consumer<PlayerInteractEvent> eventTrigger) {
        this.slot = slot;
        this.itemStack = itemStack;
        this.eventTrigger = eventTrigger;
    }

    public int getSlot() {
        return slot;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public Consumer<PlayerInteractEvent> getEventTrigger() {
        return eventTrigger;
    }
}
