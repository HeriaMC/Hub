package fr.heriamc.hub.cosmetics;

import fr.heriamc.bukkit.utils.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;

public enum CosmeticSubType {

    //paticules

    DISABLE(null, null, null, new ItemBuilder(Material.BARRIER).setName("§cDéséquiper les cosmétiques")),

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

    COW(CosmeticType.MINIATURES, CosmeticRarity.RARE, "cow", new ItemBuilder(Material.LEATHER).setName("Vache")),
    SKELETON(CosmeticType.MINIATURES, CosmeticRarity.RARE, "skeleton", new ItemBuilder(Material.ARROW).setName("Squelette")),
    SHEEP(CosmeticType.MINIATURES, CosmeticRarity.RARE, "sheep", new ItemBuilder(Material.WOOL).setName("Mouton")),
    CHICKEN(CosmeticType.MINIATURES, CosmeticRarity.RARE, "chicken", new ItemBuilder(Material.FEATHER).setName("Poulet")),
    ZOMBIE(CosmeticType.MINIATURES, CosmeticRarity.RARE, "zombie", new ItemBuilder(Material.ROTTEN_FLESH).setName("Zombie")),
    IRON_GOLEM(CosmeticType.MINIATURES, CosmeticRarity.RARE, "iron-golem", new ItemBuilder(Material.IRON_INGOT).setName("Golem de fer")),
    SPIDER(CosmeticType.MINIATURES, CosmeticRarity.RARE, "spider", new ItemBuilder(Material.WEB).setName("Araignée")),

    WITCH(CosmeticType.MINIATURES, CosmeticRarity.EPIC, "witch", new ItemBuilder(Material.POTION).setName("Sorcière")),
    BUNNY(CosmeticType.MINIATURES, CosmeticRarity.EPIC, "bunny", new ItemBuilder(Material.RABBIT_HIDE).setName("Lapin")),
    ELEPHANT(CosmeticType.MINIATURES, CosmeticRarity.EPIC, "elephant", new ItemBuilder(Material.COAL).setName("Elephant")),
    PANDA(CosmeticType.MINIATURES, CosmeticRarity.EPIC, "panda", new ItemBuilder(Material.SUGAR_CANE).setName("Panda")),
    DOLPHIN(CosmeticType.MINIATURES, CosmeticRarity.EPIC, "dolphin", new ItemBuilder(Material.PRISMARINE_CRYSTALS).setName("Dauphin")),
    DOGE(CosmeticType.MINIATURES, CosmeticRarity.EPIC, "doge", new ItemBuilder(Material.BONE).setName("Chien")),
    GHOST(CosmeticType.MINIATURES, CosmeticRarity.EPIC, "ghost", new ItemBuilder(Material.GHAST_TEAR).setName("Fantome")),

    TURTLE(CosmeticType.MINIATURES, CosmeticRarity.MYTHIC, "turtle", new ItemBuilder(Material.INK_SACK, 1, DyeColor.LIME.getDyeData()).setName("Tortue")),
    SNAIL(CosmeticType.MINIATURES, CosmeticRarity.MYTHIC, "snail", new ItemBuilder(Material.INK_SACK, 1, DyeColor.BROWN.getDyeData()).setName("Serpent")),
    FOX(CosmeticType.MINIATURES, CosmeticRarity.MYTHIC, "fox", new ItemBuilder(Material.WOOL, 1, DyeColor.ORANGE.getWoolData()).setName("Renard")),
    TIGER(CosmeticType.MINIATURES, CosmeticRarity.MYTHIC, "tiger", new ItemBuilder(Material.INK_SACK, 1, DyeColor.ORANGE.getDyeData()).setName("Tigre")),
    VAMPIRE(CosmeticType.MINIATURES, CosmeticRarity.MYTHIC, "vampire", new ItemBuilder(Material.REDSTONE).setName("Vampire")),
    TIKI(CosmeticType.MINIATURES, CosmeticRarity.MYTHIC, "tiki", new ItemBuilder(Material.WOOD, 1, 1).setName("Tiki")),
    SNOWMAN(CosmeticType.MINIATURES, CosmeticRarity.MYTHIC, "snowman", new ItemBuilder(Material.SNOW_BLOCK).setName("Bonhomme de neige")),
    DWARF(CosmeticType.MINIATURES, CosmeticRarity.MYTHIC, "dwarf", new ItemBuilder(Material.EMERALD).setName("Nain")),

