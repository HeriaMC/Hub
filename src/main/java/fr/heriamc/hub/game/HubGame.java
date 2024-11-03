package fr.heriamc.hub.game;

import fr.heriamc.api.game.size.GameSize;
import fr.heriamc.api.game.size.GameSizeTemplate;
import fr.heriamc.api.server.HeriaServerType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public enum HubGame {

    SHOOTCRAFT(HeriaServerType.SHOOTCRAFT, List.of(GameSizeTemplate.SIZE_SOLO.toGameSize()), new ItemStack(Material.BOW)),
    HIKABRAIN(HeriaServerType.HIKABRAIN, List.of(GameSizeTemplate.SIZE_1V1.toGameSize(),
            GameSizeTemplate.SIZE_2V2.toGameSize(),
            GameSizeTemplate.SIZE_4V4.toGameSize()), new ItemStack(Material.SANDSTONE)),

    ONESHOT(HeriaServerType.ONESHOT, List.of(GameSizeTemplate.FFA.toGameSize()), new ItemStack(Material.BOW)),
    RUSHFFA(HeriaServerType.RUSHFFA, List.of(GameSizeTemplate.FFA.toGameSize()), new ItemStack(Material.TNT)),

    ;

    private final HeriaServerType serverType;
    private final List<GameSize> gameSizes;
    private final ItemStack itemStack;

    HubGame(HeriaServerType serverType, List<GameSize> gameSizes, ItemStack itemStack) {
        this.serverType = serverType;
        this.gameSizes = gameSizes;
        this.itemStack = itemStack;
    }

    public HeriaServerType getServerType() {
        return serverType;
    }

    public List<GameSize> getGameSizes() {
        return gameSizes;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
