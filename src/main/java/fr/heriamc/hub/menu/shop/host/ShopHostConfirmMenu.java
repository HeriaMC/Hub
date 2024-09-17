package fr.heriamc.hub.menu.shop.host;

import fr.heriamc.api.user.HeriaPlayer;
import fr.heriamc.bukkit.menu.HeriaMenu;
import fr.heriamc.hub.HeriaHub;
import fr.heriamc.hub.menu.ConfirmMenu;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.function.Consumer;

public class ShopHostConfirmMenu extends ConfirmMenu {

    private final int hosts;

    public ShopHostConfirmMenu(Player player, HeriaHub hub, HeriaMenu before, int hosts) {
        super(player, hosts + " hosts", hub, before, actionPlayer -> {
            actionPlayer.closeInventory();
            HeriaPlayer heriaAction = hub.getBukkitAPI().getApi().getPlayerManager().get(player.getUniqueId());
            int hosts1 = heriaAction.getHosts();
            heriaAction.setHosts(hosts1 + hosts);

            hub.getBukkitAPI().getApi().getPlayerManager().save(heriaAction);
            hub.getBukkitAPI().getApi().getPlayerManager().saveInPersistant(heriaAction);
            player.sendMessage("§a» Vous avez acheté " + hosts + " supplémentaires !");
        });
        this.hosts = hosts;
    }

    @Override
    public void inventory(Inventory inventory) {
        this.setBorder(inventory, DyeColor.YELLOW.getWoolData());
    }
}