    RONALD(CosmeticType.MINIATURES, CosmeticRarity.LEGENDARY, "ronald", new ItemBuilder(Material.GOLD_INGOT).setName("Ronald")),
    DONALD_DUCK(CosmeticType.MINIATURES, CosmeticRarity.LEGENDARY, "donald-duck", new ItemBuilder(Material.GOLD_BLOCK).setName("Donald")),
    MARIO(CosmeticType.MINIATURES, CosmeticRarity.LEGENDARY, "mario", new ItemBuilder(Material.WOOL, 1, DyeColor.RED.getWoolData()).setName("Mario")),
    YOSHI(CosmeticType.MINIATURES, CosmeticRarity.LEGENDARY, "yoshi", new ItemBuilder(Material.WOOL, 1, DyeColor.LIME.getWoolData()).setName("Yoshi")),
    TOAD(CosmeticType.MINIATURES, CosmeticRarity.LEGENDARY, "toad", new ItemBuilder(Material.WOOL, 1, DyeColor.WHITE.getWoolData()).setName("Toad")),
    STITCH(CosmeticType.MINIATURES, CosmeticRarity.LEGENDARY, "stitch", new ItemBuilder(Material.WOOL, 1, DyeColor.BLUE.getWoolData()).setName("Stich")),
    SHARK(CosmeticType.MINIATURES, CosmeticRarity.LEGENDARY, "shark", new ItemBuilder(Material.WATER_BUCKET).setName("Requin")),
    SANTA_CLAUS(CosmeticType.MINIATURES, CosmeticRarity.LEGENDARY, "santa-claus", new ItemBuilder(Material.CHEST).setName("Père noël")),

    BLUE_ARMOR_HEAD(CosmeticType.ARMOR, CosmeticRarity.EPIC, "blue-armor.head", new ItemBuilder(Material.LEATHER_HELMET).setName("Casque bleu").allFlags().setLeatherArmorColor(Color.BLUE)),
    BLUE_ARMOR_CHEST(CosmeticType.ARMOR, CosmeticRarity.EPIC, "blue-armor.chest", new ItemBuilder(Material.LEATHER_CHESTPLATE).setName("Plastron bleu").allFlags().setLeatherArmorColor(Color.BLUE)),
    BLUE_ARMOR_LEGS(CosmeticType.ARMOR, CosmeticRarity.EPIC, "blue-armor.legs", new ItemBuilder(Material.LEATHER_LEGGINGS).setName("Jambières bleues").allFlags().setLeatherArmorColor(Color.BLUE)),
    BLUE_ARMOR_FEET(CosmeticType.ARMOR, CosmeticRarity.EPIC, "blue-armor.feet", new ItemBuilder(Material.LEATHER_BOOTS).setName("Bottes bleues").allFlags().setLeatherArmorColor(Color.BLUE)),

    GREEN_ARMOR_HEAD(CosmeticType.ARMOR, CosmeticRarity.EPIC, "green-armor.head",
            new ItemBuilder(Material.LEATHER_HELMET)
                    .setName("Casque vert")
                    .allFlags()
                    .setLeatherArmorColor(Color.GREEN)),
    GREEN_ARMOR_CHEST(CosmeticType.ARMOR, CosmeticRarity.EPIC, "green-armor.chest",
            new ItemBuilder(Material.LEATHER_CHESTPLATE)
                    .setName("Plastron vert")
                    .allFlags()
                    .setLeatherArmorColor(Color.GREEN)),
    GREEN_ARMOR_LEGS(CosmeticType.ARMOR, CosmeticRarity.EPIC, "green-armor.legs",
            new ItemBuilder(Material.LEATHER_LEGGINGS)
                    .setName("Jambières vertes")
                    .allFlags()
                    .setLeatherArmorColor(Color.GREEN)),
    GREEN_ARMOR_FEET(CosmeticType.ARMOR, CosmeticRarity.EPIC, "green-armor.feet",
            new ItemBuilder(Material.LEATHER_BOOTS)
                    .setName("Bottes vertes")
                    .allFlags()
                    .setLeatherArmorColor(Color.GREEN)),

