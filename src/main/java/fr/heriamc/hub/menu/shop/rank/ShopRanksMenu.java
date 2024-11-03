package fr.heriamc.hub.menu.shop.rank;

import fr.heriamc.api.user.HeriaPlayer;
import fr.heriamc.api.user.rank.HeriaRank;
import fr.heriamc.bukkit.menu.HeriaMenu;
import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.heriamc.hub.HeriaHub;
import fr.heriamc.hub.menu.shop.ShopMenu;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ShopRanksMenu extends HeriaMenu {

    private final HeriaHub hub;
    private final HeriaMenu before;

    public ShopRanksMenu(Player player, HeriaHub hub, HeriaMenu before) {
        super(player, "Grades", 54, false);
        this.hub = hub;
        this.before = before;
    }

    @Override
    public void contents(Inventory inventory) {
        this.setBorder(inventory, DyeColor.YELLOW.getWoolData());

        inventory.setItem(4, new ItemBuilder(Material.ENCHANTMENT_TABLE).setName("§f» §6Grades").build());

        for (ShopRank value : ShopRank.values()) {
            String name = value.getItemBuilder().getName();
            name = name.replaceAll("%player_name%", getPlayer().getName());

            this.insertInteractItem(inventory, value.getSlot(), value.getItemBuilder().clone()
                    .setName(name)
                    .onClick(event -> {
                HeriaPlayer heriaPlayer = hub.getBukkitAPI().getApi().getPlayerManager().get(getPlayer().getUniqueId());

                if(heriaPlayer.getRank().getPower() > value.getConverter().getPower()){
                    getPlayer().sendMessage("§c» Vous possédez déjà un rank au dessus ou équivalent de " + value.getConverter().getName());
                    getPlayer().closeInventory();
                    return;
                }

                if(value == ShopRank.CUSTOM){
                    if(heriaPlayer.getRank().getPower() != HeriaRank.SUPREME.getPower()){
                        getPlayer().sendMessage("§c» Pour acheter le grade " + value.getConverter().getName() + ", vous devez d'abord être Suprême.");
                        return;
                    }
                }
                if(heriaPlayer.getCredits() < value.getPrice()){
                    getPlayer().sendMessage("§cVous n'avez pas les fonds nécessaires pour acheter le grade " + value.getConverter().getName());
                    getPlayer().closeInventory();
                    return;
                }

                hub.getBukkitAPI().getMenuManager().open(new ShopRankConfirmMenu(getPlayer(), hub, before, value));
            }));
        }

        this.insertInteractItem(inventory, 49, new ItemBuilder(Material.DARK_OAK_DOOR_ITEM).setName("§c» Fermer le menu")
                .onClick(event -> {
                    hub.getBukkitAPI().getMenuManager().open(new ShopMenu(getPlayer(), hub, before));
                }));
    }
}
