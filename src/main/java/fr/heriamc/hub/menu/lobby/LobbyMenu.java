package fr.heriamc.hub.menu.lobby;

import fr.heriamc.api.server.HeriaServer;
import fr.heriamc.api.server.HeriaServerType;
import fr.heriamc.bukkit.menu.HeriaMenu;
import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.heriamc.hub.HeriaHub;
import fr.heriamc.proxy.packet.SendPlayerPacket;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class LobbyMenu extends HeriaMenu {

    private final HeriaHub hub;
    private final HeriaMenu before;
    private final int[] slots = new int[]{20,21,22,23,24,29,30,31,32,33};

    public LobbyMenu(Player player, HeriaHub hub, HeriaMenu before) {
        super(player, "Sélecteur de hub", 54, true);
        this.hub = hub;
        this.before = before;
    }

    @Override
    public void contents(Inventory inventory) {
        this.setBorder(inventory, DyeColor.PINK.getWoolData());

        List<HeriaServer> allInCache = this.hub.getBukkitAPI().getApi().getServerManager().getAllInCache();
        allInCache.removeIf(heriaServer -> heriaServer.getType() != HeriaServerType.HUB);


        inventory.setItem(4, new ItemBuilder(Material.ENDER_PORTAL_FRAME).setName("§f» §dSélecteur de lobby").build());

        int index = 0;
        for (HeriaServer server : allInCache) {
            this.insertInteractItem(inventory, slots[index], new ItemBuilder(Material.SKULL_ITEM, 1, (short) 3)
                    .setSkullURL(server.getStatus().getSkull().getURL())
                    .setName(server.getStatus().getColor().getColor() + server.getName().replaceAll("-", " #").toUpperCase())
                    .setLoreWithList(" ",
                            "§8» §7Statut : " + server.getStatus().getColor() + server.getStatus().getName(),
                            "§8» §7Connectés : §6" + server.getConnected().size(),
                            " ",
                            "§6§l❱ §eClique pour rejoindre")
                    .onClick(event -> {
                        getPlayer().closeInventory();
                        hub.getBukkitAPI().getApi().getMessaging().send(new SendPlayerPacket(getPlayer().getUniqueId(), server.getName()));
                    }));
            index++;
        }

        this.insertInteractItem(inventory, 49, new ItemBuilder(Material.DARK_OAK_DOOR_ITEM).setName("§c» Fermer le menu")
                .onClick(event -> {
                    if(before != null){
                        hub.getBukkitAPI().getMenuManager().open(before);
                        return;
                    }
                    getPlayer().closeInventory();
                }));
    }
}
