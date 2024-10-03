package fr.heriamc.hub.menu.options;

import fr.heriamc.bukkit.menu.HeriaMenu;
import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.heriamc.hub.HeriaHub;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class OptionsMenu extends HeriaMenu {

    private final HeriaHub hub;
    public OptionsMenu(Player player, HeriaHub hub) {
        super(player, "Paramètres", 54, false);
        this.hub = hub;
    }

    @Override
    public void contents(Inventory inventory) {
        setBorder(inventory, DyeColor.RED.getWoolData());
        
    }
}
