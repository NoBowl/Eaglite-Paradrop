package com.eagliteparadrop.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.eagliteparadrop.utils.CrateUtils;

public class ParadropCommand implements CommandExecutor {

    private final JavaPlugin plugin;

    public ParadropCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 2) {
            player.sendMessage("Usage: /paradrop <type> <amount>");
            return true;
        }

        String type = args[0].toLowerCase();
        int amount;

        try {
            amount = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            player.sendMessage("Amount must be a number.");
            return true;
        }

        Location location = player.getLocation();
        CrateUtils.dropCrate(location, type, amount);
        player.sendMessage("Dropped " + amount + " " + type + "(s) at your location.");
        return true;
    }
}