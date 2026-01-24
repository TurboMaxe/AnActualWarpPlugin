package io.turbo.random.aawp1.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.turbo.random.aawp1.AAWP;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Version {
  public static String ver = "1.1.0";

    public static LiteralCommandNode<CommandSourceStack> createCommand() {
        return Commands.literal("version")
                .executes(ctx -> {

                    FileConfiguration config = AAWP.getInstance().getConfig();
                    JavaPlugin plugin = AAWP.getInstance();
                    CommandSender sender = ctx.getSource().getSender();

                    if (!(sender.hasPermission("AAWP.version"))) {
                        sender.sendMessage(Component.text("You are not allowed to use this command!").color(TextColor.color(0xB8181C)));
                        return Command.SINGLE_SUCCESS;
                    }

                    sender.sendMessage(Component.text("This server is currently running on version: \n " + "-" + ver + "of AAWP." ).color(TextColor.color(0xFFF936)));
                    return Command.SINGLE_SUCCESS;
                })
                .build();
    }
}