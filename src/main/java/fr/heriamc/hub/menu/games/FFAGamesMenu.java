package fr.heriamc.hub.menu.games;

import fr.heriamc.api.server.HeriaServerType;
import fr.heriamc.bukkit.menu.HeriaMenu;
import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.heriamc.hub.HeriaHub;
import fr.heriamc.hub.game.HubGame;
import fr.heriamc.hub.menu.games.sub.SubGameListMenu;
import fr.heriamc.hub.menu.games.sub.SubGameMainMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class FFAGamesMenu extends GamesMenu{

    public FFAGamesMenu(Player player, HeriaHub hub, HeriaMenu before) {
        super(player, hub, before);
    }

    @Override
    public void contents(Inventory inv) {
        super.contents(inv);

        inv.setItem(21, new ItemBuilder(Material.BARRIER).setName("§c» Prochainement...").build());
        inv.setItem(23, new ItemBuilder(Material.BARRIER).setName("§c» Prochainement...").build());

        this.insertInteractItem(inv, 22, new ItemBuilder(Material.BOW)
                .setName("§e» §6OneShot")
                .setLoreWithList(
                        "",
                        "§7Le OneShot est un jeu où vous",
                        "§7disposez d'un arc et d'une flèche",
                        "§7pour éliminer un adversaire en",
                        "§7un seul tir",
                        " ",
                        "§8» §7Version : §e1.8+",
                        "§8» §7Connectés : §60",
                        "§8» §7Développeur : §6JoupiterHD",
                        " ",
                        "§6&l❱ §eClique: §fJouer")
                .onClick(event -> {
                    hub.getBukkitAPI().getMenuManager().open(new SubGameMainMenu(getPlayer(), hub, HubGame.ONESHOT, this));
                    return;
                }));

        this.insertInteractItem(inv, 31, new ItemBuilder(Material.TNT)
                .setName("§e» §6RushFFA")
                .setLoreWithList(
                        "",
                        "§7Le RushFFA vous place sur de petites",
                        "§7bases où l’objectif est de tuer le",
                        "§7plus d'ennemis possible en utilisant",
                        "§7des TNT pour foncer dans l'action.",
                        " ",
                        "§8» §7Version : §e1.8+",
                        "§8» §7Connectés : §60",
                        "§8» §7Développeur : §64kara",
                        " ",
                        "§6&l❱ §eClique: §fJouer")
                .onClick(event -> {
                    hub.getBukkitAPI().getMenuManager().open(new SubGameMainMenu(getPlayer(), hub, HubGame.RUSHFFA, this));
                    return;
                }));
    }
}
