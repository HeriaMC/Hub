package fr.heriamc.hub.menu.games.sub;

import fr.heriamc.api.game.size.GameSize;
import fr.heriamc.bukkit.menu.HeriaMenu;
import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.heriamc.hub.HeriaHub;
import fr.heriamc.hub.game.HubGame;
import fr.heriamc.proxy.queue.packet.QueueJoinPacket;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SubGameMainMenu extends HeriaMenu {

    private final HeriaHub hub;
    private final HubGame hubGame;
    private final HeriaMenu before;

    public SubGameMainMenu(Player player, HeriaHub hub, HubGame hubGame, HeriaMenu before) {
        super(player, "Jouer en " + hubGame.getServerType().getName(), 54, false);
        this.hub = hub;
        this.hubGame = hubGame;
        this.before = before;
    }

    @Override
    public void contents(Inventory inventory) {
        setBorder(inventory, DyeColor.ORANGE.getData());

        int startSlot = 22 - (hubGame.getGameSizes().size() / 2);

        for (GameSize gameSize : hubGame.getGameSizes()) {
            this.insertInteractItem(inventory, startSlot, new ItemBuilder(hubGame.getItemStack().clone())
                    .setName("§e" + gameSize.getName())
                    .addLore(" ")
                    .addLore("§6§l❱ §eClic-gauche: §fFile d'attente")
                    .addLore("§6§l❱ §eClic-droit: §fParcourir les parties")
                    .onClick(event -> {
                        if(event.isRightClick()){
                            this.hub.getBukkitAPI().getMenuManager().open(new SubGameListMenu(getPlayer(), hub, hubGame, gameSize, this));
                            return;
                        }

                        this.hub.getBukkitAPI().getApi().getMessaging().send(
                                new QueueJoinPacket(getPlayer().getUniqueId(), null, null, hubGame.getServerType(), gameSize));

                        getPlayer().sendMessage("§aVous avez rejoins une file d'attente.");
                        getPlayer().closeInventory();
                    }));
            startSlot++;
        }

        this.insertInteractItem(inventory, 49, new ItemBuilder(Material.DARK_OAK_DOOR_ITEM)
                .setName("§c» Retour")
                .onClick(event -> {
                    if(before == null){
                        getPlayer().closeInventory();
                        return;
                    }
                    hub.getBukkitAPI().getMenuManager().open(before);
                }));
    }
}
