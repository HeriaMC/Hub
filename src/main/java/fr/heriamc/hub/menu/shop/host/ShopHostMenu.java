package fr.heriamc.hub.menu.shop.host;

import fr.heriamc.api.user.HeriaPlayer;
import fr.heriamc.api.utils.HeriaSkull;
import fr.heriamc.bukkit.menu.HeriaMenu;
import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.heriamc.hub.HeriaHub;
import fr.heriamc.hub.menu.shop.ShopMenu;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ShopHostMenu extends HeriaMenu {

    private final HeriaHub hub;
    private int count = 1;

    public ShopHostMenu(Player player, HeriaHub hub) {
        super(player, "Hosts", 54, false);
        this.hub = hub;
    }

    @Override
    public void contents(Inventory inventory) {
        this.setBorder(inventory, DyeColor.YELLOW.getWoolData());

        inventory.setItem(4, new ItemBuilder(Material.NETHER_STAR).setName("§f» §dHost").build());

        float initial = (float) count / 2;
        float price = (float) count / 2;

        if(count >= 10){
            price -= ((float) count / 10);
        }

        float finalPrice = price;
        this.insertInteractItem(inventory, 22, new ItemBuilder(Material.NETHER_STAR)
                .setName("§dPanier des hosts")
                .setLoreWithList("§7Vous avez sélectionné §d" + count + " hosts §7à acheter",
                        " ",
                        "§f→ 10 hosts achetés, §d1 EUR §foffert.",
                        " ",
                        "§fPrix: §d" + (initial == price ? price + " EUR" : "§d§m" + initial + "§d " + price + " EUR"),
                        "§6§l❱ §eClique pour acheter"
                        )
                .onClick(event -> {
                    HeriaPlayer heriaPlayer = hub.getBukkitAPI().getApi().getPlayerManager().get(getPlayer().getUniqueId());

                    if(heriaPlayer.getCredits() < finalPrice){
                        getPlayer().sendMessage("§cVous n'avez pas les fonds nécessaires pour acheter " + count + " hosts");
                        return;
                    }

                    hub.getBukkitAPI().getMenuManager().open(new ShopHostConfirmMenu(getPlayer(), hub, this, count, finalPrice));
                }));

        this.insertInteractItem(inventory, 29, new ItemBuilder(Material.SKULL_ITEM, 1, (short) 3)
                .setSkullURL(HeriaSkull.RED_MINUS.getURL())
                .setName("§c» Diminuer de 5")
                .onClick(event -> {
                    this.count -= 5;

                    if(count < 1){
                        count = 1;
                    }
                    this.updateMenu();
                }));
        this.insertInteractItem(inventory, 30, new ItemBuilder(Material.SKULL_ITEM, 1, (short) 3)
                .setSkullURL(HeriaSkull.RED_MINUS.getURL())
                .setName("§c» Diminuer de 1")
                .onClick(event -> {
                    this.count -= 1;

                    if(count < 1){
                        count = 1;
                    }
                    this.updateMenu();
                }));

        this.insertInteractItem(inventory, 32, new ItemBuilder(Material.SKULL_ITEM, 1, (short) 3)
                .setSkullURL(HeriaSkull.GREEN_PLUS.getURL())
                .setName("§a» Augmenter de 1")
                .onClick(event -> {
                    this.count += 1;

                    if(count > 100){
                        count = 100;
                    }
                    this.updateMenu();
                }));

        this.insertInteractItem(inventory, 33, new ItemBuilder(Material.SKULL_ITEM, 1, (short) 3)
                .setSkullURL(HeriaSkull.GREEN_PLUS.getURL())
                .setName("§a» Augmenter de 5")
                .onClick(event -> {
                    this.count += 5;

                    if(count > 100){
                        count = 100;
                    }
                    this.updateMenu();
                }));

        this.insertInteractItem(inventory, 49, new ItemBuilder(Material.DARK_OAK_DOOR_ITEM).setName("§c» Fermer le menu")
                .onClick(event -> {
                    hub.getBukkitAPI().getMenuManager().open(new ShopMenu(getPlayer(), hub, this));
                }));
    }
}
