package fr.heriamc.hub.lootbox;

import fr.heriamc.api.utils.HeriaSkull;
import fr.heriamc.bukkit.utils.ItemBuilder;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.security.SecureRandom;

public enum LootboxPrize {


    PARTICLE_RARE(new ItemBuilder(Material.NETHER_STAR).setName("§9Une particule rare"), HeriaSkull.BOX_BLUE, 9),
    PARTICLE_EPIC(new ItemBuilder(Material.NETHER_STAR).setName("§5Une particule épique"), HeriaSkull.BOX_MAGENTA, 8),
    PARTICLE_MYTHIC(new ItemBuilder(Material.NETHER_STAR).setName("§cUne particule mythique"), HeriaSkull.BOX_RED, 7),
    PARTICLE_LEGENDARY(new ItemBuilder(Material.NETHER_STAR).setName("§6Une particule légendaire"), HeriaSkull.BOX_ORANGE, 6),

    PET_RARE(new ItemBuilder(Material.DOUBLE_PLANT).setName("§9Un compagnon rare"), HeriaSkull.BOX_BLUE, 9),
    PET_EPIC(new ItemBuilder(Material.DOUBLE_PLANT).setName("§5Un compagnon épique"), HeriaSkull.BOX_MAGENTA, 8),
    PET_MYTHIC(new ItemBuilder(Material.DOUBLE_PLANT).setName("§cUn compagnon mythique"), HeriaSkull.BOX_RED, 7),
    PET_LEGENDARY(new ItemBuilder(Material.DOUBLE_PLANT).setName("§6Un compagnon légendaire"), HeriaSkull.BOX_ORANGE, 6),

    ARMOR(new ItemBuilder(Material.LEATHER_CHESTPLATE).setName("§dUne armure"), HeriaSkull.BOX_PINK, 7),
    CONNECTION_MESSAGE(new ItemBuilder(Material.PAPER).setName("§aUn message de connection"), HeriaSkull.BOX_GREEN, 7),

    CREDIT_10(new ItemBuilder(Material.INK_SACK, 4, DyeColor.PURPLE.getDyeData()).setName("§d4 EUR de crédit"), HeriaSkull.BOX_MAGENTA, 5),
    CREDIT_15(new ItemBuilder(Material.INK_SACK, 6, DyeColor.PURPLE.getDyeData()).setName("§d6 EUR de crédit"), HeriaSkull.BOX_MAGENTA, 3),

    COINS_2500(new ItemBuilder(Material.GOLD_INGOT, 25).setName("§e2500 points"), HeriaSkull.BOX_YELLOW, 10),
    COINS_5000(new ItemBuilder(Material.GOLD_INGOT, 50).setName("§e5000 points"), HeriaSkull.BOX_YELLOW, 8),

    ;

    private final ItemBuilder item;
    private final HeriaSkull skull;
    private final int percent;
    private final ItemStack skullItem;


    LootboxPrize(ItemBuilder item, HeriaSkull skull, int percent) {
        this.item = item;
        this.skull = skull;
        this.percent = percent;
        this.skullItem = new ItemBuilder(Material.SKULL_ITEM, 1, (short) 3)
                .setSkullURL(this.getSkull().getURL()).build();
    }

    public ItemBuilder getItem() {
        return item;
    }

    public HeriaSkull getSkull() {
        return skull;
    }

    public int getPercent() {
        return percent;
    }

    public ItemStack getSkullItem() {
        return skullItem;
    }

    public static LootboxPrize getRandomPrize() {
        SecureRandom random = new SecureRandom();
        int totalPercent = 0;

        for (LootboxPrize prize : values()) {
            totalPercent += prize.getPercent();
        }
        int randomNumber = random.nextInt(totalPercent);
        int cumulativeSum = 0;

        for (LootboxPrize prize : values()) {
            cumulativeSum += prize.getPercent();
            if (randomNumber < cumulativeSum) {
                return prize;
            }
        }

        //if not found :c
        return PARTICLE_RARE;
    }
}
