package cz.churaq.lock;


import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class LockCommand {

    private final Main plugin;

    public LockCommand(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("lock") && args.length == 2) {
            if (args[0].equalsIgnoreCase("chest")) {
                Player player = (Player) sender;
                String tier = args[1];

                Block targetBlock = player.getTargetBlock(null, 5); // Získání bloku před hráčem (truhla)

                if (targetBlock.getType() == Material.CHEST) {
                    // Určení složitosti zámku podle levelu
                    int lockTier = Integer.parseInt(tier.substring(1));

                    // Nastavení složitosti
                    player.sendMessage("Vytvořena truhla s úrovní zámku " + tier);

                    // Nastavení zámku
                    setLock(targetBlock, lockTier);
                    return true;
                }
            }
        }
        return false;
    }

    private void setLock(Block block, int lockTier) {
        // Uložení metadata do truhly podle úrovně složitosti
        block.setMetadata("lock_tier", new FixedMetadataValue(plugin, lockTier));
    }
}
