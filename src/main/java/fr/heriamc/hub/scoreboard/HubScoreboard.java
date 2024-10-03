package fr.heriamc.hub.scoreboard;

import fr.heriamc.api.user.HeriaPlayer;
import fr.heriamc.bukkit.scoreboard.PersonalScoreboard;
import fr.heriamc.hub.HeriaHub;
import fr.heriamc.hub.utils.ObjectiveSign;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;


public class HubScoreboard extends PersonalScoreboard {

    private final HeriaHub hub;

    private HeriaPlayer heriaPlayer;
    private final String hubNumber;
    private int connected;

    public HubScoreboard(Player player, HeriaHub hub) {
        super(player);
        this.hub = hub;

        this.hubNumber = hub.getBukkitAPI().getInstanceName().split("-")[1];
        reloadData();
    }

    public void reloadData() {
        this.heriaPlayer = hub.getBukkitAPI().getApi().getPlayerManager().get(player.getUniqueId());
        this.connected = Bukkit.getOnlinePlayers().size();
    }

    public String formatCoins(int coins) {
        if (coins >= 1_000_000) {
            return String.format("%.1fM", coins / 1_000_000.0);
        } else if (coins >= 1_000) {
            return String.format("%.1fk", coins / 1_000.0);
        } else {
            return String.valueOf(coins);
        }
    }

    public void setLines(String ip) {
        objectiveSign.setDisplayName("§e§l» §6§lHERIAMC §e§l«");

        objectiveSign.clearScores();

        objectiveSign.setLine(0, "§e ");
        objectiveSign.setLine(1, "§8■ §7Grade: " + heriaPlayer.getRank().getPrefix());
        objectiveSign.setLine(2, "§b ");
        objectiveSign.setLine(3, "§6§l» §f§l" + player.getName());
        objectiveSign.setLine(4, "§8■ §7Points: §6" + formatCoins((int) heriaPlayer.getCoins()) +" ⛃");
        objectiveSign.setLine(5, "§8■ §7Crédit: §d" + heriaPlayer.getCredits() + " EUR");
        objectiveSign.setLine(6, "§8■ §7Hosts: §3" + heriaPlayer.getHosts() +" ☀");
        objectiveSign.setLine(7, "§c ");
        objectiveSign.setLine(8, "§8■ §7Hub: §a#" + this.hubNumber);
        objectiveSign.setLine(9, "§8■ §7Connectés: §b" + this.connected);
        objectiveSign.setLine(10, "§6 ");
        objectiveSign.setLine(11, ip);

        objectiveSign.updateLines();
    }

    public void onLogout() {
        objectiveSign.removeReceiver(Bukkit.getServer().getOfflinePlayer(uuid));
    }
}
