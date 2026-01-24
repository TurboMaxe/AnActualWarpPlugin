package io.turbo.random.aawp1.commands.warp;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.turbo.random.aawp1.AAWP;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.io.IOException;

/**
 * author@TurboMaxe
 * since 1/24/2026
 *
 */

public class SetWarp {
    public static  LiteralCommandNode<CommandSourceStack> createCommand() {
           return Commands.literal("setwarp")
               .then(Commands.argument("setwarpName", StringArgumentType.word())
                        .executes(ctx -> {
                            CommandSender sender = ctx.getSource().getSender();
                            Entity executor = ctx.getSource().getExecutor();
                            String warpName = ctx.getArgument("setwarpName", String.class);
                            FileConfiguration config = AAWP.getInstance().getConfig();

                            if (!(sender instanceof Player player)) {
                                sender.sendMessage("Only players can use this command!");
                                return Command.SINGLE_SUCCESS;
                            }


                            if (config.contains("warps." + warpName)) {
                                player.sendMessage(Component.text("§cWarp '" + warpName + "' already exists!").color(TextColor.color(0xFF0F23)));
                                return Command.SINGLE_SUCCESS;
                            }

                            if(!(sender.hasPermission("AAWP.warp"))) {

                                sender.sendMessage(Component.text("You are not allowed to use this command!").color(TextColor.color(0xB8181C)));
                                return Command.SINGLE_SUCCESS;
                            }


                            Location playerLocation = player.getLocation();
                            config.set("warps." + warpName + ".x", playerLocation.getX());
                            config.set("warps." + warpName + ".y", playerLocation.getY());
                            config.set("warps." + warpName + ".z", playerLocation.getZ());
                            config.set("warps." + warpName + ".world", playerLocation.getWorld().getName());
                            config.set("warps." + warpName + ".yaw", playerLocation.getYaw());
                            config.set("warps." + warpName + ".pitch", playerLocation.getPitch());
                            config.set("warps." + warpName + ".creator", player.getName());
                            try {
                                config.save("config.yml");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                            sender.sendMessage(Component.text("Successfully made warp " + warpName).color(TextColor.color(0x1FD51C)));
                            AAWP.getInstance().saveConfig();
                            return Command.SINGLE_SUCCESS;
                        })
                     )
                  .build();
              }
    }

