package io.turbo.random.aawp1;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import io.turbo.random.aawp1.commands.*;
import io.turbo.random.aawp1.commands.warp.*;
import io.turbo.random.aawp1.util.gui.FastInvManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * author@TurboMaxe
 * since 1/24/2026
 *
 */

public final class AAWP extends JavaPlugin implements Listener {

        @Getter
        private static AAWP instance;
        public static final String VERSION = "1.1.0";

        @Override
        public void onEnable() {
            instance = this;
            saveDefaultConfig();
            getLogger().info("Plugin enabled!");
            Bukkit.getPluginManager().registerEvents(this, this);
            this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
                commands.registrar().register(SetWarp.createCommand());
                commands.registrar().register(Warp.createCommand());
                commands.registrar().register(ClearWarps.createCommand());
                commands.registrar().register(AAWPinfo.createCommand());
                commands.registrar().register(Help.createhelpCommand());
                commands.registrar().register(Version.createCommand());
                commands.registrar().register(ReloadConfig.createCommand());
                commands.registrar().register(Warps.createCommand());
                commands.registrar().register(DelWarp.createCommand());
            });
            saveDefaultConfig();
            FastInvManager.register(this);
        }

        @Override
        public void onDisable() {
            getLogger().info("AnActualWarpPlugin (AAWP) Disabled!");
            saveDefaultConfig();
        }
    }
