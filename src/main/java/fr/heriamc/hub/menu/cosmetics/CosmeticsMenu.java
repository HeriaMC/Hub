package fr.heriamc.hub.menu.cosmetics;

import fr.heriamc.bukkit.menu.HeriaMenu;
import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.heriamc.hub.HeriaHub;
import fr.heriamc.hub.cosmetics.CosmeticRarity;
import fr.heriamc.hub.cosmetics.CosmeticType;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class CosmeticsMenu extends HeriaMenu {

    private final HeriaHub hub;

    public CosmeticsMenu(Player player, HeriaHub hub) {
        super(player, "Cosmétiques", 54, false);
        this.hub = hub;
    }

    @Override
    public void contents(Inventory inventory) {
        this.setBorder(inventory, DyeColor.PURPLE.getWoolData());

        inventory.setItem(4, new ItemBuilder(Material.ENDER_CHEST).setName("§f» §5Cosmétiques").build());


        for (CosmeticType value : CosmeticType.values()) {
            this.insertInteractItem(inventory, value.getSlot(), value.getRepresentation()
                    .onClick(event -> {
                        this.hub.getBukkitAPI().getMenuManager().open(new SubCosmeticsMenu(getPlayer(), hub, value));
                    }));
        }

        this.insertInteractItem(inventory, 49, new ItemBuilder(Material.DARK_OAK_DOOR_ITEM).setName("§c» Fermer le menu")
                .onClick(event -> {
                    getPlayer().closeInventory();
                }));
    }
}
