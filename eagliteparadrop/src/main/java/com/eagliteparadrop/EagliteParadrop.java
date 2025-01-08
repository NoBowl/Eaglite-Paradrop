package com.eagliteparadrop;

import org.bukkit.plugin.java.JavaPlugin;
import com.eagliteparadrop.commands.ParadropCommand;
import com.eagliteparadrop.events.CrateDropEvent;

public class EagliteParadrop extends JavaPlugin {

    @Override
    public void onEnable() {
        // Register commands
        getCommand("paradrop").setExecutor(new ParadropCommand(this));
        
        // Register events
        getServer().getPluginManager().registerEvents(new CrateDropEvent(this), this);
        
        getLogger().info("Eaglite Paradrop has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Eaglite Paradrop has been disabled!");
    }
}