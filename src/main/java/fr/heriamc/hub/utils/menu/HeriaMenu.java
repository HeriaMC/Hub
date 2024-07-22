package fr.heriamc.core.utils.ui;

import fr.heriamc.tools.ItemBuilder;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class HeriaMenu implements InventoryHolder {

    private final @Getter Player player;
    private final String name;
    private final int size;
    private final InventoryType type;
    private final @Getter boolean update;
    private final @Getter boolean cancelled;

    public HeriaMenu(Player player, String name, int size, boolean update) {
        this.player = player;
        this.name = name;
        this.size = size;
        this.type = null;
        this.update = update;
        this.cancelled = true;
    }

    public HeriaMenu(Player player, String name, InventoryType type, boolean update) {
        this.player = player;
        this.name = name;
        this.size = 0;
        this.type = type;
        this.update = update;
        this.cancelled = true;
    }

    public HeriaMenu(Player player, String name, int size, boolean update, boolean cancelled) {
        this.player = player;
        this.name = name;
        this.size = size;
        this.type = null;
        this.update = update;
        this.cancelled = cancelled;
    }

    public HeriaMenu(Player player, String name, InventoryType type, boolean update, boolean cancelled) {
        this.player = player;
        this.name = name;
        this.size = 0;
        this.type = type;
        this.update = update;
        this.cancelled = cancelled;
    }

    public abstract void contents(Inventory inv);

    public abstract void onClick(ItemStack item, int slot, ClickType click);

    public void setBorder(Inventory inv, int data){
        this.setBorder(inv, (short) data);
    }

    public void setBorder(Inventory inv, short data){
        ArrayList<Integer> vitres = new ArrayList<>(Arrays.asList(0, 1, 9, 7, 8, 17, 52, 53, 36, 44, 45, 46));
        for (int vitre : vitres) {
            inv.setItem(vitre, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, data).setName(" ").build(false));
        }
    }

    public void updateMenu() {
        this.player.getOpenInventory().getTopInventory().clear();
        this.contents(this.player.getOpenInventory().getTopInventory());
    }

    public Inventory getInventory() {
        if (this.type == null) {
            return Bukkit.createInventory(this, this.size, this.name);
        } else {
            return Bukkit.createInventory(this, this.type, this.name);
        }
    }
}