package fr.heriamc.hub.cosmetics;

import fr.heriamc.bukkit.utils.ItemBuilder;
import org.bukkit.DyeColor;
import org.bukkit.Material;

public enum CosmeticSubType {

    //paticules

    EMERALD_TWIRL(CosmeticType.PARTICLE, CosmeticRarity.RARE, "emerald-twirl", new ItemBuilder(Material.EMERALD).setName("Tourbillon émeraude")),
    ENCHANTED(CosmeticType.PARTICLE, CosmeticRarity.RARE, "enchanted", new ItemBuilder(Material.BOOK).setName("Enchanté")),
    PARTY_TIME(CosmeticType.PARTICLE, CosmeticRarity.RARE, "party-time", new ItemBuilder(Material.INK_SACK, 1, DyeColor.PURPLE.getDyeData()).setName("Fête")),
    IN_LOVE(CosmeticType.PARTICLE, CosmeticRarity.RARE, "in-love", new ItemBuilder(Material.RED_ROSE).setName("Amoureux")),
    CRUSHED_CANDY_CANE(CosmeticType.PARTICLE, CosmeticRarity.RARE, "crushed-candy-cane", new ItemBuilder(Material.SUGAR).setName("Bonbons écrasés")),

    RAIN_CLOUD(CosmeticType.PARTICLE, CosmeticRarity.EPIC, "rain-cloud", new ItemBuilder(Material.INK_SACK, 1, DyeColor.BLUE.getDyeData()).setName("Nuage de pluie")),
    MUSIC(CosmeticType.PARTICLE, CosmeticRarity.EPIC, "music", new ItemBuilder(Material.NOTE_BLOCK).setName("Musique")),
    DEMON_TWIST(CosmeticType.PARTICLE, CosmeticRarity.EPIC, "demon-twist", new ItemBuilder(Material.COAL, 1, (short) 1).setName("Torsion démoniaque")),
    PROTECTIVE_SHIELD(CosmeticType.PARTICLE, CosmeticRarity.EPIC, "protective-shield", new ItemBuilder(Material.INK_SACK, 1, DyeColor.CYAN.getDyeData()).setName("Bouclier protecteur")),
    BIG_HEART(CosmeticType.PARTICLE, CosmeticRarity.EPIC, "big-heart", new ItemBuilder(Material.YELLOW_FLOWER).setName("Grand coeur")),
    STAR(CosmeticType.PARTICLE, CosmeticRarity.EPIC, "star", new ItemBuilder(Material.NETHER_STAR).setName("Etoile")),
    SNOW_CLOUD(CosmeticType.PARTICLE, CosmeticRarity.EPIC, "snow-cloud", new ItemBuilder(Material.SNOW_BLOCK).setName("Nuage de neige")),

    FROST_LORD(CosmeticType.PARTICLE, CosmeticRarity.MYTHIC, "frost-lord", new ItemBuilder(Material.SNOW_BALL).setName("Seigneur du givre")),
    LEGENDARY_AURA(CosmeticType.PARTICLE, CosmeticRarity.MYTHIC, "legendary-aura", new ItemBuilder(Material.EYE_OF_ENDER).setName("Aura légendaire")),
    TORNADO(CosmeticType.PARTICLE, CosmeticRarity.MYTHIC, "tornado", new ItemBuilder(Material.WEB).setName("Tornade")),
    VAMPIRE_WINGS(CosmeticType.PARTICLE, CosmeticRarity.MYTHIC, "vampire-wings", new ItemBuilder(Material.FIREWORK_CHARGE).setName("Ailes de vampire")),
    BLACK_HOLE(CosmeticType.PARTICLE, CosmeticRarity.MYTHIC, "black-hole", new ItemBuilder(Material.COAL_BLOCK).setName("Trou noir")),
    COLORFUL_TRAIL(CosmeticType.PARTICLE, CosmeticRarity.MYTHIC, "colorful-trail", new ItemBuilder(Material.INK_SACK, 1, DyeColor.MAGENTA.getDyeData()).setName("Piste colorée")),

    YIN_AND_YONG(CosmeticType.PARTICLE, CosmeticRarity.LEGENDARY, "yin-and-yang", new ItemBuilder(Material.MELON_SEEDS).setName("Yin et Yang")),
    FLAME_OF_THE_DEMONS(CosmeticType.PARTICLE, CosmeticRarity.LEGENDARY, "flame-of-the-demons", new ItemBuilder(Material.BLAZE_POWDER).setName("Flamme des démons")),
    FLAME_OF_MAGIC(CosmeticType.PARTICLE, CosmeticRarity.LEGENDARY, "flame-of-magic", new ItemBuilder(Material.BLAZE_ROD).setName("Flamme de la magie")),
    ANGEL_WINGS(CosmeticType.PARTICLE, CosmeticRarity.LEGENDARY, "angel-wings", new ItemBuilder(Material.INK_SACK, 1, DyeColor.GRAY.getDyeData()).setName("Ailes d'ange")),
    UMBRELLA(CosmeticType.PARTICLE, CosmeticRarity.LEGENDARY, "umbrella", new ItemBuilder(Material.HOPPER).setName("Parapluie")),

    //miniautres


    ;

    private final CosmeticType cosmeticType;
    private final CosmeticRarity rarity;
    private final String id;
    private final ItemBuilder representation;

    CosmeticSubType(CosmeticType cosmeticType, CosmeticRarity rarity, String id, ItemBuilder representation) {
        this.cosmeticType = cosmeticType;
        this.rarity = rarity;
        this.id = id;
        this.representation = representation;
    }

    public CosmeticType getCosmeticType() {
        return cosmeticType;
    }

    public CosmeticRarity getRarity() {
        return rarity;
    }

    public String getId() {
        return id;
    }

    public ItemBuilder getRepresentation() {
        return representation;
    }
}
