package fr.heriamc.hub;

import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.heriamc.hub.menu.cosmetics.CosmeticsMenu;
import fr.heriamc.hub.menu.games.BasicGamesMenu;
import fr.heriamc.hub.menu.games.GamesMenu;
import fr.heriamc.hub.menu.lobby.LobbyMenu;
import fr.heriamc.hub.menu.options.OptionsMenu;
import fr.heriamc.hub.menu.shop.ShopMenu;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.BiConsumer;

public enum HubItem {

    GAMES(0, new ItemBuilder(Material.COMPASS).setName("§6Menu des jeux §8・§7Clic droit").build(), (hub, event) -> {
        GamesMenu gamesMenu = new BasicGamesMenu(event.getPlayer(), hub, null);
        hub.getBukkitAPI().getMenuManager().open(gamesMenu);
    }),

    SHOP(1, new ItemBuilder(Material.GOLD_INGOT).setName("§eBoutique §8・§7Clic droit").build(), (hub, event) -> {
        hub.getBukkitAPI().getMenuManager().open(new ShopMenu(event.getPlayer(), hub, null));
    }),

    COSMETICS(4, new ItemBuilder(Material.ENDER_CHEST).setName("§5Cosmétiques §8・§7Clic droit").build(), ((hub, event) -> {
        hub.getBukkitAPI().getMenuManager().open(new CosmeticsMenu(event.getPlayer(), hub));
    })),

    OPTIONS(7, new ItemBuilder(Material.REDSTONE_COMPARATOR).setName("§cParamètres §8・§7Clic droit").build(), (hub, event) -> {
        hub.getBukkitAPI().getMenuManager().open(new OptionsMenu(event.getPlayer(), hub));
    }),

    LOBBY_SELECTOR(8, new ItemBuilder(Material.ENDER_PORTAL_FRAME).setName("§dSélecteur de lobby §8・§7Clic droit").build(), (hub, event) -> {
        hub.getBukkitAPI().getMenuManager().open(new LobbyMenu(event.getPlayer(), hub, null));
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
