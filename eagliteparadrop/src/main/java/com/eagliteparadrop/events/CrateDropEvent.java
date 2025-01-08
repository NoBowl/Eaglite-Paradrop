package com.eagliteparadrop.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Barrel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class CrateDropEvent implements Listener {

    private final Random random = new Random();

    public CrateDropEvent() {
        Bukkit.getPluginManager().registerEvents(this, EagliteParadrop.getInstance());
    }

    @EventHandler
    public void onCrateDrop(PlayerCommandPreprocessEvent event) {
        String[] args = event.getMessage().split(" ");
        if (args[0].equalsIgnoreCase("/paradrop")) {
            Location location = event.getPlayer().getLocation().add(0, 50, 0);
            dropCrate(location, Material.CHEST); // Change to Material.BARREL for barrels
            event.getPlayer().sendMessage("Crate dropped!");
        }
    }

    private void dropCrate(Location location, Material crateType) {
        Block block = location.getBlock();
        block.setType(crateType);
        
        if (crateType == Material.CHEST) {
            Chest chest = (Chest) block.getState();
            Inventory inventory = chest.getInventory();
            fillCrate(inventory);
        } else if (crateType == Material.BARREL) {
            Barrel barrel = (Barrel) block.getState();
            Inventory inventory = barrel.getInventory();
            fillCrate(inventory);
        }
    }

    private void fillCrate(Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            if (random.nextBoolean()) {
                inventory.setItem(i, new ItemStack(Material.DIAMOND, random.nextInt(3) + 1));
            }
        }
    }
}