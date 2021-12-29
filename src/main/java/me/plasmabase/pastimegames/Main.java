package me.plasmabase.pastimegames;

import me.plasmabase.pastimegames.events.Inventory;
import me.plasmabase.pastimegames.manager.GameManager;
import me.plasmabase.pastimegames.commands.Reload;
import me.plasmabase.pastimegames.helper.Glow;
import me.plasmabase.pastimegames.manager.Game;
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
        for (Game game : GameManager.getAllGames()) {
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
