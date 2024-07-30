package fr.heriamc.hub.tasks;

import fr.heriamc.bukkit.utils.Title;
import fr.heriamc.hub.HeriaHub;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class ActionBarTask extends BukkitRunnable {

    private final List<String> messages;
    private String actualMessage;
    private int seconds, secondsToChange, ticks;

    public ActionBarTask() {
        this.seconds = 0;
        this.secondsToChange = 5;
        this.ticks = 0;
        this.messages = List.of(
                "§f➔ §cNous recrutons des modérateurs ! Rejoignez-nous sur Discord pour postuler.",
                "§f➔ §eDécouvrez notre boutique en ligne à l'adresse store.heriaserv.net !",
                "§f➔ §7Si vous rencontrez un bug, tapez §b§n/bug §fpour le signaler.");
        this.runTaskTimerAsynchronously(HeriaHub.get(), 0, 1);
    }

    @Override
    public void run() {
        if (this.ticks % 20 == 0) {
            if (this.seconds == this.secondsToChange) {
                this.seconds = 0;
                this.secondsToChange = 5;
                this.actualMessage = getRandomMessage();
            }
            HeriaHub.get().getServer().getOnlinePlayers().forEach(player -> Title.sendActionBar(player, this.actualMessage));
            seconds++;
        }
        ticks++;
    }

    public String getRandomMessage() {
        return messages.get((int) (Math.random() * messages.size()));
    }

}