    RED_ARMOR_HEAD(CosmeticType.ARMOR, CosmeticRarity.EPIC, "red-armor.head",
            new ItemBuilder(Material.LEATHER_HELMET)
                    .setName("Casque rouge")
                    .allFlags()
                    .setLeatherArmorColor(Color.RED)),
    RED_ARMOR_CHEST(CosmeticType.ARMOR, CosmeticRarity.EPIC, "red-armor.chest",
            new ItemBuilder(Material.LEATHER_CHESTPLATE)
                    .setName("Plastron rouge")
                    .allFlags()
                    .setLeatherArmorColor(Color.RED)),
    RED_ARMOR_LEGS(CosmeticType.ARMOR, CosmeticRarity.EPIC, "red-armor.legs",
            new ItemBuilder(Material.LEATHER_LEGGINGS)
                    .setName("Jambières rouges")
                    .allFlags()
                    .setLeatherArmorColor(Color.RED)),
    RED_ARMOR_FEET(CosmeticType.ARMOR, CosmeticRarity.EPIC, "red-armor.feet",
            new ItemBuilder(Material.LEATHER_BOOTS)
                    .setName("Bottes rouges")
                    .allFlags()
                    .setLeatherArmorColor(Color.RED)),

    YELLOW_ARMOR_HEAD(CosmeticType.ARMOR, CosmeticRarity.EPIC, "yellow-armor.head",
            new ItemBuilder(Material.LEATHER_HELMET)
                    .setName("Casque jaune")
                    .allFlags()
                    .setLeatherArmorColor(Color.YELLOW)),
    YELLOW_ARMOR_CHEST(CosmeticType.ARMOR, CosmeticRarity.EPIC, "yellow-armor.chest",
            new ItemBuilder(Material.LEATHER_CHESTPLATE)
                    .setName("Plastron jaune")
                    .allFlags()
                    .setLeatherArmorColor(Color.YELLOW)),
    YELLOW_ARMOR_LEGS(CosmeticType.ARMOR, CosmeticRarity.EPIC, "yellow-armor.legs",
            new ItemBuilder(Material.LEATHER_LEGGINGS)
                    .setName("Jambières jaunes")
                    .allFlags()
                    .setLeatherArmorColor(Color.YELLOW)),
    YELLOW_ARMOR_FEET(CosmeticType.ARMOR, CosmeticRarity.EPIC, "yellow-armor.feet",
            new ItemBuilder(Material.LEATHER_BOOTS)
                    .setName("Bottes jaunes")
                    .allFlags()
                    .setLeatherArmorColor(Color.YELLOW)),

    RAINBOW_ARMOR_HEAD(CosmeticType.ARMOR, CosmeticRarity.EPIC, "rainbow-armor.head",
            new ItemBuilder(Material.LEATHER_HELMET)
                    .setName("Casque arc-en-ciel")
                    .allFlags()
                    .setLeatherArmorColor(Color.WHITE)),
    RAINBOW_ARMOR_CHEST(CosmeticType.ARMOR, CosmeticRarity.EPIC, "rainbow-armor.chest",
            new ItemBuilder(Material.LEATHER_CHESTPLATE)
                    .setName("Plastron arc-en-ciel")
                    .allFlags()
                    .setLeatherArmorColor(Color.WHITE)),
    RAINBOW_ARMOR_LEGS(CosmeticType.ARMOR, CosmeticRarity.EPIC, "rainbow-armor.legs",
            new ItemBuilder(Material.LEATHER_LEGGINGS)
                    .setName("Jambières arc-en-ciel")
                    .allFlags()
                    .setLeatherArmorColor(Color.WHITE)),
    RAINBOW_ARMOR_FEET(CosmeticType.ARMOR, CosmeticRarity.EPIC, "rainbow-armor.feet",
            new ItemBuilder(Material.LEATHER_BOOTS)
                    .setName("Bottes arc-en-ciel")
                    .allFlags()
                    .setLeatherArmorColor(Color.WHITE));

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

    public static CosmeticSubType getFromId(String id){
        for (CosmeticSubType cosmetic : values()) {
            if(cosmetic.getId() == null){
                continue;
            }
            if(cosmetic.getId().equals(id)){
                return cosmetic;
            }
        }
        return null;
    }
}
