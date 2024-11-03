package fr.heriamc.hub.lootbox;

import fr.heriamc.bukkit.HeriaBukkit;
import fr.heriamc.bukkit.menu.HeriaMenu;
import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.heriamc.hub.HeriaHub;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class LootboxMainMenu extends HeriaMenu {

    private final HeriaHub hub;
    private final Location boxLocation;

    public LootboxMainMenu(Player player, HeriaHub hub, Location boxLocation) {
        super(player, "Lootbox", 27, false);
        this.hub = hub;
        this.boxLocation = boxLocation;
    }

    @Override
    public void contents(Inventory inventory) {
        this.insertInteractItem(inventory, 12, new ItemBuilder(Material.INK_SACK, 1, DyeColor.LIME.getDyeData())
                .setName("§aOuvrir une lootbox")
                .addLore(" ")
                .addLore("§7Vous avez §a0 §7lootboxes.")
                .addLore(" ")
                .addLore("§6§l» §eClique: §fOuvrir")
                .onClick(event -> {
                    getPlayer().closeInventory();
                    /*getPlayer().playSound(getPlayer().getLocation(), Sound.VILLAGER_NO, 1, 1);
                    getPlayer().sendMessage("§cVous n'avez pas de lootboxes !");*/

                    new LootboxOpenRunnable(hub, getPlayer(), boxLocation);
                }));

        this.insertInteractItem(inventory, 14, new ItemBuilder(Material.CHEST)
                .setName("§eRécompenses")
                .addLore(" ")
                .addLore("§7Visualisez les différentes")
                .addLore("§7récompenses possibles.")
                .addLore(" ")
                .addLore("§6§l» §eClique: §fVoir")
                .onClick(event -> {
                    hub.getBukkitAPI().getMenuManager().open(new LootboxPrizesMenu(getPlayer(), hub, boxLocation));
                }));
    }
}
