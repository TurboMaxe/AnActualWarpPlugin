package me.turbo.random.anActualPlugin1;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class WarpInventory implements InventoryHolder {

  public static Inventory inventory;
  private final CommandHandler plugin;

  // inventory constructer

  public WarpInventory(CommandHandler plugin) {

    this.plugin = plugin;
    this.inventory = Bukkit.createInventory(this, 9, "Warps");
    this.inventory.setItem(4, new ItemStack(Material.DIAMOND));
  }


  @Override
  public Inventory getInventory() {
    return this.inventory;
  }

  public void open(Player player) {
    player.openInventory(this.inventory);
  }
}
