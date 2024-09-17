package fr.heriamc.hub.menu.shop.rank;

import fr.heriamc.api.user.HeriaPlayer;
import fr.heriamc.bukkit.menu.HeriaMenu;
import fr.heriamc.hub.HeriaHub;
import fr.heriamc.hub.menu.ConfirmMenu;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;


public class ShopRankConfirmMenu extends ConfirmMenu {

    private final ShopRank shopRank;

    public ShopRankConfirmMenu(Player player, HeriaHub hub, HeriaMenu before, ShopRank shopRank) {
        super(player, ChatColor.stripColor(shopRank.getConverter().getPrefix()), hub, before, (actionPlayer) -> {
            actionPlayer.closeInventory();
            HeriaPlayer heriaAction = hub.getBukkitAPI().getApi().getPlayerManager().get(player.getUniqueId());
            heriaAction.removeCredits(shopRank.getPrice());
            hub.getBukkitAPI().getApi().getPlayerManager().save(heriaAction);
            hub.getBukkitAPI().getApi().getPlayerManager().saveInPersistant(heriaAction);
            player.sendMessage("§a» Vous avez acheté le grade " + shopRank.getConverter().getPrefix() + " ! Il est possible que certaines fonctionnalités demandent une reconnection au serveur.");
        });
        this.shopRank = shopRank;
    }

    @Override
    public void inventory(Inventory inventory) {
        this.setBorder(inventory, DyeColor.YELLOW.getWoolData());
    }
}
