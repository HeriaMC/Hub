package fr.heriamc.hub.menu.lobby;

import fr.heriamc.api.server.HeriaServer;
import fr.heriamc.api.server.HeriaServerType;
import fr.heriamc.bukkit.menu.HeriaMenu;
import fr.heriamc.bukkit.menu.pagination.HeriaPaginationMenu;
import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.heriamc.hub.HeriaHub;
import fr.heriamc.proxy.packet.SendPlayerPacket;
import fr.heriamc.proxy.queue.packet.QueueJoinPacket;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class LobbyMenu extends HeriaPaginationMenu<HeriaServer> {

    private final HeriaHub hub;
    private final HeriaMenu before;

    public LobbyMenu(Player player, HeriaHub hub, HeriaMenu before) {
        super(player, "Sélecteur de hub", 54, true, List.of(20,21,22,23,24,29,30,31,32,33),
                () -> hub.getBukkitAPI().getApi().getServerManager().getAll(HeriaServerType.HUB));
        this.hub = hub;
        this.before = before;
    }

    @Override
    public void inventory(Inventory inventory) {
        this.setBorder(inventory, DyeColor.PINK.getWoolData());
        inventory.setItem(4, new ItemBuilder(Material.ENDER_PORTAL_FRAME).setName("§f» §dSélecteur de lobby").build());

        this.insertInteractItem(inventory, 48, new ItemBuilder(Material.DARK_OAK_DOOR_ITEM).setName("§c» Fermer le menu")
                .onClick(event -> {
                    if(before != null){
                        hub.getBukkitAPI().getMenuManager().open(before);
                        return;
                    }
                    getPlayer().closeInventory();
                }));
    }

    @Override
    protected ItemBuilder item(HeriaServer server, int i, int i1) {
        return new ItemBuilder(Material.SKULL_ITEM, 1, (short) 3)
                .setSkullURL(server.getStatus().getSkull().getURL())
                .setName(server.getStatus().getColor().getColor() + server.getName().replaceAll("-", " #").toUpperCase())
                .setLoreWithList(" ",
                        "§8» §7Statut : " + server.getStatus().getColor() + server.getStatus().getName(),
                        "§8» §7Connectés : §6" + server.getConnected().size(),
                        " ",
                        "§6§l❱ §eClique: §fRejoindre")
                .onClick(event -> {
                    getPlayer().closeInventory();
                    hub.getBukkitAPI().getApi().getMessaging().send(new QueueJoinPacket(getPlayer().getUniqueId(), server.getName(), null, null, null));
                });
    }
}
