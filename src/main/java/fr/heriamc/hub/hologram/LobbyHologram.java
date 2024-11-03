package fr.heriamc.hub.hologram;

import fr.heriamc.hub.HeriaHub;
import fr.heriamc.hub.utils.hologram.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;

public class LobbyHologram implements Listener {

    private final HeriaHub hub;
    private final List<Hologram> holograms = new ArrayList<>();
    private final List<Hologram> boxHolograms = new ArrayList<>();

    public LobbyHologram(HeriaHub hub) {
        this.hub = hub;

        World world = Bukkit.getWorld("world");
        holograms.add(new Hologram.Builder(hub, new Location(world, -60.5, 49, 0.5))
                .withLine("§e§l» §6§lHERIAMC §e§l«")
                .withBlankLine()
                .withLine("§fHeriaMC est un serveur Mini-Jeux")
                .withLine("§faussi avec du FFA, parfait pour")
                .withLine("§fs'amuser entre amis !")
                .withBlankLine()
                .withLine("§a» §fBoutique: §ashop.heriamc.fr")
                .withLine("§e» §fSite Web: §ewww.heriamc.fr")
                .build());

        Hologram.Builder lootbox = new Hologram.Builder(hub)
                .withLine("§5§lLOOTBOX")
                .withBlankLine()
                .withLine("§fGagnez des récompenses uniques")
                .withLine("§fen ouvrant des lootboxes !")
                .withBlankLine();

        Hologram box1 = lootbox.withLocation(new Location(world, -60.5, 49.5, 15.5)).build();
        Hologram box2 = lootbox.withLocation(new Location(world, -60.5, 49.5, -14.5)).build();

        holograms.add(box1);
        holograms.add(box2);

        this.boxHolograms.add(box1);
        this.boxHolograms.add(box2);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        holograms.forEach(hologram -> hologram.addReceiver(e.getPlayer()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        holograms.forEach(hologram -> hologram.removeReceiver(e.getPlayer()));
    }

    public Hologram getFromBox(float z){
        if(z > 0){
            return boxHolograms.get(0);
        } else {
            return boxHolograms.get(1);
        }
    }
}
