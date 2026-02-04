package io.turbo.random.aawp1.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.turbo.random.aawp1.AAWP;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class ReloadConfig {
    public static LiteralCommandNode<CommandSourceStack> createCommand() {
        return Commands.literal("reloadconfig")
                .executes(ctx -> {

                    FileConfiguration config = AAWP.getInstance().getConfig();
                    CommandSender sender = ctx.getSource().getSender();

                    if (!(sender.hasPermission("AAWP.reloadconfig"))) {
                        sender.sendMessage(Component.text("You are not allowed to use this command!").color(TextColor.color(0xB8181C)));
                        return Command.SINGLE_SUCCESS;
                    }
                    AAWP.getInstance().saveDefaultConfig();
                    AAWP.getInstance().saveConfig();
                    config.options().copyDefaults(true);
                    sender.sendMessage(Component.text("Successfully reloaded the AAWP config!").color(NamedTextColor.GREEN));
                    return Command.SINGLE_SUCCESS;

                })
                .build();
    }
}


