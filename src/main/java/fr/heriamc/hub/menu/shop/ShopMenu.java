package fr.heriamc.hub.menu.shop;

import fr.heriamc.bukkit.menu.HeriaMenu;
import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.heriamc.hub.HeriaHub;
import fr.heriamc.hub.menu.shop.host.ShopHostMenu;
import fr.heriamc.hub.menu.shop.rank.ShopRanksMenu;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ShopMenu extends HeriaMenu {

    private final HeriaHub hub;
    private final HeriaMenu before;

    public ShopMenu(Player player, HeriaHub hub, HeriaMenu before) {
        super(player, "Boutique", 54, false);
        this.hub = hub;
        this.before = before;
    }

    @Override
    public void contents(Inventory inventory) {
        this.setBorder(inventory, DyeColor.YELLOW.getWoolData());

        inventory.setItem(4, new ItemBuilder(Material.GOLD_INGOT).setName("§f» §eBoutique").build());

        this.insertInteractItem(inventory, 22, new ItemBuilder(Material.ENCHANTMENT_TABLE).setName("§f» §6Grades")
                .onClick(event -> {
                    hub.getBukkitAPI().getMenuManager().open(new ShopRanksMenu(getPlayer(), hub, before));
                }));

        this.insertInteractItem(inventory, 31, new ItemBuilder(Material.NETHER_STAR).setName("§f» §dHost")
                .onClick(event -> {
                    hub.getBukkitAPI().getMenuManager().open(new ShopHostMenu(getPlayer(), hub));
                }));

        this.insertInteractItem(inventory, 30, new ItemBuilder(Material.CHEST).setName("§f» §eLootboxes")
                .onClick(event -> {

                }));

        this.insertInteractItem(inventory, 32, new ItemBuilder(Material.HOPPER).setName("§f» §9Pack de cosmétiques")
                .onClick(event -> {

                }));

        this.insertInteractItem(inventory, 49, new ItemBuilder(Material.DARK_OAK_DOOR_ITEM).setName("§c» Fermer le menu")
                .onClick(event -> {
                    if(before != null){
                        this.hub.getBukkitAPI().getMenuManager().open(before);
                        return;
                    }
                    getPlayer().closeInventory();
                }));
    }
}
