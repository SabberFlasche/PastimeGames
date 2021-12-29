package me.plasmabase.pastimegames.commands;

import me.plasmabase.pastimegames.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("pastimegames.reload")) {
            sender.sendMessage(ChatColor.RED + "You cannot run this command");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Wrong syntax");
            return true;
        }
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                Main.getPlugin().reloadConfig();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        Main.getPlugin().getConfig().getString("reload-message")));
            }
        }
        return true;
    }
}
