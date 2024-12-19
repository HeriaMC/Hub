package fr.heriamc.hub.cosmetics;

import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.heriamc.hub.HeriaHub;
import fr.heriamc.hub.cosmetics.armor.RainbowArmorRunnable;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.Map;

public enum CosmeticType {

    PARTICLE("Particules", 21, new ItemBuilder(Material.NETHER_STAR).setName("§f» §aParticules")){
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

    MINIATURES("Pets", 22, new ItemBuilder(Material.SADDLE).setName("§f» §6Pets")){
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

    ARMOR("Armures", 23, new ItemBuilder(Material.LEATHER_CHESTPLATE).setName("§f» §dArmures")){

        private final Map<Player, RainbowArmorRunnable> armorRunnableMap = new HashMap<>();

        @Override
        public void equip(HeriaHub hub, Player player, String id) {
            Bukkit.broadcastMessage("equip armor to " + player.getName() + " with id " + id);
            String armorPart = id.split("\\.")[1];

            CosmeticSubType cosmetic = CosmeticSubType.getFromId(id);
            //Bukkit.broadcastMessage("cosmetic= " + cosmetic);

            EquipmentSlot equipmentSlot = EquipmentSlot.valueOf(armorPart.toUpperCase());
            //Bukkit.broadcastMessage("equipmentSlot= " + equipmentSlot);

            if(cosmetic == null){
                return;
            }

            if(id.startsWith("rainbow")){

                RainbowArmorRunnable rainbowArmorRunnable = this.armorRunnableMap.get(player);

                if(rainbowArmorRunnable == null){
                    rainbowArmorRunnable = new RainbowArmorRunnable(player);
                    hub.getServer().getScheduler().runTaskTimer(hub, rainbowArmorRunnable, 0, 1L);
                    this.armorRunnableMap.put(player, rainbowArmorRunnable);
                }

                rainbowArmorRunnable.addEquipmentSlot(equipmentSlot);
                return;
            }

            //Bukkit.broadcastMessage("equipping.. ");
            this.equipArmor(player.getInventory(), equipmentSlot, cosmetic.getRepresentation().build());
        }

        @Override
        public void unEquip(HeriaHub hub, Player player, String id) {
            String armorPart = id.split("\\.")[1];
            EquipmentSlot equipmentSlot = EquipmentSlot.valueOf(armorPart.toUpperCase());

            if(id.startsWith("rainbow")){
                RainbowArmorRunnable rainbowArmorRunnable = this.armorRunnableMap.get(player);
                if(rainbowArmorRunnable != null) rainbowArmorRunnable.removeEquipmentSlot(equipmentSlot);
            }

            this.equipArmor(player.getInventory(), equipmentSlot, null);
        }

        public void equipArmor(PlayerInventory inventory, EquipmentSlot equipmentSlot, ItemStack item){
            switch (equipmentSlot){
                case FEET -> inventory.setBoots(item);
                case LEGS -> inventory.setLeggings(item);
                case CHEST -> inventory.setChestplate(item);
                case HEAD -> inventory.setHelmet(item);
            }
        }
    },

    JOIN_MESSAGES("Messsages de connection", 31, new ItemBuilder(Material.PAPER).setName("§f» §3Messages de connection")){

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
