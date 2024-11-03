package fr.heriamc.hub.menu.games;

import fr.heriamc.bukkit.menu.HeriaMenu;
import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.heriamc.hub.HeriaHub;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

public class BasicGamesMenu extends GamesMenu{

    public BasicGamesMenu(Player player, HeriaHub hub, HeriaMenu before) {
        super(player, hub, before);
    }

    @Override
    public void contents(Inventory inv) {
        super.contents(inv);

        this.insertInteractItem(inv, 22, new ItemBuilder(Material.DIAMOND_PICKAXE)
                .setName("§e» §6Jeux engageants")
                .setLoreWithList(
                        " ",
                        "§7La clé du succès réside dans la",
                        "§7coopération et la tactique pour",
                        "§7mener ton équipe à la victoire.",
                        "",
                        "§8» §7Version: §e1.8+",
                        "§8» §7Connectés: §60",
                        "",
                        "§6&l❱ §eClique: §fParcourir")
                .flag(ItemFlag.HIDE_ATTRIBUTES)
                .onClick(event -> {
                    hub.getBukkitAPI().getMenuManager().open(new MiniGamesMenu(getPlayer(), hub, this));
                }));

        this.insertInteractItem(inv, 31, new ItemBuilder(Material.ARROW)
                .setName("§e» §6Jeux FFA")
                .setLoreWithList(
                        " ",
                        "§7Les jeux FFA sont des modes",
                        "§7rapides et dynamiques, où chaque",
                        "§7 joueur combat pour lui-même.",
                        "",
                        "§8» §7Version : §e1.8+",
                        "§8» §7Connectés : §60",
                        "",
                        "§6&l❱ §eClique: §fParcourir")
                .onClick(event -> {
                    hub.getBukkitAPI().getMenuManager().open(new FFAGamesMenu(getPlayer(), hub, this));
                }));

        inv.setItem(30, new ItemBuilder(Material.BARRIER).setName("§c» Prochainement...").build());
    }
}
