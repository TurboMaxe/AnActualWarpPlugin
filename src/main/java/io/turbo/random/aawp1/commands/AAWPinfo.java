package io.turbo.random.aawp1.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.turbo.random.aawp1.AAWP;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class AAWPinfo {

    public static LiteralCommandNode<CommandSourceStack> createCommand() {
        return Commands.literal("aawp")
                .executes(ctx -> {
                    FileConfiguration config = AAWP.getInstance().getConfig();
                    JavaPlugin plugin = AAWP.getInstance();
                    CommandSender sender = ctx.getSource().getSender();
                    Entity executor = ctx.getSource().getExecutor();
                    Logger l = Bukkit.getLogger();


                    if (!(sender.hasPermission("AAWP.aawp"))) {
                        sender.sendMessage(Component.text("You are not allowed to use this command!").color(TextColor.color(0xB8181C)));
                        return Command.SINGLE_SUCCESS;
                    }

                    if (!(sender instanceof Player player)) {
                        l.info("---------------- AAWP 1.1 ---------------");
                        l.info("    This server is running AAWP 1.1      ");
                        l.info("           Available Commands:           ");
                        l.info(" warp, setwarp, clearwarps, aawp, reload ");
                        l.info(" reloadconfig, warps, delwarp  version   ");
                        l.info("-----------------------------------------");
                        l.info("               Plugin link:              ");
                        l.info(" github.com/TurboMaxe/AnActualWarpPlugin1");
                        l.info("Author(s):" + plugin.getPluginMeta().getAuthors() + ".");
                        l.info("---------------- AAWP 1.1 ---------------");
                        return Command.SINGLE_SUCCESS;
                    }

                    player.sendMessage(Component.text("This server is running AAWP, version 1.1, type /awwp:help to view the commands.").color(TextColor.color(0xFFF936)));
                    return Command.SINGLE_SUCCESS;

                   })
                .build();
        }
   }

