package fr.heriamc.hub.lootbox;

import fr.heriamc.hub.HeriaHub;
import fr.heriamc.hub.utils.hologram.Hologram;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.TileEntityEnderChest;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.EulerAngle;

import java.util.Collection;

public class LootboxOpenRunnable implements Runnable {

    private final HeriaHub hub;
    private final Player player;

    private final ArmorStand itemStand;

    private final Location boxLocation;
    private final Hologram hologram;
    private final Collection<? extends Player> removed;

    private int taskId = 0;
    private LootboxPrize prize;

    private int timer = 0;
    private int generate = 0;

    private boolean shouldPlay = true;
    private boolean playedFirework = false;

    public LootboxOpenRunnable(HeriaHub hub, Player player, Location boxLocation) {
        this.hub = hub;
        this.player = player;
        this.boxLocation = boxLocation;

        this.hologram = hub.getHologramManager().getFromBox(boxLocation.getBlockZ());
        this.removed = Bukkit.getOnlinePlayers();

        for (Player onlinePlayer : removed) {
            hologram.removeReceiver(onlinePlayer);
        }

        World world = hub.getServer().getWorld("world");
        this.itemStand = world.spawn(boxLocation.clone().add(0.5, -1.4, 0.5), ArmorStand.class);

        this.itemStand.setVisible(false);
        this.itemStand.setCustomNameVisible(true);
        this.itemStand.setGravity(false);
        this.itemStand.setArms(false);
        this.itemStand.setCanPickupItems(false);
        this.itemStand.setMarker(false);

        this.giveRandomItem();

        this.playChestAction(boxLocation, true);
        BukkitTask bukkitTask = Bukkit.getScheduler().runTaskTimer(hub, this, 0L, 1L);
        this.taskId = bukkitTask.getTaskId();

    }

    @Override
    public void run() {
        if(shouldPlay){
            if(timer % 2 == 0) {
                this.itemStand.teleport(this.itemStand.getLocation().add(0, 0.05, 0));
            }

            if(timer % 5 == 0){
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    onlinePlayer.playSound(this.itemStand.getLocation(), Sound.CLICK, 1, 1);
                }
            }
        }


        if(timer >= 20){
            if(generate >= 9){

                hub.getServer().getScheduler().cancelTask(this.taskId);
                this.itemStand.remove();
                this.playChestAction(this.boxLocation, false);
                for (Player onlinePlayer : removed) {
                    hologram.addReceiver(onlinePlayer);
                }

                return;
            } else if(generate == 5){
                if(!playedFirework){
                    Bukkit.broadcastMessage("§5§lLOOTBOXES §7▏ §f" + player.getName() + " à gagné " + this.prize.getItem().getName() + "§f dans une lootbox !");
                    this.shouldPlay = false;

                    Firework firework = boxLocation.getWorld().spawn(this.itemStand.getLocation(), Firework.class);
                    FireworkMeta meta = firework.getFireworkMeta();

                    meta.addEffect(FireworkEffect.builder().withColor(Color.GREEN, Color.PURPLE, Color.ORANGE)
                            .with(FireworkEffect.Type.BALL_LARGE).withFlicker().build());
                    meta.setPower(1);
                    firework.setFireworkMeta(meta);

                    //TODO: give reward

                    this.playedFirework = true;
                }

            }

            if(shouldPlay){
                this.giveRandomItem();
            }
            timer = 0;

            this.generate++;
        }

        timer++;
    }

    public void giveRandomItem(){
        this.prize = LootboxPrize.getRandomPrize();
        this.itemStand.setHelmet(this.prize.getSkullItem());
        this.itemStand.setCustomName(this.prize.getItem().getName());
    }

    public void playChestAction(Location location, boolean open) {
        net.minecraft.server.v1_8_R3.World world = ((CraftWorld) location.getWorld()).getHandle();
        BlockPosition position = new BlockPosition(location.getX(), location.getY(), location.getZ());
        TileEntityEnderChest tileChest = (TileEntityEnderChest) world.getTileEntity(position);
        world.playBlockAction(position, tileChest.w(), 1, open ? 1 : 0);
    }

}
