package me.turbo.random.anActualPlugin1;

import me.turbo.random.anActualPlugin1.commands.DelWarp;
import me.turbo.random.anActualPlugin1.commands.SetWarp;
import me.turbo.random.anActualPlugin1.commands.Warp;
import lombok.Getter;
import me.turbo.random.anActualPlugin1.commands.Warps;

import org.bukkit.plugin.java.JavaPlugin;

public class CommandHandler extends JavaPlugin {
    @Getter private static CommandHandler instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        getLogger().info("AAWP enabled!");

        getCommand("warp").setExecutor(new Warp(this));
        getCommand("setwarp").setExecutor(new SetWarp(this));
        getCommand("warps").setExecutor(new Warps());
        getCommand("delwarp").setExecutor(new DelWarp());

    }
    
    @Override
    public void onDisable() {
        getLogger().info("AAWP disabled!");

    }
}

