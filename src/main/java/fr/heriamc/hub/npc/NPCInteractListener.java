package fr.heriamc.hub.npc;

import fr.heriamc.hub.HeriaHub;
import net.jitse.npclib.api.events.NPCInteractEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.function.Consumer;

public class NPCInteractListener implements Listener {

    private final HeriaHub hub;

    public NPCInteractListener(HeriaHub hub) {
        this.hub = hub;
    }

    @EventHandler
    public void onNpcInteract(NPCInteractEvent e){
        Consumer<NPCInteractEvent> interactEventConsumer = hub.getNpcManager().getConsumer(e.getNPC());
        if(interactEventConsumer != null){
            interactEventConsumer.accept(e);
        }
    }
}
