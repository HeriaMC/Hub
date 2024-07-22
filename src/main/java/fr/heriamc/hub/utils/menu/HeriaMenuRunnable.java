package fr.heriamc.core.utils.ui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class HeriaMenuRunnable implements Runnable {

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (HeriaMenuManager.getInventory().containsKey(player)) {
                if (HeriaMenuManager.getInventory().get(player).isUpdate()) {
                    HeriaMenuManager.getInventory().get(player).updateMenu();
                }
            }
        }
    }
}