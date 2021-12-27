package net.npsnetwork.pastimegames.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Test implements CommandExecutor {
    private Inventory window;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            window = Bukkit.createInventory(null, 6 * 9, "Test:");
            player.openInventory(window);
        }
        return true;
    }
}
