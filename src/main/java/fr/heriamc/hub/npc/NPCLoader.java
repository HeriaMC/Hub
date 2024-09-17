package fr.heriamc.hub.npc;

import fr.heriamc.hub.HeriaHub;
import net.jitse.npclib.api.NPC;
import net.jitse.npclib.api.skin.MineSkinFetcher;
import net.jitse.npclib.hologram.Hologram;
import net.jitse.npclib.internal.NPCBase;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.*;

public class NPCLoader {

    private final NPC npc;
    private final Map<Integer, String> lines = new HashMap<>();

    public NPCLoader(Location location, int skinId, String... lines){
        NPC npc = HeriaHub.get().getNpcLib().createNPC(Arrays.asList(lines));
        for (int i = 0; i < lines.length; i++) {
            this.lines.put(i,lines[i]);
        }
        npc.setLocation(location);
        if (skinId != 0) {
            Objects.requireNonNull(npc);
            MineSkinFetcher.fetchSkinFromIdAsync(skinId, npc::setSkin);
        }
        this.npc = npc;
    }

    public void setLines(int line, String text){
        lines.put(line,text);
        List<String> l = new ArrayList<>(lines.values());
        try {
            Field field = NPCBase.class.getDeclaredField("text");
            Field uniqueText = NPCBase.class.getDeclaredField("uniqueText");
            uniqueText.setAccessible(true);
            Map<UUID, List<String>> uniqueTextMap = (Map<UUID, List<String>>) uniqueText.get(npc);
            uniqueTextMap.clear();
            field.setAccessible(true);
            field.set(npc,l);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        npc.setText(l);

        for (Player player : Bukkit.getOnlinePlayers()){
            Hologram h = npc.getPlayerHologram(player);
            h.hide(player);
            h.show(player);
        }

    }

    public NPC getNpc() {
        return npc;
    }
}
