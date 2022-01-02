package me.plasmabase.pastimegames.events;

import me.plasmabase.pastimegames.PastimeGames;
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
        String title = player.getOpenInventory().getTitle();
        if (title.equalsIgnoreCase(PastimeGames.settingsManager().connect4InventoryTitle())
                || title.equalsIgnoreCase(PastimeGames.settingsManager().tictactoeInventoryTitle())) {
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
