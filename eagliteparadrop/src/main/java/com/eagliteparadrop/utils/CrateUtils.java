package com.eagliteparadrop.utils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Barrel;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;

public class CrateUtils {

    private static final Random RANDOM = new Random();

    public static void createCrate(Block block, List<ItemStack> loot, boolean isChest) {
        if (isChest) {
            block.setType(Material.CHEST);
            Chest chest = (Chest) block.getState();
            Inventory inventory = chest.getInventory();
            fillInventory(inventory, loot);
        } else {
            block.setType(Material.BARREL);
            Barrel barrel = (Barrel) block.getState();
            Inventory inventory = barrel.getInventory();
            fillInventory(inventory, loot);
        }
    }

    private static void fillInventory(Inventory inventory, List<ItemStack> loot) {
        for (ItemStack item : loot) {
            int amount = RANDOM.nextInt(item.getMaxStackSize()) + 1;
            ItemStack lootItem = new ItemStack(item.getType(), amount);
            inventory.addItem(lootItem);
        }
    }
}