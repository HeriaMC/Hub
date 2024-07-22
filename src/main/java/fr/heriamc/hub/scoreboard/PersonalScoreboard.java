package fr.heriamc.hub.scoreboard;

import fr.heriamc.hub.HeriaHub;
import fr.heriamc.hub.utils.ObjectiveSign;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;


public class PersonalScoreboard {
    private final Player player;
    private final UUID uuid;
    private final ObjectiveSign objectiveSign;

    private final HeriaHub hub;

    public PersonalScoreboard(Player player, HeriaHub hub) {
        this.player = player;
        this.hub = hub;

        uuid = player.getUniqueId();
        objectiveSign = new ObjectiveSign("sidebar", "heriamc");
        reloadData();
        objectiveSign.addReceiver(player);
    }

    public void reloadData() {

    }

    /*public String formatCoins(int coins) {
        if (coins >= 1_000_000) {
            return String.format("%.1fM", coins / 1_000_000.0);
        } else if (coins >= 1_000) {
            return String.format("%.1fk", coins / 1_000.0);
        } else {
            return String.valueOf(coins);
        }
    }*/

    public void setLines(String ip) {
        objectiveSign.setDisplayName("§e§l» §6§lHERIAMC §e§l«");

        objectiveSign.clearScores();

        objectiveSign.setLine(0, "§e ");
        objectiveSign.setLine(1, "§8■ §7Grade: §7Joueur");
        objectiveSign.setLine(2, "§b ");
        objectiveSign.setLine(3, "§6§l» §f§l" + player.getName());
        objectiveSign.setLine(4, "§8■ §7Points: §60 ⛃");
        objectiveSign.setLine(5, "§8■ §7Crédit: §d0 EUR");
        objectiveSign.setLine(6, "§c ");
        objectiveSign.setLine(7, "§8■ §7Hub: §a#1");
        objectiveSign.setLine(8, "§8■ §7Connectés: §b4");
        objectiveSign.setLine(9, "§6 ");
        objectiveSign.setLine(10, ip);

        objectiveSign.updateLines();
    }

    public void onLogout() {
        objectiveSign.removeReceiver(Bukkit.getServer().getOfflinePlayer(uuid));
    }
}
