package io.turbo.random.aawp1.commands.warp;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.turbo.random.aawp1.AAWP;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

/**
* author@TurboMaxe
* since 1/24/2026
*
*/
public class Warp {

    public static String warpName;

    /**
     * Constructor method that teleports the player
     * @param player the command sender
     * @param warpName the name of the warp, the identifier used to teleport the player
     */

    public static void teleportPlayer(Player player, String warpName ) {

        FileConfiguration config = AAWP.getInstance().getConfig();

        double x = config.getDouble("warps." + warpName + ".x");
        double y = config.getDouble("warps." + warpName + ".y");
        double z = config.getDouble("warps." + warpName + ".z");
        float yaw = (float) config.getDouble("warps." + warpName + ".yaw");
        float pitch = (float) config.getDouble("warps." + warpName + ".pitch");

        String worldName = config.getString("warps." + warpName + ".world");
        World world = Bukkit.getWorld(worldName);

        Location warpLocation = new Location(world, x, y, z);
        player.teleport(warpLocation);

    }


    public static LiteralCommandNode<CommandSourceStack> createCommand() {
        return Commands.literal("warp")
                .then(Commands.argument("warpName", StringArgumentType.word())
                        .executes(ctx -> {

                            CommandSender sender = ctx.getSource().getSender();
                            Entity executor = ctx.getSource().getExecutor();
                            String warpName = ctx.getArgument("warpName", String.class);
                            FileConfiguration config = AAWP.getInstance().getConfig();

                            if (!(executor instanceof Player player)) {
                                sender.sendMessage(Component.text("Only players can use this command!").color(TextColor.color(0xB8181C)));
                                return Command.SINGLE_SUCCESS;
                            }

                            if (!(config.contains("warps." + warpName))) {
                                sender.sendMessage(Component.text("This warp does not exist!").color(TextColor.color(0xB8181C)));
                                return Command.SINGLE_SUCCESS;
                            }
                            if(!(sender.hasPermission("AAWP.warp"))) {

                                sender.sendMessage(Component.text("You are not allowed to use this command!").color(TextColor.color(0xB8181C)));
                                return Command.SINGLE_SUCCESS;
                            }


                            sender.sendMessage(Component.text("Succesfully teleported you to warp: '" + warpName + "'").color(TextColor.color(0x1CEA14)));
                            teleportPlayer(player, warpName);
                            return Command.SINGLE_SUCCESS;
                        })
                )
        .build();

    }
}


