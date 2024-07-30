package fr.heriamc.hub.menu;

import fr.heriamc.bukkit.menu.HeriaMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class GamesMenu extends HeriaMenu {

    public GamesMenu(Player player) {
        super(player, "Menu des jeux", 54, false);
    }

    @Override
    public void contents(Inventory inv) {
        this.setBorder(inv, 1);

    }

    @Override
    public void onClick(InventoryClickEvent event) {

    }
}
