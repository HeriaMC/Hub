package fr.heriamc.hub.menu.options;

import fr.heriamc.api.user.HeriaPlayer;
import fr.heriamc.api.user.settings.HeriaPlayerSettings;
import fr.heriamc.api.user.settings.HeriaPlayerSettings.UserSettingState;
import fr.heriamc.api.utils.EnumUtils;
import fr.heriamc.bukkit.menu.HeriaMenu;
import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.heriamc.hub.HeriaHub;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class OptionsMenu extends HeriaMenu {

    private final HeriaHub hub;
    public OptionsMenu(Player player, HeriaHub hub) {
        super(player, "Paramètres", 54, false);
        this.hub = hub;
    }

    @Override
    public void contents(Inventory inventory) {
        setBorder(inventory, DyeColor.RED.getWoolData());

        inventory.setItem(4, new ItemBuilder(Material.REDSTONE_COMPARATOR).setName("§f» §cParamètres").build());

        HeriaPlayerSettings settings = hub.getBukkitAPI().getApi().getSettingsManager().get(getPlayer().getUniqueId());

        insertSettingItem(inventory, 20, new ItemBuilder(Material.PAPER).setName("§eMessages privés"), settings::setPrivateMessage, settings.getPrivateMessage());
        insertSettingItem(inventory, 21, new ItemBuilder(Material.NAME_TAG).setName("§eDemandes d'amis"), settings::setFriendDemand, settings.getFriendDemand());
        insertSettingItem(inventory, 22, new ItemBuilder(Material.BOOK).setName("§eDemandes de groupes"), settings::setGroupDemand, settings.getGroupDemand());
        insertSettingItem(inventory, 23, new ItemBuilder(Material.FEATHER).setName("§eVisibilité des joueurs"), settings::setHubVisibilityPlayer, settings.getHubVisibilityPlayer());

        this.insertInteractItem(inventory, 49, new ItemBuilder(Material.DARK_OAK_DOOR_ITEM).setName("§c» Quitter")
                .onClick(event -> {
                    this.getPlayer().closeInventory();
                }));
    }

    private void insertSettingItem(Inventory inventory, int slot, ItemBuilder itemBuilder, Consumer<UserSettingState> setter, UserSettingState actual) {
        UserSettingState previous = EnumUtils.getPrevious(actual, UserSettingState.values());
        UserSettingState next = EnumUtils.getNext(actual, UserSettingState.values());
        this.insertInteractItem(inventory, slot, itemBuilder.clone()
                .addLore(" ")
                .addLore(" §7▲ Statut: " + previous.getName())
                .addLore(" §e■ Statut: " + actual.getName())
                .addLore(" §7▼ Statut: " + next.getName())
                .addLore(" ")
                .addLore("§6§l❱ §eClique: §fChanger")
                .onClick(event -> {
                    if(event.isLeftClick()){
                        setter.accept(next);
                        this.updateMenu();
                    } else if (event.isRightClick()) {
                        setter.accept(previous);
                        this.updateMenu();
                    }
                }));
    }

}
