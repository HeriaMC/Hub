package fr.heriamc.hub.cosmetics;

public enum CosmeticRarity {

    RARE("§9", "RARE", 1000, 1),
    EPIC("§5", "EPIQUE", 2000, 2),
    MYTHIC("§c", "MYTHIQUE", 3000, 3),
    LEGENDARY("§6", "LÉGENDAIRE", 4000, 4),

    ;

    private final String color;
    private final String name;
    private final float coinsPrice;
    private final float creditsPrice;

    CosmeticRarity(String color, String name, float coinsPrice, float creditsPrice) {
        this.color = color;
        this.name = name;
        this.coinsPrice = coinsPrice;
        this.creditsPrice = creditsPrice;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String getColoredName(){
        return color +  "§l" + name;
    }

    public float getCoinsPrice() {
        return coinsPrice;
    }

    public float getCreditsPrice() {
        return creditsPrice;
    }
}
