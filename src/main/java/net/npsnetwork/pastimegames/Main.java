package net.npsnetwork.pastimegames;

import net.npsnetwork.pastimegames.commands.Reload;
import net.npsnetwork.pastimegames.commands.Test;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main plugin;
    public static Main getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;

        this.saveDefaultConfig();

        getCommand("pastimegames").setExecutor(new Reload());
        getCommand("test").setExecutor(new Test());
    }

    @Override
    public void onDisable() {

    }
}
