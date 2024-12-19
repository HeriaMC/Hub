package fr.heriamc.hub.cosmetics.armor;

import fr.heriamc.bukkit.utils.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class RainbowArmorRunnable extends BukkitRunnable {

    private final Player player;

    private static final int CYCLE_SPEED = 5;
    private static final int THRESHOLD = 5000;
    private final List<EquipmentSlot> slots = new ArrayList<>(0);

    private int count = 0;

    public RainbowArmorRunnable(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        if(player == null || !player.isOnline()){
            this.cancel();
            return;
        }

        PlayerInventory playerInventory = player.getInventory();

        Color armorColor = convertCountToRGB(count);
        setArmor(playerInventory, armorColor);
        count += CYCLE_SPEED;

        if (count >= Integer.MAX_VALUE - THRESHOLD) {
            count = 0;
        }

    }

    private void setArmor(PlayerInventory playerInventory, Color armorColor) {
        for (EquipmentSlot slot : this.slots) {
            if(slot == EquipmentSlot.HEAD)
                setArmor(playerInventory::setHelmet, Material.LEATHER_HELMET, armorColor);
            else if(slot == EquipmentSlot.CHEST)
                setArmor(playerInventory::setChestplate, Material.LEATHER_CHESTPLATE, armorColor);
            else if(slot == EquipmentSlot.LEGS)
                setArmor(playerInventory::setLeggings, Material.LEATHER_LEGGINGS, armorColor);
            else if(slot == EquipmentSlot.FEET)
                setArmor(playerInventory::setBoots, Material.LEATHER_BOOTS, armorColor);
        }
    }
    private void setArmor(Consumer<ItemStack> setter, Material material, Color armorColor) {
        setter.accept(createColoredArmor(material, armorColor));
    }

    private ItemStack createColoredArmor(Material material, Color armorColor) {
        ItemBuilder item = new ItemBuilder(material, 1);
        item.setLeatherArmorColor(armorColor);
        return item.build();
    }

    private Color convertCountToRGB(int count) {
        int red = (int) (Math.sin(count * 0.01) * 127 + 128);
        int green = (int) (Math.sin(count * 0.01 + 2) * 127 + 128);
        int blue = (int) (Math.sin(count * 0.01 + 4) * 127 + 128);
        return Color.fromRGB(red, green, blue);
    }

    public void addEquipmentSlot(EquipmentSlot equipmentSlot){
        this.slots.add(equipmentSlot);
    }

    public void removeEquipmentSlot(EquipmentSlot equipmentSlot){

    }
}
