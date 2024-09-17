package fr.heriamc.hub.menu.games;

import fr.heriamc.bukkit.menu.HeriaMenu;
import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.heriamc.hub.HeriaHub;
import fr.heriamc.hub.menu.lobby.LobbyMenu;
import fr.heriamc.hub.menu.shop.ShopMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class GamesMenu extends HeriaMenu {

    protected final HeriaHub hub;
    protected final HeriaMenu before;

    public GamesMenu(Player player, HeriaHub hub, HeriaMenu before) {
        super(player, "Menu des jeux", 54, false);
        this.hub = hub;
        this.before = before;
    }

    @Override
    public void contents(Inventory inv) {
        this.setBorder(inv, 1);

        this.insertInteractItem(inv, 49, new ItemBuilder(Material.DARK_OAK_DOOR_ITEM)
                .setName("§c» Fermer le menu")
                .onClick(event -> {
                    if(before != null){
                        hub.getBukkitAPI().getMenuManager().open(before);
                        return;
                    }

                    getPlayer().closeInventory();
                }));

        this.insertInteractItem(inv, 18, new ItemBuilder(Material.SIGN)
                .setName("§f» §bInfos du serveur")
                .onClick(event -> {

                }));

        this.insertInteractItem(inv, 27, new ItemBuilder(Material.NETHER_STAR)
                .setName("§f» §dHost")
                .onClick(event -> {

                }));

        this.insertInteractItem(inv, 26, new ItemBuilder(Material.ENDER_PORTAL_FRAME)
                .setName("§f» §5Changer de lobby")
                .onClick(event -> {
                    hub.getBukkitAPI().getMenuManager().open(new LobbyMenu(getPlayer(), hub, this));
                }));

        this.insertInteractItem(inv, 35, new ItemBuilder(Material.GOLD_INGOT)
                .setName("§f» §6Boutique")
                .onClick(event -> {
                    hub.getBukkitAPI().getMenuManager().open(new ShopMenu(getPlayer(), hub, this));
                }));

        this.insertInteractItem(inv, 4, new ItemBuilder(Material.SKULL_ITEM, 1, (short) 3)
                .setName("§e» §aProfil")
                .setSkullOwner(getPlayer().getName())
                .onClick(event -> {

                }));


    }
}
