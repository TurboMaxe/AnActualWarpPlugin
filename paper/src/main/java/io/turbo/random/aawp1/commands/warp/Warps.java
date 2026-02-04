package io.turbo.random.aawp1.commands.warp;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.turbo.random.aawp1.AAWP;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Set;

public class Warps {
    public static LiteralCommandNode<CommandSourceStack> createCommand() {
        return Commands.literal("warps")
                .executes(ctx -> {

                    if (AAWP.getInstance().getConfig().getString("warps.") == null || AAWP.getInstance().getConfig().getConfigurationSection("warps").getKeys(false).isEmpty()) {
                      ctx.getSource().getSender().sendMessage(Component.text("There have been no warps made!").color(NamedTextColor.RED));
                      return Command.SINGLE_SUCCESS;
                    }

                    if(!(ctx.getSource().getSender().hasPermission("AAWP.warps"))) {

                        ctx.getSource().getSender().sendMessage(Component.text("You are not allowed to use this command!").color(TextColor.color(0xB8181C)));
                        return Command.SINGLE_SUCCESS;
                    }

                    FileConfiguration config = AAWP.getInstance().getConfig();
                    Set<String> warpNames = config.getConfigurationSection("warps").getKeys(false);

                    for (String warp : warpNames) {
                        ctx.getSource().getSender().sendMessage(Component.text("Available warps:").color(NamedTextColor.GOLD) + "\n-" + warp);

                    }

                    return Command.SINGLE_SUCCESS;
                })
                .build();

    }
}
