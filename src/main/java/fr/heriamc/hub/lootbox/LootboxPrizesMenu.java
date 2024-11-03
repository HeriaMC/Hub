package fr.heriamc.hub.lootbox;

import fr.heriamc.bukkit.HeriaBukkit;
import fr.heriamc.bukkit.menu.HeriaMenu;
import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.heriamc.hub.HeriaHub;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class LootboxPrizesMenu extends HeriaMenu {

    private final HeriaHub hub;
    private final Location boxLocation;

    public LootboxPrizesMenu(Player player, HeriaHub hub, Location boxLocation) {
        super(player, "Récompenses", 27, false);
        this.hub = hub;
        this.boxLocation = boxLocation;
    }

    @Override
    public void contents(Inventory inventory) {
        int index = 0;
        for (LootboxPrize value : LootboxPrize.values()) {
            inventory.setItem(index, value.getItem()
                    .clone()
                    .addLore(" ")
                    .addLore("§7Probabilité: §d" + value.getPercent() +"%")
                    .addLore(" ")
                    .build());

            index++;
        }

        this.insertInteractItem(inventory, 22, new ItemBuilder(Material.DARK_OAK_DOOR_ITEM)
                .setName("§c» Retour")
                .onClick(event -> {
                    hub.getBukkitAPI().getMenuManager().open(new LootboxMainMenu(getPlayer(), hub, boxLocation));
                }));
    }
}
