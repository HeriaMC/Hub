package fr.heriamc.hub.cosmetics;

import fr.heriamc.api.user.HeriaPlayer;
import fr.heriamc.hub.HeriaHub;
import fr.heriamc.hub.data.HubPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CosmeticManager {

    private final HeriaHub hub;

    public CosmeticManager(HeriaHub hub) {
        this.hub = hub;
    }

    public List<CosmeticSubType> getCosmeticsForType(CosmeticType type){

        List<CosmeticSubType> cosmeticSubTypes = new ArrayList<>(List.of(CosmeticSubType.DISABLE));
        cosmeticSubTypes.addAll(Arrays.stream(CosmeticSubType.values())
                .filter(cosmetic -> cosmetic.getCosmeticType() == type)
                .toList());

        return cosmeticSubTypes;
    }

    public void unEquipPlayer(Player player){
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);

        hub.getServer().dispatchCommand(hub.getServer().getConsoleSender(), "pc unequip " + player.getName() + " particle-effects");
        hub.getServer().dispatchCommand(hub.getServer().getConsoleSender(), "pc unequip " + player.getName() + " miniatures");
    }

    public void equipPlayer(Player player, HubPlayer hubPlayer){
        Bukkit.getScheduler().runTaskLater(hub, () -> {
            if(hubPlayer.getEquippedParticle() != null){
                CosmeticType.PARTICLE.equip(hub, player, hubPlayer.getEquippedParticle().getId());
            }

            if(hubPlayer.getEquippedMiniature() != null){
                CosmeticType.MINIATURES.equip(hub, player, hubPlayer.getEquippedMiniature().getId());
            }
        }, 10L);
    }

    public void announceArrival(Player player, HubPlayer hubPlayer){
        HeriaPlayer heriaPlayer = hub.getBukkitAPI().getApi().getPlayerManager().get(player.getUniqueId());
        Bukkit.broadcastMessage(heriaPlayer.getRank().getPrefix() + heriaPlayer.getNickedName() + " §fa rejoint le lobby !");
    }

}
