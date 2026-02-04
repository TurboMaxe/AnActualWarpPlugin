package io.turbo.random.aawp1.commands.warp;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.turbo.random.aawp1.AAWP;
import io.turbo.random.aawp1.guis.ClearWarpGui;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

/**
 * author@TurboMaxe
 * since 1/24/2026
 *
 */

public class ClearWarps {
    public static LiteralCommandNode<CommandSourceStack> createCommand() {
        return Commands.literal("clearwarps")
                       .executes(ctx -> {
                            FileConfiguration config = AAWP.getInstance().getConfig();
                            CommandSender sender = ctx.getSource().getSender();
                            Entity executor = ctx.getSource().getExecutor();

                            if (!(config.contains("warps."))) {
                                sender.sendMessage(Component.text("No warps have currently been made!").color(TextColor.color(0xB8181C)));
                                return Command.SINGLE_SUCCESS;
                            }

                            if (!(sender.hasPermission("AAWP.clearwarps"))) {
                                sender.sendMessage(Component.text("You are not allowed to use this command!").color(TextColor.color(0xB8181C)));
                                return Command.SINGLE_SUCCESS;
                            }

                           if (!(executor instanceof Player player)) {
                               sender.sendMessage(Component.text("Only players can use this command!").color(TextColor.color(0xB8181C)));
                               return Command.SINGLE_SUCCESS;
                           }

                            if (sender instanceof Player p) {
                                new ClearWarpGui().open(p);
                                p.sendMessage(Component.text("Opening the confirmation GUI..\n").color(TextColor.color(0xBDB3BA))
                                        .append(Component.text("Click the green button to confirm to clear all available warps from the config.").color(TextColor.color(0xEDCC46)))
                                );
                            }


                            AAWP.getInstance().getConfig().createSection("warps");
                            return Command.SINGLE_SUCCESS;
                        })
                .build();

    }
}
