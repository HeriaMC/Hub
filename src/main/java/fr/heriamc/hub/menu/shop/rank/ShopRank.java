package fr.heriamc.hub.menu.shop.rank;

import fr.heriamc.api.user.rank.HeriaRank;
import fr.heriamc.bukkit.utils.ItemBuilder;
import org.bukkit.DyeColor;
import org.bukkit.Material;

public enum ShopRank {

    VIP(HeriaRank.VIP, 5, 33, new ItemBuilder(Material.CLAY_BRICK).setName("§f» §eVIP §e%player_name%")
            .setLoreWithList("§7§oCette offre permet d'avoir",
                    "§7différents avantages ci-dessous.",
                    "",
                    "§7・§fPréfix : §eVIP §a✓",
                    "§7・§fDouble sauts au lobby. §a✓",
                    "§7・§f/tag §8(Désactive son grade) §a✓",
                    "§7・§fFile d'attente prioritaire. §a✓",
                    "§7・§fBooster de points §6x1.25 §a✓",
                    "§7・§fZone VIP §a✓",
                    "§7・§f10 hosts offerts. §a✓ ",
                    "",
                    "§fPrix: §d5 EUR",
                    "§6§l❱ §eClique pour acheter")),

    VIP_PLUS(HeriaRank.VIP_PLUS, 10, 32, new ItemBuilder(Material.IRON_INGOT).setName("§f» §3VIP+ §3%player_name%")
            .setLoreWithList("§7§oCette offre permet d'avoir",
                    "§7différents avantages ci-dessous.",
                    "",
                    "§7・§fAvantages précédents §a✓",
                    "§7・§fPréfix : §3VIP+ §a✓",
                    "§7・§fJumps infinis au lobby. §a✓",
                    "§7・§fMessage de connexion §8(non-personnalisable) §a✓",
                    "§7・§fBooster de points §6x1.50 §a✓",
                    "§7・§fFile d'attente prioritaire§3+ §a✓",
                    "§7・§fFaire un groupe de 6 personnes. §a✓",
                    "§7・§f15 hosts offerts. §a✓",
                    "",
                    "§fPrix: §d10 EUR",
                    "§6§l❱ §eClique pour acheter")),

    SUPER_VIP(HeriaRank.SUPER_VIP, 15, 30, new ItemBuilder(Material.INK_SACK, 1, DyeColor.BLUE.getDyeData()).setName("§f» §bSuperVIP §b%player_name%")
            .setLoreWithList("§7§oCette offre permet d'avoir",
                    "§7différents avantages ci-dessous.",
                    "",
                    "§7・§fAvantages précédents §a✓",
                    "§7・§fPréfix : §bSuperVIP §a✓",
                    "§7・§fFly au lobby. §a✓",
                    "§7・§fMessage de connexion §8(personnalisable) §a✓",
                    "§7・§fBooster de points §6x1.75 §a✓",
                    "§7・§fFile d'attente prioritaire§3++ §a✓",
                    "§7・§fFaire un groupe de 8 personnes. §a✓",
                    "§7・§f25 hosts offerts. §a✓",
                    "",
                    "§fPrix: §d15 EUR",
                    "§6§l❱ §eClique pour acheter")),

    SUPREME(HeriaRank.SUPREME, 20, 29, new ItemBuilder(Material.DIAMOND).setName("§f» §dSuprême §d%player_name%")
            .setLoreWithList("§7§oCette offre permet d'avoir",
                    "§7différents avantages ci-dessous.",
                    "",
                    "§7・§fAvantages précédents §a✓",
                    "§7・§fPréfix : §dSuprême §a✓",
                    "§7・§fMettre un §6§lGG §fen surbrillance. §a✓",
                    "§7・§fBooster de points §6x2 §a✓",
                    "§7・§fFile d'attente prioritaire§3+++ §a✓",
                    "§7・§fAnnoncer son host à tout le serveur §a✓",
                    "§7・§fFaire un groupe de 12 personnes. §a✓",
                    "§7・§fHosts illimités. §a✓",
                    "",
                    "§fPrix: §d20 EUR",
                    "§6§l❱ §eClique pour acheter")),

    CUSTOM(HeriaRank.CUSTOM, 15, 22, new ItemBuilder(Material.EMERALD).setName("§f» §fPersonnalisé %player_name%")
            .setLoreWithList("§7§oCette offre permet d'avoir",
                    "§7différents avantages ci-dessous.",
                    "",
                    "§7・§fAvantages précédents §a✓",
                    "§7・§fPréfix personnalisé §a✓",
                    "§7・§f/prefix §8(Permet de changer son prefix) §a✓",
                    "§7・§fFaire une annonce dans la boss bar. §a✓",
                    "§7・§fBooster de points §6x2.75 §a✓",
                    "§7・§fCosmétiques exclusifs au staff §a✓",
                    "§7・§fFaire un groupe de 16 personnes. §a✓",
                    "§7・§fFile d'attente prioritaire§3++++ §a✓",
                    "",
                    "§c→ Une fois l'achat effectué, vous pourrez utiliser",
                    "§cla commande /prefix pour changer votre préfix.",
                    "",
                    "§fPrix: §d15 EUR §8(Grade Suprême requis)",
                    "§6§l❱ §eClique pour acheter")),

    ;

    private final HeriaRank converter;
    private final int price;
    private final int slot;
    private final ItemBuilder itemBuilder;

    ShopRank(HeriaRank converter, int price, int slot, ItemBuilder itemBuilder) {
        this.converter = converter;
        this.price = price;
        this.slot = slot;
        this.itemBuilder = itemBuilder;
    }

    public HeriaRank getConverter() {
        return converter;
    }

    public int getPrice() {
        return price;
    }

    public int getSlot() {
        return slot;
    }

    public ItemBuilder getItemBuilder() {
        return itemBuilder;
    }
}
