package fr.heriamc.hub.npc;

import fr.heriamc.api.server.HeriaServerType;
import fr.heriamc.hub.HeriaHub;
import fr.heriamc.hub.game.HubGame;
import fr.heriamc.hub.menu.games.sub.SubGameMainMenu;
import net.jitse.npclib.api.NPC;
import net.jitse.npclib.api.events.NPCInteractEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

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
    private final Location center = new Location(world, -60.5, 48, 0.5);

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
        NPCLoader hika = createNpc(new Location(world, -75.5, 48, 0.5), 1784272602, "§3§lHIKABRAIN", "§fJoueurs en jeu: §a0", "", "§6» §eClique §6«");
        npcs.put(hika.getNpc(), e -> {
            hub.getBukkitAPI().getMenuManager().open(new SubGameMainMenu(e.getWhoClicked(), hub, HubGame.HIKABRAIN, null));
        });
        putToRefreshPlayers(hika, HeriaServerType.HIKABRAIN);

        NPCLoader shootcraft = createNpc(new Location(world, -74.5, 48, -5.5), 631879224, "§a§lSHOOTCRAFT", "§fJoueurs en jeu: §a0", "", "§6» §eClique §6«");
        npcs.put(shootcraft.getNpc(), e -> {
            hub.getBukkitAPI().getMenuManager().open(new SubGameMainMenu(e.getWhoClicked(), hub, HubGame.SHOOTCRAFT, null));
        });
        putToRefreshPlayers(hika, HeriaServerType.SHOOTCRAFT);

        NPCLoader rushffa = createNpc(new Location(world, -74.5, 48, 6.5), 1173467410, "§e§lRUSHFFA", "§fJoueurs en jeu: §a0", "", "§6» §eClique §6«");
        npcs.put(rushffa.getNpc(), e -> {
            hub.getBukkitAPI().getMenuManager().open(new SubGameMainMenu(e.getWhoClicked(), hub, HubGame.RUSHFFA, null));
        });
        putToRefreshPlayers(hika, HeriaServerType.RUSHFFA);

        NPCLoader oneshot = createNpc(new Location(world, -71.5, 48, -10.5), 798143114, "§6§lONESHOT", "§fJoueurs en jeu: §a0", "", "§6» §eClique §6«");
        npcs.put(oneshot.getNpc(), e -> {
            hub.getBukkitAPI().getMenuManager().open(new SubGameMainMenu(e.getWhoClicked(), hub, HubGame.ONESHOT, null));
        });
        putToRefreshPlayers(hika, HeriaServerType.ONESHOT);

        NPCLoader soon1 = createNpc(new Location(world, -71.5, 48, 11.5), 1229866094, "§c§lPROCHAINEMENT", "§fJoueurs en jeu: §c§k000", "", "§6» §eClique §6«");
        npcs.put(soon1.getNpc(), e -> {});

        NPCLoader soon2 = createNpc(new Location(world, -66.5, 48, -13.5), 1229866094, "§c§lPROCHAINEMENT", "§fJoueurs en jeu: §c§k000", "", "§6» §eClique §6«");
        npcs.put(soon2.getNpc(), e -> {});

        NPCLoader soon3 = createNpc(new Location(world, -66.5, 48, 14.5), 1229866094, "§c§lPROCHAINEMENT", "§fJoueurs en jeu: §c§k000", "", "§6» §eClique §6«");
        npcs.put(soon3.getNpc(), e -> {});

        makeLookCenter();
    }

    public void makeLookCenter(){
        for (NPC npc : npcs.keySet()) {
            Location location = npc.getLocation();

            Vector dirBetweenLocations = center.toVector().subtract(location.toVector());
            location.setDirection(dirBetweenLocations);
            npc.setLocation(location);
        }
    }

    /*public void makeEntityLookCenter(Entity entity){
        Location location = entity.getLocation();

        Vector dirBetweenLocations = center.toVector().subtract(location.toVector());
        location.setDirection(dirBetweenLocations);
        entity.teleport(location);
    }*/

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
