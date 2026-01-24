package io.turbo.random.aawp1.guis;

import io.turbo.random.aawp1.AAWP;
import io.turbo.random.aawp1.util.gui.FastInv;
import io.turbo.random.aawp1.util.item.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.inventory.InventoryClickEvent;

/*
* author@turbomaxe
*
 */

public class ClearWarpGui extends FastInv {

    private boolean preventClose = false;
    public ClearWarpGui() {
        super(27, "Confirmation GUI");


        setItem(12, new ItemBuilder(Material.LIME_SHULKER_BOX).name(ChatColor.GREEN + "" + ChatColor.BOLD + "Click me to delete all warps!").build(), e ->
                e.getWhoClicked());
        setItem(14, new ItemBuilder(Material.RED_SHULKER_BOX).name(ChatColor.RED + "" + ChatColor.BOLD + "Click me to exit this gui!").build(), e ->
                e.getWhoClicked());
        setItems(getBorders(), new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).name(".").build());
        setCloseFilter(p -> this.preventClose);
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        if (event.getSlot() == 12) {

            FileConfiguration config = AAWP.getInstance().getConfig();
            String warpName = AAWP.getInstance().getConfig().getString("warps.");
            if (warpName == null) {
                event.getWhoClicked().sendMessage(Component.text("There are no warps to delete!").color(TextColor.color(0xFF0009)));
                event.setCancelled(true);
            }

            config.set("warps", null);
            AAWP.getInstance().getConfig().createSection("warps");
            AAWP.getInstance().saveConfig();
            event.getWhoClicked().sendMessage(Component.text("Succesfully deleted all warps!").color(TextColor.color(0x37FF39)));
            event.getInventory().close();
        }

        if (event.getSlot() == 14) {
            event.getInventory().close();
            event.getWhoClicked().sendMessage(Component.text("Cancelling action.").color(TextColor.color(0xFFF936)));
        }

    }

}
