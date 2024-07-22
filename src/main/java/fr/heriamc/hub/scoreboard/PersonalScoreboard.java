package fr.silentmc.ffa.modules.scoreboard;

import fr.silentmc.api.SilentAPI;
import fr.silentmc.api.user.SilentPlayer;
import fr.silentmc.api.utils.math.ExpUtils;
import fr.silentmc.ffa.modules.ModuleType;
import fr.silentmc.ffa.modules.event.Event;
import fr.silentmc.ffa.modules.event.EventModule;
import fr.silentmc.ffa.modules.event.addons.EventScoreboard;
import fr.silentmc.ffa.modules.scoreboard.utils.ObjectiveSign;
import fr.silentmc.ffa.user.FFAPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

/*
 * This file is part of SamaGamesAPI.
 *
 * SamaGamesAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SamaGamesAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SamaGamesAPI.  If not, see <http://www.gnu.org/licenses/>.
 */
public class PersonalScoreboard {
    private final Player player;
    private final UUID uuid;
    private final ObjectiveSign objectiveSign;

    private final ScoreboardModule module;

    private EventModule eventModule;

    private FFAPlayer ffaPlayer;
    private SilentPlayer silentPlayer;
    private String rank;
    private ExpUtils expUtils;
    private Event event;

    private EventScoreboard eventScoreboard;

    public PersonalScoreboard(Player player, ScoreboardModule module) {
        this.player = player;
        this.module = module;

        uuid = player.getUniqueId();
        objectiveSign = new ObjectiveSign("sidebar", "silentmc");
        reloadData();
        objectiveSign.addReceiver(player);

        this.eventModule = ModuleType.EVENT.getModuleAs();
    }

    public void reloadData() {
        SilentAPI api = module.getManager().getSilentAPI();
        this.silentPlayer = api.getPlayerManager().getPlayer(player.getUniqueId());
        this.ffaPlayer = module.getManager().getPlayerManager().getPlayer(player.getUniqueId());

        this.rank = module.getManager().getSilentBukkit().getLuckPermsHook().getPrefix(player.getUniqueId());
        this.expUtils = new ExpUtils(silentPlayer.getXp());

        if(this.eventModule == null){
            this.eventModule = ModuleType.EVENT.getModuleAs();
        }

        this.event = this.eventModule.getLaunchedEvent();

        if(this.event != null){
            if(!this.event.getScoreboard().getClass().isInstance(this.eventScoreboard)){
                this.eventScoreboard = this.event.getScoreboard().clone();
            }

            this.eventScoreboard.reloadData(player);
        }
    }

    public String formatTime(int i){
        String string = String.valueOf(i);
        if(string.length() == 1){
            return "0" + string;
        }
        return string;
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
        objectiveSign.setDisplayName("§6§lHYLERS");

        objectiveSign.clearScores();

        objectiveSign.setLine(0, "§7 La conquête des temples ");
        objectiveSign.setLine(1, "§1");
        objectiveSign.setLine(2, "§e§l▏ §6§l" + player.getName());
        objectiveSign.setLine(3, " §7◆ §fGrade: " + rank);
        objectiveSign.setLine(4, " §7◆ §fArgent: §6" + formatCoins(silentPlayer.getCoins()) + " ⛃");
        objectiveSign.setLine(5, " §7◆ §fGemmes: §b" + formatCoins(silentPlayer.getCredits()) + " ✮");
        objectiveSign.setLine(6, "§2");

        if(this.event != null){
            List<String> lines = this.eventScoreboard.getLines();

            if(eventScoreboard == null){
                System.out.println("null");
            }

            objectiveSign.setLine(7, "§e§l▏ §6§l" + this.event.getType().getName() + " §7(20m15s)");
            int i = 8;

            for (String line : lines) {
                objectiveSign.setLine(i, " §7◆ §f" + line);
                i++;
            }

            objectiveSign.setLine(i, " ");
            i++;

            objectiveSign.setLine(i, ip);
            objectiveSign.updateLines();
            return;
        }

        objectiveSign.setLine(7, "§e§l▏ §6§lStats");
        objectiveSign.setLine(8, " §7◆ §fK/D: §a" + silentPlayer.getKills() + "§f/§a" + silentPlayer.getDeaths());
        objectiveSign.setLine(9, " §7◆ §fNiveau: §9" + expUtils.getLevel() + " ✦");
        objectiveSign.setLine(10, " §7◆ §fExp: §3" + expUtils.getActualExp() +"§f/§3" + expUtils.getActualExpToNextLevel());
        objectiveSign.setLine(11, "§3");
        objectiveSign.setLine(12, ip);

        objectiveSign.updateLines();
    }

    public void onLogout() {
        objectiveSign.removeReceiver(Bukkit.getServer().getOfflinePlayer(uuid));
    }
}
