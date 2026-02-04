package me.turbo.random.anActualPlugin1.commands;

import me.turbo.random.anActualPlugin1.CommandHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class Warps implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, @NotNull String[] args) {

        FileConfiguration config = CommandHandler.getInstance().getConfig();

        // Check if warps exist
        if (!config.contains("warps") || config.getConfigurationSection("warps").getKeys(false).isEmpty()) {
            sender.sendMessage("§cNo warps are set!");
            return true;
        }

        Set<String> warpNames = config.getConfigurationSection("warps").getKeys(false);

        sender.sendMessage("§aAvailable warps:");
        for (String warp : warpNames) {
            sender.sendMessage("§e- " + warp);
        }

        return true;
    }
}
