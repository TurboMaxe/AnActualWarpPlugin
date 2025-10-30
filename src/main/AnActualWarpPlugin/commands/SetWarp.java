package me.turbo.random.anActualPlugin1.commands;

import me.turbo.random.anActualPlugin1.CommandHandler;
import me.turbo.random.anActualPlugin1.WarpInventory;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetWarp implements CommandExecutor {

    private final CommandHandler plugin;

    public SetWarp(CommandHandler plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        FileConfiguration config = CommandHandler.getInstance().getConfig();

       
        if (args.length == 0) {
            player.sendMessage("§cProvide a warp name!");
            player.openInventory(WarpInventory.inventory);
            return true;
        }

        String warpName = args[0];
        Location loc = player.getLocation();

        if (config.contains("warps." + warpName)) {
            player.sendMessage("§cWarp '" + warpName + "' already exists!");
            return true;
        }

        config.set("warps." + warpName + ".world", loc.getWorld().getName());
        config.set("warps." + warpName + ".x", loc.getX());
        config.set("warps." + warpName + ".y", loc.getY());
        config.set("warps." + warpName + ".z", loc.getZ());
        config.set("warps." + warpName + ".yaw", loc.getYaw());
        config.set("warps." + warpName + ".pitch", loc.getPitch());

        CommandHandler.getInstance().saveConfig();
        player.sendMessage("§eYou have created warp '" + warpName + "'");

        return true;
    }
}
