package me.turbo.random.anActualPlugin1.commands;

import me.turbo.random.anActualPlugin1.CommandHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DelWarp implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cOnly players can use this command!");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("§cProvide a warp name! Usage: /delwarp <warpName>");
            return true;
        }

        String warpName = args[0];
        FileConfiguration config = CommandHandler.getInstance().getConfig();

        if (!config.contains("warps." + warpName)) {
            player.sendMessage("§cWarp '" + warpName + "' does not exist!");
            return true;
        }

        config.set("warps." + warpName, null);
        CommandHandler.getInstance().saveConfig();

        player.sendMessage("§aWarp '" + warpName + "' has been removed!");
        return true;
    }
}
