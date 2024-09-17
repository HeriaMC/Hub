package fr.heriamc.hub.cosmetics;

import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.heriamc.hub.HeriaHub;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public enum CosmeticType {

    PARTICLE("Particules", 21, new ItemBuilder(Material.NETHER_STAR).setName("§aParticules")){
        @Override
        public void equip(HeriaHub hub, Player player, String id) {
            Server server = hub.getServer();
            server.dispatchCommand(server.getConsoleSender(), "pc give cosmetic " + player.getName() + " particle-effects " + id);
            server.dispatchCommand(server.getConsoleSender(), "pc equip " + player.getName() + " particle-effects " + id);
        }
    },

    MINIATURES("Pets", 22, new ItemBuilder(Material.STONE)){
        @Override
        public void equip(HeriaHub hub, Player player, String id) {
            Server server = hub.getServer();
            server.dispatchCommand(server.getConsoleSender(), "pc equip " + player.getName() + " miniatures " + id);
        }
    }

    ;

    private final String name;
    private final int slot;
    private final ItemBuilder representation;

    CosmeticType(String name, int slot, ItemBuilder representation) {
        this.name = name;
        this.slot = slot;
        this.representation = representation;
    }

    public String getName() {
        return name;
    }

    public int getSlot() {
        return slot;
    }

    public ItemBuilder getRepresentation() {
        return representation;
    }

    public void equip(HeriaHub hub, Player player, String id) {

    }

}
