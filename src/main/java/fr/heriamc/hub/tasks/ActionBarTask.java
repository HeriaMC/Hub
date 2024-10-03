package fr.heriamc.hub.tasks;

import fr.heriamc.bukkit.utils.Title;
import fr.heriamc.hub.HeriaHub;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class ActionBarTask extends BukkitRunnable {

    private final List<String> actionbars;

    private int timer = 0;
    private int actual = 0;

    public ActionBarTask(HeriaHub hub) {
        this.actionbars = List.of(
                "§e§l» §eBienvenue sur §6§lHeriaMC §e! §e§l«",
                "§f➔ §cNous recrutons des modérateurs ! Rejoignez-nous sur Discord pour postuler.",
                "§f➔ §eGrades§f,§cBadges,§dCrédits§f... §8→ §bshop.heriamc.fr ",
                "§f➔ §fSi vous rencontrez un bug, tapez §b§n/bug §fpour le signaler.");

        this.runTaskTimer(hub, 0, 20);
    }

    @Override
    public void run() {
        if(timer >= 5){
            actual++;
            timer = 0;
        }

        for (Player player : Bukkit.getOnlinePlayers()){
            Title.sendActionBar(player, actionbars.get(actual % actionbars.size()));
        }

        timer++;
    }

}
