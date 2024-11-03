package fr.heriamc.hub.data;

import fr.heriamc.api.data.SerializableData;
import fr.heriamc.hub.cosmetics.CosmeticSubType;

import java.util.UUID;

public class HubPlayer implements SerializableData<UUID> {

    private UUID id;
    private String name;

    private CosmeticSubType equippedParticle;
    private CosmeticSubType equippedMiniature;

    private CosmeticSubType equippedJoinMessage;

    private CosmeticSubType equippedHelmet;
    private CosmeticSubType equippedChestplate;
    private CosmeticSubType equippedLeggings;
    private CosmeticSubType equippedBoots;

    public HubPlayer(UUID uuid, String name, CosmeticSubType equippedParticle, CosmeticSubType equippedMiniature, CosmeticSubType equippedJoinMessage, CosmeticSubType equippedHelmet, CosmeticSubType equippedChestplate, CosmeticSubType equippedLeggings, CosmeticSubType equippedBoots) {
        this.id = uuid;
        this.name = name;
        this.equippedParticle = equippedParticle;
        this.equippedMiniature = equippedMiniature;
        this.equippedJoinMessage = equippedJoinMessage;
        this.equippedHelmet = equippedHelmet;
        this.equippedChestplate = equippedChestplate;
        this.equippedLeggings = equippedLeggings;
        this.equippedBoots = equippedBoots;
    }

    public UUID getId() {
        return id;
    }

    public HubPlayer setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public HubPlayer setName(String name) {
        this.name = name;
        return this;
    }

    public CosmeticSubType getEquippedParticle() {
        return equippedParticle;
    }

    public HubPlayer setEquippedParticle(CosmeticSubType equippedParticle) {
        this.equippedParticle = equippedParticle;
        return this;
    }

    public CosmeticSubType getEquippedMiniature() {
        return equippedMiniature;
    }

    public HubPlayer setEquippedMiniature(CosmeticSubType equippedMiniature) {
        this.equippedMiniature = equippedMiniature;
        return this;
    }

    public CosmeticSubType getEquippedJoinMessage() {
        return equippedJoinMessage;
    }

    public HubPlayer setEquippedJoinMessage(CosmeticSubType equippedJoinMessage) {
        this.equippedJoinMessage = equippedJoinMessage;
        return this;
    }

    public CosmeticSubType getEquippedHelmet() {
        return equippedHelmet;
    }

    public HubPlayer setEquippedHelmet(CosmeticSubType equippedHelmet) {
        this.equippedHelmet = equippedHelmet;
        return this;
    }

    public CosmeticSubType getEquippedChestplate() {
        return equippedChestplate;
    }

    public HubPlayer setEquippedChestplate(CosmeticSubType equippedChestplate) {
        this.equippedChestplate = equippedChestplate;
        return this;
    }

    public CosmeticSubType getEquippedLeggings() {
        return equippedLeggings;
    }

    public HubPlayer setEquippedLeggings(CosmeticSubType equippedLeggings) {
        this.equippedLeggings = equippedLeggings;
        return this;
    }

    public CosmeticSubType getEquippedBoots() {
        return equippedBoots;
    }

    public HubPlayer setEquippedBoots(CosmeticSubType equippedBoots) {
        this.equippedBoots = equippedBoots;
        return this;
    }

    @Override
    public UUID getIdentifier() {
        return id;
    }

    @Override
    public void setIdentifier(UUID uuid) {
        this.id = uuid;
    }
}
