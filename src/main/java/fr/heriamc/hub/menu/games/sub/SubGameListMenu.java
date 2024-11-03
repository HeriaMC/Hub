package fr.heriamc.hub.menu.games.sub;

import fr.heriamc.api.game.HeriaGameInfo;
import fr.heriamc.api.game.size.GameSize;
import fr.heriamc.api.server.HeriaServerType;
import fr.heriamc.bukkit.HeriaBukkit;
import fr.heriamc.bukkit.menu.HeriaMenu;
import fr.heriamc.bukkit.menu.pagination.HeriaPaginationMenu;
import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.heriamc.hub.HeriaHub;
import fr.heriamc.hub.game.HubGame;
import fr.heriamc.proxy.queue.packet.QueueJoinPacket;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class SubGameListMenu extends HeriaPaginationMenu<HeriaGameInfo> {

    private final HeriaHub hub;
    private final HubGame hubGame;
    private final GameSize gameSize;
    private final HeriaMenu before;

    public SubGameListMenu(Player player, HeriaHub hub, HubGame hubGame, GameSize gameSize, HeriaMenu before) {
        super(player, "Parties de " + hubGame.getServerType().getName() + " " + gameSize.getName(), 54, true, List.of(10,11,12,13,14,15,16,
                19,20,21,22,23,24,25,
                28,29,30,31,32,33,34,
                37,38,39,40,41,42,43), () -> hub.getBukkitAPI().getApi().getHeriaGameManager().getGames(hubGame.getServerType(),gameSize));
        this.hub = hub;
        this.hubGame = hubGame;
        this.gameSize = gameSize;
        this.before = before;
    }

    @Override
    public void inventory(Inventory inventory) {
        setBorder(inventory, DyeColor.ORANGE.getData());

        this.insertInteractItem(inventory, 48, new ItemBuilder(Material.DARK_OAK_DOOR_ITEM)
                .setName("§c» Retour")
                .onClick(event -> {
                    if(before == null){
                        getPlayer().closeInventory();
                        return;
                    }
                    hub.getBukkitAPI().getMenuManager().open(before);
                }));
    }

    @Override
    protected ItemBuilder item(HeriaGameInfo heriaGameInfo, int i, int i1) {
        return new ItemBuilder(Material.SKULL_ITEM, 1, (short) 3)
                .setSkullURL(heriaGameInfo.getState().getSkull().getURL())
                .setName(heriaGameInfo.getState().getColor() + heriaGameInfo.getGameName())
                .addLore(" ")
                .addLore("§7Type: §e" + heriaGameInfo.getGameSize().getName().toUpperCase())
                .addLore("§7Joueurs: §b" + heriaGameInfo.getAlivePlayersCount() + "§7/§b" + heriaGameInfo.getGameSize().getMaxPlayer())
                .addLore("§7Spectateurs: §f" + heriaGameInfo.getSpectatorsCount())
                .addLore(" ")
                .addLore("§6§l❱ §eClique: §fFile d'attente")
                .onClick(event -> {
                    getPlayer().closeInventory();
                    hub.getBukkitAPI().getApi().getMessaging().send(
                            new QueueJoinPacket(getPlayer().getUniqueId(), heriaGameInfo.getServerName(), heriaGameInfo.getGameName(), null, null));
                });
    }
}
