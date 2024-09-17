package fr.heriamc.hub.npc;

import fr.heriamc.api.server.HeriaServerType;
import fr.heriamc.hub.HeriaHub;
import net.jitse.npclib.api.NPC;
import net.jitse.npclib.api.events.NPCInteractEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class NPCManager {

    private final HeriaHub hub;
    private final Map<NPC, Consumer<NPCInteractEvent>> npcs = new HashMap<>();
    private final Map<NPCLoader, HeriaServerType> playersRefresh = new HashMap<>();
    private final HashMap<HeriaServerType, Integer> lastPlayers = new HashMap<>();

    private final World world = Bukkit.getWorld("world");

    public NPCManager(HeriaHub hub) {
        this.hub = hub;

        ScheduledExecutorService refresh = Executors.newScheduledThreadPool(2);

        refresh.scheduleAtFixedRate(() -> playersRefresh.forEach((npcLoader, remoteString) -> {
            if(lastPlayers.containsKey(remoteString)){
                int size;

                if(lastPlayers.get(remoteString) != (size = hub.getBukkitAPI().getApi().getServerManager().getAllPlayersOnServerType(remoteString))){
                    npcLoader.setLines(1, "§fJoueurs en jeu: §a" + size);
                    lastPlayers.put(remoteString,size);
                }
            }
        }),5,5, TimeUnit.SECONDS);
    }

    public void initNpc(){
        NPCLoader hika = createNpc(new Location(world, 40.5, 72, -0.5, 90, 0), 1784272602, "§3§lHIKABRAIN", "§fJoueurs en jeu: §a0", "", "§6» §eClique §6«");
        npcs.put(hika.getNpc(), e -> {e.getWhoClicked().sendMessage("Hikabrain cliqué");});
        putToRefreshPlayers(hika, HeriaServerType.HIKABRAIN);

        NPCLoader freecube = createNpc(new Location(world, 40.5, 72, 3.5, 95.5F, 0), 631879224, "§a§lFREECUBE", "§fJoueurs en jeu: §a0", "", "§6» §eClique §6«");
        npcs.put(freecube.getNpc(), e -> {e.getWhoClicked().sendMessage("Freecube cliqué");});
        //TODO: change
        putToRefreshPlayers(hika, HeriaServerType.RUSHFFA);

        NPCLoader rushffa = createNpc(new Location(world, 40.5, 72, -4.5, 84.5F, 0), 1173467410, "§e§lRUSHFFA", "§fJoueurs en jeu: §a0", "", "§6» §eClique §6«");
        npcs.put(rushffa.getNpc(), e -> {e.getWhoClicked().sendMessage("Rushffa cliqué");});
        putToRefreshPlayers(hika, HeriaServerType.RUSHFFA);

        NPCLoader oneshot = createNpc(new Location(world, 38.5, 72, 7.5, 125F, 0), 798143114, "§6§lONESHOT", "§fJoueurs en jeu: §a0", "", "§6» §eClique §6«");
        npcs.put(oneshot.getNpc(), e -> {e.getWhoClicked().sendMessage("Oneshot cliqué");});
        putToRefreshPlayers(hika, HeriaServerType.ONESHOT);

        NPCLoader soon1 = createNpc(new Location(world, 38.5, 72, -8.5, 55F, 0), 1229866094, "§c§lPROCHAINEMENT", "§fJoueurs en jeu: §c§k000", "", "§6» §eClique §6«");
        npcs.put(soon1.getNpc(), e -> {});

        NPCLoader soon2 = createNpc(new Location(world, 34.5, 72, 9.5, 143F, 0), 1229866094, "§c§lPROCHAINEMENT", "§fJoueurs en jeu: §c§k000", "", "§6» §eClique §6«");
        npcs.put(soon2.getNpc(), e -> {});

        NPCLoader soon3 = createNpc(new Location(world, 34.5, 72, -10.5, 37F, 0), 1229866094, "§c§lPROCHAINEMENT", "§fJoueurs en jeu: §c§k000", "", "§6» §eClique §6«");
        npcs.put(soon3.getNpc(), e -> {});

    }

    private NPCLoader createNpc(Location location, int skinId, String... lines){
        return new NPCLoader(location,skinId,lines);
    }

    public void putToRefreshPlayers(NPCLoader loader, HeriaServerType executor){
        playersRefresh.put(loader, executor);
        lastPlayers.put(executor, 0);
    }

    public void displayNpc(Player player){
        for(NPC npc : getNpc()){
            npc.create();
            npc.show(player);
        }
    }

    public Consumer<NPCInteractEvent> getConsumer(NPC npc){
        return this.npcs.get(npc);
    }

    public List<NPC> getNpc(){
        return new ArrayList<>(this.npcs.keySet());
    }
}
