package fr.heriamc.hub;

import fr.heriamc.hub.menu.GamesMenu;
import fr.heriamc.hub.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.BiConsumer;

public enum HubItem {

    GAMES(0, new ItemBuilder(Material.COMPASS).setName("§6Menu des jeux").build(), (hub, event) -> {
        GamesMenu gamesMenu = new GamesMenu(event.getPlayer());
        hub.getMenuManager().open(gamesMenu);
    }),

    SHOP(1, new ItemBuilder(Material.GOLD_INGOT).setName("§eBoutique").build(), (hub, event) -> {

    }),

    OPTIONS(7, new ItemBuilder(Material.REDSTONE_COMPARATOR).setName("§cParamètres").build(), (hub, event) -> {

    }),

    LOBBY_SELECTOR(8, new ItemBuilder(Material.ENDER_PORTAL_FRAME).setName("§5Sélecteur de lobby").build(), (hub, event) -> {

    })


    ;

    private final int slot;
    private final ItemStack itemStack;
    private final BiConsumer<HeriaHub, PlayerInteractEvent> eventTrigger;

    HubItem(int slot, ItemStack itemStack,  BiConsumer<HeriaHub, PlayerInteractEvent> eventTrigger) {
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

    public  BiConsumer<HeriaHub, PlayerInteractEvent> getEventTrigger() {
        return eventTrigger;
    }

    public static HubItem fromStack(ItemStack stack){
        for (HubItem value : values()) {
            if(value.getItemStack().equals(stack)){
                return value;
            }
        }

        return null;
    }
}
