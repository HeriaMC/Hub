package fr.heriamc.hub.cosmetics;

import fr.heriamc.hub.HeriaHub;

import java.util.Arrays;
import java.util.List;

public class CosmeticManager {

    private final HeriaHub hub;

    public CosmeticManager(HeriaHub hub) {
        this.hub = hub;
    }

    public List<CosmeticSubType> getCosmeticsForType(CosmeticType type){
        return Arrays.stream(CosmeticSubType.values())
                .filter(cosmetic -> cosmetic.getCosmeticType() == type)
                .toList();
    }
}
