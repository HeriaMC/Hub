package fr.heriamc.hub.menu.cosmetics;

import fr.heriamc.bukkit.menu.pagination.HeriaPaginationMenu;
import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.heriamc.hub.HeriaHub;
import fr.heriamc.hub.cosmetics.CosmeticRarity;
import fr.heriamc.hub.cosmetics.CosmeticSubType;
import fr.heriamc.hub.cosmetics.CosmeticType;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class SubCosmeticsMenu extends HeriaPaginationMenu<CosmeticSubType> {

    private final HeriaHub hub;
    private final CosmeticType type;

    public SubCosmeticsMenu(Player player, HeriaHub hub, CosmeticType type) {
        super(player, "Cosmétiques", 54, false, List.of(20,21,22,23,24,
                29,30,31,32,33), hub.getCosmeticManager().getCosmeticsForType(type));

        this.hub = hub;
        this.type = type;
    }

    @Override
    public void inventory(Inventory inventory) {
        setBorder(inventory, DyeColor.PURPLE.getWoolData());

        this.insertInteractItem(inventory, 48, new ItemBuilder(Material.DARK_OAK_DOOR_ITEM)
                .setName("§c» Retour")
                .onClick(event -> {
                    hub.getBukkitAPI().getMenuManager().open(new CosmeticsMenu(getPlayer(), hub));
                }));
    }

    @Override
    protected ItemBuilder item(CosmeticSubType cosmeticSubType, int i, int i1) {
        CosmeticRarity rarity = cosmeticSubType.getRarity();
        return cosmeticSubType.getRepresentation().clone()
                .setName(rarity.getColor() + cosmeticSubType.getRepresentation().getName())
                .addLore(" ")
                .addLore("§8» §7Rareté: " + rarity.getColoredName())
                .addLore("§8» §7Prix: §6" + rarity.getCoinsPrice() + " ⛃§8/§d" + rarity.getCreditsPrice() + " EUR")
                .addLore(" ")
                .addLore("§6§l❱ §eClic gauche pour équiper")
                .addLore("§6§l❱ §eClic droit pour prévisualiser")
                .onClick(event -> {
            type.equip(hub, getPlayer(), cosmeticSubType.getId());
        });
    }
}