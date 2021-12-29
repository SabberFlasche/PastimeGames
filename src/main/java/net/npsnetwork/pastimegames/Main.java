package net.npsnetwork.pastimegames;

import net.npsnetwork.pastimegames.commands.Reload;
import net.npsnetwork.pastimegames.events.Inventory;
import net.npsnetwork.pastimegames.helper.Glow;
import net.npsnetwork.pastimegames.manager.GameManager;
import net.npsnetwork.pastimegames.manager.connectfour.Connect4Game;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

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

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new Inventory(), this);
        registerGlow();
    }

    @Override
    public void onDisable() {
        for (Connect4Game game : GameManager.getAllConnect4Games()) {
            game.endGame(null);
        }
    }

    private void registerGlow() {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Glow glow = new Glow(new NamespacedKey(this, "gloww"));
            Enchantment.registerEnchantment(glow);
        }
        catch (IllegalArgumentException e){
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
