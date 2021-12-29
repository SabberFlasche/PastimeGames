package net.npsnetwork.pastimegames.events;

import net.npsnetwork.pastimegames.manager.connectfour.Connect4Manager;
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
        Connect4Manager.onCloseInventory(player);
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        event.setCancelled(Connect4Manager.onClick(event.getSlot(), player));
    }

    @EventHandler
    public void onInvDrag(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();
        event.setCancelled(Connect4Manager.onDrag(player));
    }
}
