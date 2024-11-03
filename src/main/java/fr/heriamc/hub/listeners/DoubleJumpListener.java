package fr.heriamc.hub.listeners;

import fr.heriamc.api.data.LocalDataExpiration;
import fr.heriamc.api.data.LocalDataManager;
import fr.heriamc.api.user.HeriaPlayer;
import fr.heriamc.api.user.rank.HeriaRank;
import fr.heriamc.hub.HeriaHub;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

import java.util.concurrent.TimeUnit;

public class DoubleJumpListener extends LocalDataManager<Player, Boolean> implements Listener {

    private final HeriaHub hub;

    public DoubleJumpListener(HeriaHub hub) {
        super(new LocalDataExpiration(3, TimeUnit.SECONDS));
        this.hub = hub;
    }

    @EventHandler
    public void onFlightToggle(PlayerToggleFlightEvent e){
        Player player = e.getPlayer();
        HeriaPlayer heriaPlayer = hub.getBukkitAPI().getApi().getPlayerManager().get(player.getUniqueId());

        if(heriaPlayer.getRank().getPower() >= HeriaRank.SUPER_VIP.getPower()){
            return;
        }

        e.setCancelled(true);

        if(this.getInLocal(player) != null){
            return;
        }

        Vector multiply = player.getLocation().getDirection().setY(2.0D);
        player.setVelocity(multiply);

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.playSound(player.getLocation(), Sound.BAT_TAKEOFF, 1, 1);

            for (int i = 0; i < 10; i++) {
                onlinePlayer.getWorld().playEffect(player.getLocation(), Effect.CLOUD, 1, 1);
            }
        }


        if(heriaPlayer.getRank().getPower() >= HeriaRank.VIP_PLUS.getPower()){
            return;
        }

        this.putInLocal(player, true);


    }
}
