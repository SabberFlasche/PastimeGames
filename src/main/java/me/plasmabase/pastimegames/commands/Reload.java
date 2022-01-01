package me.plasmabase.pastimegames.commands;

import me.plasmabase.pastimegames.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission(Main.settingsManager().permissionsReload())) {
            sender.sendMessage(Main.settingsManager().noPermissionMessage());
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(Main.settingsManager().wrongSyntaxMessage());
            return true;
        }
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                Main.settingsManager().reload();
                sender.sendMessage(Main.settingsManager().reloadMessage());
            }
        }
        return true;
    }
}
