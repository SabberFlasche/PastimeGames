package me.plasmabase.pastimegames.events;

import me.plasmabase.pastimegames.Main;
import me.plasmabase.pastimegames.manager.Games.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class Inventory implements Listener {
    @EventHandler
    public void onInvClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (player.getOpenInventory().getTitle().equalsIgnoreCase(Main.settingsManager().connect4InventoryTitle())) {
            GameManager.onCloseInventory(player);
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        event.setCancelled(GameManager.onClick(event.getSlot(), player));
    }

    @EventHandler
    public void onInvDrag(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();
        event.setCancelled(GameManager.onDrag(player));
    }
}
