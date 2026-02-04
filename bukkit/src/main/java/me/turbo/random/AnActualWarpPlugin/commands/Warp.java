package me.turbo.random.anActualPlugin1.commands;

import me.turbo.random.anActualPlugin1.CommandHandler;
import me.turbo.random.anActualPlugin1.WarpInventory;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Warp implements CommandExecutor {

    private final CommandHandler plugin;

    public Warp(CommandHandler plugin) {
        this.plugin = plugin;
    }

    public void open(Player player) {
        player.openInventory(WarpInventory.inventory);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("§cYou have to be a player to use this command!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("§cProvide a warp name! Usage: /warp [warpName]");
            return true;
        }

        String warpName = args[0]; // FIRST argument

        FileConfiguration config = CommandHandler.getInstance().getConfig();

        // Checks if the warp is in config.yml
        if (!config.contains("warps." + warpName)) {
            player.sendMessage("§cWarp '" + warpName + "' does not exist!");
            return true;
        }

        String worldName = config.getString("warps." + warpName + ".world");
        World world = Bukkit.getWorld(worldName);

        if (world == null) {
            player.sendMessage("§cWorld '" + worldName + "' is not loaded!");
            return true;
        }

        double x = config.getDouble("warps." + warpName + ".x");
        double y = config.getDouble("warps." + warpName + ".y");
        double z = config.getDouble("warps." + warpName + ".z");
        float yaw = (float) config.getDouble("warps." + warpName + ".yaw");
        float pitch = (float) config.getDouble("warps." + warpName + ".pitch");

        Location warpLocation = new Location(world, x, y, z, yaw, pitch);
        player.teleport(warpLocation);
        player.sendMessage("§aTeleported to warp §e" + warpName + "§a!");

        return true;
    }
}
