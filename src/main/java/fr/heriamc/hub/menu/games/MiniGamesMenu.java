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

public class MiniGamesMenu extends GamesMenu {

    public MiniGamesMenu(Player player, HeriaHub hub, HeriaMenu before) {
        super(player, hub, before);
    }

    @Override
    public void contents(Inventory inv) {
        super.contents(inv);

        inv.setItem(30, new ItemBuilder(Material.BARRIER).setName("§c» Prochainement...").build());
        inv.setItem(32, new ItemBuilder(Material.BARRIER).setName("§c» Prochainement...").build());

        this.insertInteractItem(inv, 22, new ItemBuilder(Material.STICK)
                .setName("§e» §6Shootcraft")
                .setLoreWithList(
                        "",
                        "§7Le FreeCube vous permet de créer",
                        "§7des maisons, des villes, et tout",
                        "§7ce qui vous passe par la tête.",
                        " ",
                        "§8» §7Version : §e1.8+",
                        "§8» §7Connectés : §60",
                        "§8» §7Développeur : §64kara",
                        " ",
                        "§6&l❱ §eClique: §fJouer")
                .onClick(event -> {
                    hub.getBukkitAPI().getMenuManager().open(new SubGameMainMenu(getPlayer(), hub, HubGame.SHOOTCRAFT, this));
                    return;
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
                        "§8» §7Développeur : §64kara",
                        " ",
                        "§6&l❱ §eClique: §fJouer")
                .onClick(event -> {
                    hub.getBukkitAPI().getMenuManager().open(new SubGameMainMenu(getPlayer(), hub, HubGame.HIKABRAIN, this));
                    return;
                }));
    }
}
