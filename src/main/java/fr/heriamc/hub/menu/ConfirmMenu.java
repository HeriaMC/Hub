package fr.heriamc.hub.menu;

import fr.heriamc.bukkit.menu.HeriaMenu;
import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.heriamc.hub.HeriaHub;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.function.Consumer;

public abstract class ConfirmMenu extends HeriaMenu {

    private final String name;
    private final HeriaHub hub;
    private final HeriaMenu before;
    private final Consumer<Player> confirmAction;

    public ConfirmMenu(Player player, String name, HeriaHub hub, HeriaMenu before, Consumer<Player> confirmAction) {
        super(player, "Confirmer l'achat de " + name + " ?", 54, false);
        this.name = name;
        this.hub = hub;
        this.before = before;
        this.confirmAction = confirmAction;
    }

    @Override
    public void contents(Inventory inventory) {
        this.inventory(inventory);

        this.insertInteractItem(inventory, 21, new ItemBuilder(Material.SLIME_BALL).setName("§aConfirmer").onClick(event -> {
            this.confirmAction.accept(getPlayer());
        }));

        this.insertInteractItem(inventory, 23, new ItemBuilder(Material.SLIME_BALL).setName("§cAnnuler").onClick(event -> {
            hub.getBukkitAPI().getMenuManager().open(before);
        }));
    }

    public abstract void inventory(Inventory inventory);
}
