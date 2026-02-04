package io.turbo.random.aawp1.commands.warp;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.turbo.random.aawp1.AAWP;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static io.turbo.random.aawp1.commands.warp.Warp.warpName;

public class DelWarp {

    public static LiteralCommandNode<CommandSourceStack> createCommand() {
        return Commands.literal("delwarp")
                .executes(ctx -> {

                    CommandSender sender = ctx.getSource().getSender();

                    if (!(sender instanceof Player player)) {
                        sender.sendMessage("§cOnly players can use this command!");
                        return Command.SINGLE_SUCCESS;
                    }

                    FileConfiguration config = AAWP.getInstance().getConfig();

                    if (!config.contains("warps." + warpName)) {
                        player.sendMessage("§cWarp '" + warpName + "' does not exist!");
                        return Command.SINGLE_SUCCESS;
                    }

                    config.set("warps." + warpName, null);
                    AAWP.getInstance().saveConfig();
                    return Command.SINGLE_SUCCESS;

                })
                .build();
    }
}
