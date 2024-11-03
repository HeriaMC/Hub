package fr.heriamc.hub;

import fr.heriamc.bukkit.HeriaBukkit;
import fr.heriamc.bukkit.scoreboard.ScoreboardManager;
import fr.heriamc.hub.cosmetics.CosmeticManager;
import fr.heriamc.hub.data.HubPlayerManager;
import fr.heriamc.hub.hologram.LobbyHologram;
import fr.heriamc.hub.listeners.DoubleJumpListener;
import fr.heriamc.hub.listeners.HubListeners;
import fr.heriamc.hub.lootbox.LootboxListener;
import fr.heriamc.hub.npc.NPCInteractListener;
import fr.heriamc.hub.npc.NPCManager;
import fr.heriamc.hub.tasks.ActionBarTask;
import net.jitse.npclib.NPCLib;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.dependency.Dependency;
import org.bukkit.plugin.java.annotation.dependency.DependsOn;
import org.bukkit.plugin.java.annotation.plugin.ApiVersion;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.author.Author;
import org.bukkit.plugin.java.annotation.plugin.author.Authors;

@Plugin(name = "HeriaHub", version = "1.0.0")
@Authors(@Author("Karaam_"))
@DependsOn({@Dependency("HeriaAPI"), @Dependency("NPCLibPlugin")})
@ApiVersion(ApiVersion.Target.v1_13)
public class HeriaHub extends JavaPlugin {

    private static HeriaHub instance;

    private NPCLib npcLib;
    private NPCManager npcManager;
    private ScoreboardManager scoreboardManager;
    private CosmeticManager cosmeticManager;
    private LobbyHologram hologramManager;
    private HubPlayerManager playerManager;

    private HeriaBukkit bukkit;

    public static HeriaHub get() {
        return instance;
    }


    @Override
    public void onEnable() {
        instance = this;

        this.bukkit = HeriaBukkit.get();

        this.npcLib = new NPCLib(this);

        this.npcManager = new NPCManager(this);
        this.npcManager.initNpc();

        this.scoreboardManager = new ScoreboardManager(this);
        this.cosmeticManager = new CosmeticManager(this);
        this.playerManager = new HubPlayerManager(this.bukkit.getApi().getRedisConnection(), this.bukkit.getApi().getMongoConnection());

        this.getServer().getPluginManager().registerEvents(new HubListeners(this), this);
        this.getServer().getPluginManager().registerEvents(new DoubleJumpListener(this), this);
        this.getServer().getPluginManager().registerEvents(new NPCInteractListener(this), this);

        this.hologramManager = new LobbyHologram(this);
        this.getServer().getPluginManager().registerEvents(hologramManager, this);
        this.getServer().getPluginManager().registerEvents(new LootboxListener(this), this);

        new ActionBarTask(this);
    }

    @Override
    public void onDisable() {

    }

    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

    public HubPlayerManager getPlayerManager() {
        return playerManager;
    }

    public NPCLib getNpcLib() {
        return npcLib;
    }

    public NPCManager getNpcManager() {
        return npcManager;
    }

    public CosmeticManager getCosmeticManager() {
        return cosmeticManager;
    }

    public LobbyHologram getHologramManager() {
        return hologramManager;
    }

    public HeriaBukkit getBukkitAPI() {
        return bukkit;
    }

}
