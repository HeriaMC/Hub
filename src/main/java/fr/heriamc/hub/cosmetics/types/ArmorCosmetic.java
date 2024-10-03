package fr.heriamc.hub.cosmetics.types;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public enum ArmorCosmetic {

    ;

    private final String id;
    private final ItemStack armor;

    private static final List<ArmorCosmetic> cosmetics = List.of(ArmorCosmetic.values());

    ArmorCosmetic(String id, ItemStack armor) {
        this.id = id;
        this.armor = armor;
    }

    public String getId() {
        return id;
    }

    public ItemStack getArmor() {
        return armor;
    }

    public static ArmorCosmetic getFromId(String id){
        for (ArmorCosmetic cosmetic : cosmetics) {
            if(cosmetic.getId().equals(id)){
                return cosmetic;
            }
        }
        return null;
    }

}
