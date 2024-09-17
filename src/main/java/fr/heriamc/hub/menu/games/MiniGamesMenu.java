package fr.heriamc.hub.menu.games;

import fr.heriamc.bukkit.menu.HeriaMenu;
import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.heriamc.hub.HeriaHub;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MiniGamesMenu extends GamesMenu {

    public MiniGamesMenu(Player player, HeriaHub hub, HeriaMenu before) {
        super(player, hub, before);
    }

    @Override
    public void contents(Inventory inv) {
        super.contents(inv);

        inv.setItem(30, new ItemBuilder(Material.BARRIER).setName("§c» Prochainement...").build());
        inv.setItem(32, new ItemBuilder(Material.BARRIER).setName("§c» Prochainement...").build());

        this.insertInteractItem(inv, 22, new ItemBuilder(Material.GRASS)
                .setName("§e» §6Freecube")
                .setLoreWithList(
                        "",
                        "§7Le FreeCube vous permet de créer",
                        "§7des maisons, des villes, et tout",
                        "§7ce qui vous passe par la tête.",
                        " ",
                        "§8» §7Version : §e1.8+",
                        "§8» §7Connectés : §60",
                        " ",
                        "§6&l❱ §eClique pour rejoindre")
                .onClick(event -> {

                }));


        this.insertInteractItem(inv, 31, new ItemBuilder(Material.SANDSTONE)
                .setName("§e» §6HikaBrain")
                .setLoreWithList(
                        "",
                        "§7Le Hikabrain vous place sur un",
                        "§7pont où l'objectif est de marquer",
                        "§75 points contre l’adversaire",
                        " ",
                        "§8» §7Version : §e1.8+",
                        "§8» §7Connectés : §60",
                        " ",
                        "§6&l❱ §eClique pour rejoindre")
                .onClick(event -> {

                }));
    }
}
