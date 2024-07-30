package fr.heriamc.hub;

import fr.heriamc.bukkit.HeriaBukkit;
import fr.heriamc.hub.listeners.HubListeners;
import fr.heriamc.hub.scoreboard.ScoreboardManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.plugin.ApiVersion;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.author.Author;
import org.bukkit.plugin.java.annotation.plugin.author.Authors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Plugin(name = "HeriaHub", version = "1.0.0")
@Authors(@Author("Karaam_"))
@ApiVersion(ApiVersion.Target.v1_13)
public class HeriaHub extends JavaPlugin {

    private static HeriaHub instance;
    private HeriaBukkit bukkitAPI;

    private ScoreboardManager scoreboardManager;

    private ScheduledExecutorService executorMonoThread;
    private ScheduledExecutorService scheduledExecutorService;

    public static HeriaHub get() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        scheduledExecutorService = Executors.newScheduledThreadPool(16);
        executorMonoThread = Executors.newScheduledThreadPool(1);

        this.scoreboardManager = new ScoreboardManager(this);
        this.bukkitAPI = HeriaBukkit.get();

        this.getServer().getPluginManager().registerEvents(new HubListeners(this), this);
    }

    @Override
    public void onDisable() {

    }

    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

    public HeriaBukkit getBukkitAPI() {
        return bukkitAPI;
    }

    public ScheduledExecutorService getExecutorMonoThread() {
        return executorMonoThread;
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;
    }
}
