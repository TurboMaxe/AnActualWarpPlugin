package io.turbo.random.aawp1.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Help {
    public static LiteralCommandNode<CommandSourceStack> createhelpCommand() {
        return Commands.literal("help")
                .executes(ctx -> {
                    CommandSender sender = ctx.getSource().getSender();

                    if (!(sender.hasPermission("AAWP.help"))) {
                        sender.sendMessage(Component.text("You are not allowed to use this command!").color(TextColor.color(0xB8181C)));
                        return Command.SINGLE_SUCCESS;
                    }

                    if (sender instanceof Player p) {
                        p.sendMessage(Component.text("Available Commands:").color(TextColor.color(0xFFFFFF)).decoration(TextDecoration.BOLD, true));
                        p.sendMessage(Component.text("- help").color(NamedTextColor.LIGHT_PURPLE));
                        p.sendMessage(Component.text("- version").color(NamedTextColor.LIGHT_PURPLE));
                        p.sendMessage(Component.text("- setwarp").color(NamedTextColor.LIGHT_PURPLE));
                        p.sendMessage(Component.text("- version").color(NamedTextColor.LIGHT_PURPLE));
                        p.sendMessage(Component.text("- clearwarps").color(NamedTextColor.LIGHT_PURPLE));
                        p.sendMessage(Component.text("- reload").color(NamedTextColor.LIGHT_PURPLE));

                        return Command.SINGLE_SUCCESS;
                    }

                    return Command.SINGLE_SUCCESS;

                })
                .build();
    }
}
