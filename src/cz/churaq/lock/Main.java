package cz.churaq.lock;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {


    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new LockPickingListener(this, this));
        getLogger().info("...");

    }

    @Override
    public void onDisable() {

        getLogger().info("...");
    }
}
