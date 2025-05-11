package cz.churaq.lock;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class LockPickingListener implements Listener {


    private final Main plugin;

    public LockPickingListener(Main plugin) {
        this.plugin = plugin;

    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        if (block != null && block.getType() == Material.CHEST) {
            if (block.hasMetadata("lock_tier")) {

                int tier = block.getMetadata("lock_tier").get(0).asInt();

                ItemStack item = player.getInventory().getItemInMainHand();
                if (item.getType() == Material.STICK) {
                    event.setCancelled(true);


                    LockPickingMinigame minigame = new LockPickingMinigame(plugin);
                    minigame.startMinigame(player, tier);
                }
            }
        }
    }
}
