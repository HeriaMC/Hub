package fr.heriamc.hub.menu.shop.host;

import fr.heriamc.api.user.HeriaPlayer;
import fr.heriamc.bukkit.menu.HeriaMenu;
import fr.heriamc.bukkit.menu.confirm.ConfirmMenu;
import fr.heriamc.hub.HeriaHub;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;


public class ShopHostConfirmMenu extends ConfirmMenu {

    public ShopHostConfirmMenu(Player player, HeriaHub hub, HeriaMenu before, int hosts, float price) {
        super(player, hosts + " hosts", hub.getBukkitAPI(), before, actionPlayer -> {
            actionPlayer.closeInventory();
            HeriaPlayer heriaAction = hub.getBukkitAPI().getApi().getPlayerManager().get(player.getUniqueId());
            int hosts1 = heriaAction.getHosts();
            heriaAction.setHosts(hosts1 + hosts);
            heriaAction.removeCredits(price);

            hub.getBukkitAPI().getApi().getPlayerManager().save(heriaAction);
            hub.getBukkitAPI().getApi().getPlayerManager().saveInPersistant(heriaAction);
            player.sendMessage("§a» Vous avez acheté " + hosts + " supplémentaires !");
        });
    }

    @Override
    public void inventory(Inventory inventory) {
        this.setBorder(inventory, DyeColor.YELLOW.getWoolData());
    }
}
