package fr.heriamc.hub.cosmetics;

import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.heriamc.hub.HeriaHub;
import fr.heriamc.hub.cosmetics.types.ArmorCosmetic;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public enum CosmeticType {

    PARTICLE("Particules", 21, new ItemBuilder(Material.NETHER_STAR).setName("§aParticules")){
        @Override
        public void equip(HeriaHub hub, Player player, String id) {
            Server server = hub.getServer();

            server.dispatchCommand(server.getConsoleSender(), "pc give cosmetic " + player.getName() + " particle-effects " + id);
            server.dispatchCommand(server.getConsoleSender(), "pc equip " + player.getName() + " particle-effects " + id);
        }

        @Override
        public void unEquip(HeriaHub hub, Player player, String id) {
            Server server = hub.getServer();

            server.dispatchCommand(server.getConsoleSender(), "pc unequip " + player.getName() + " particle-effects");
        }
    },

    MINIATURES("Pets", 22, new ItemBuilder(Material.SADDLE).setName("§6Pets")){
        @Override
        public void equip(HeriaHub hub, Player player, String id) {
            Server server = hub.getServer();
            server.dispatchCommand(server.getConsoleSender(), "pc equip " + player.getName() + " miniatures " + id);
        }

        @Override
        public void unEquip(HeriaHub hub, Player player, String id) {
            Server server = hub.getServer();

            server.dispatchCommand(server.getConsoleSender(), "pc unequip " + player.getName() + " miniatures");
        }
    },

    ARMOR("Armures", 23, new ItemBuilder(Material.LEATHER_CHESTPLATE).setName("§dArmures")){
        @Override
        public void equip(HeriaHub hub, Player player, String id) {
            String armorPart = id.split("\\.")[1];

            ArmorCosmetic cosmetic = ArmorCosmetic.getFromId(id);
            EquipmentSlot equipmentSlot = EquipmentSlot.valueOf(armorPart.toUpperCase());

            if(cosmetic == null){
                return;
            }

            this.equipArmor(player.getInventory(), equipmentSlot, cosmetic.getArmor());
        }

        @Override
        public void unEquip(HeriaHub hub, Player player, String id) {
            String armorPart = id.split("\\.")[1];
            EquipmentSlot equipmentSlot = EquipmentSlot.valueOf(armorPart.toUpperCase());

            this.equipArmor(player.getInventory(), equipmentSlot, null);

        }

        public void equipArmor(PlayerInventory inventory, EquipmentSlot equipmentSlot, ItemStack item){
            switch (equipmentSlot){
                case FEET -> inventory.setBoots(item);
                case LEGS -> inventory.setLeggings(item);
                case CHEST -> inventory.setChestplate(item);
                case HAND -> inventory.setHelmet(item);
            }
        }
    },

    JOIN_MESSAGES("Messsages de connection", 31, new ItemBuilder(Material.PAPER).setName("§3Messages de connection")){
        @Override
        public void equip(HeriaHub hub, Player player, String id) {
            //TODO ?
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

    public void unEquip(HeriaHub hub, Player player, String id){

    }

}
