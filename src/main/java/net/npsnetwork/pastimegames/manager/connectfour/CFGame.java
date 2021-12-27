package net.npsnetwork.pastimegames.manager.connectfour;

import net.npsnetwork.pastimegames.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Random;

public class CFGame {
    private Inventory window1;
    private Inventory window2;
    private Player player1;
    private Player player2;
    private ItemStack turnTrue;
    private ItemStack turnFalse;
    private ItemStack chipTrue;
    private ItemStack chipFalse;
    private boolean turn;

    protected CFGame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        window1 = Bukkit.createInventory(null, 6 * 9, "Connect 4:");
        window2 = Bukkit.createInventory(null, 6 * 9, "Connect 4:");
        determineFirstTurn();
        initMainWindow();
        updateTurnItems();
        openInventories();
    }

    private void determineFirstTurn() {
        Random random = new Random();
        turn = random.nextBoolean();
    }

    private void initMainWindow() {
        //init dummy-item
        ItemStack _void = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta _void_meta = _void.getItemMeta();
        _void_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                Main.getPlugin().getConfig().getString("connectfour.items.placeholder.displayName")));
        //ArrayList<String> _void_lore = new ArrayList<>();
        //_void_meta.setLore(_void_lore);
        _void.setItemMeta(_void_meta);

        //init turn-items
        turnTrue = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta turnTrue_meta = turnTrue.getItemMeta();
        turnTrue_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                Main.getPlugin().getConfig().getString("connectfour.items.yourTurn.displayName")));
        turnTrue.setItemMeta(turnTrue_meta);

        turnFalse = new ItemStack(Material.GLASS_PANE);
        ItemMeta turnFalse_meta = turnFalse.getItemMeta();
        turnFalse_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                Main.getPlugin().getConfig().getString("connectfour.items.opponentsTurn.displayName")));
        turnFalse.setItemMeta(turnFalse_meta);

        //init Player-Heads
        ItemStack head_player1 =new ItemStack(Material.PLAYER_HEAD);
        SkullMeta head_player1_meta = (SkullMeta) head_player1.getItemMeta();
        head_player1_meta.setDisplayName(player1.getName());
        head_player1.setItemMeta(head_player1_meta);

        ItemStack head_player2 =new ItemStack(Material.PLAYER_HEAD);
        SkullMeta head_player2_meta = (SkullMeta) head_player2.getItemMeta();
        head_player2_meta.setDisplayName(player2.getName());
        head_player2.setItemMeta(head_player2_meta);



        //Column 1
        this.setItemWindow1(0, head_player1);
        this.setItemWindow2(0, head_player2);
        this.setUniversalItem(9, _void);
        //Turn-item 1 - 18
        //Turn-item 2 - 27
        this.setUniversalItem(36, _void);
        this.setItemWindow1(45, head_player2);
        this.setItemWindow2(45, head_player1);

        //Column 2
        this.setUniversalItem(1, _void);
        this.setUniversalItem(10, _void);
        this.setUniversalItem(19, _void);
        this.setUniversalItem(28, _void);
        this.setUniversalItem(37, _void);
        this.setUniversalItem(46, _void);

    }

    private void setUniversalItem(int slot, ItemStack item) {
        setItemWindow1(slot, item);
        setItemWindow2(slot, item);
    }

    private void setItemWindow1(int slot, ItemStack item) {
        window1.setItem(slot, item);
    }
    private void setItemWindow2(int slot, ItemStack item) {
        window2.setItem(slot, item);
    }

    private void updateTurnItems() {
        if (turn) {
            setItemWindow1(18, turnTrue);
            setItemWindow2(27, turnFalse);
        }else {
            setItemWindow1(18, turnFalse);
            setItemWindow2(27, turnTrue);
        }
    }

    protected void openInventories() {
        player1.openInventory(window1);
        player2.openInventory(window2);
    }

    protected void onClick(int slot, Player player) {
        int column = getColumns(slot);

        if (column == 0)
            return;

        if (!isTurn(player))
            return;

        placeChip(column, player);
        switchTurn();
    }

    private int getColumns(int slot) {
        switch (slot) {
            case 2: case 11: case 20: case 29: case 38: case 47:
                return 1;
            case 3: case 12: case 21: case 30: case 39: case 48:
                return 2;
            case 4: case 13: case 22: case 31: case 40: case 49:
                return 3;
            case 5: case 14: case 23: case 32: case 41: case 50:
                return 4;
            case 6: case 15: case 24: case 33: case 42: case 51:
                return 5;
            case 7: case 16: case 25: case 34: case 43: case 52:
                return 6;
            case 8: case 17: case 26: case 35: case 44: case 53:
                return 7;
        }
        return 0;
    }

    private boolean isTurn(Player player) {
        if (isInGame(player)) {
            ArrayList<Player> players = getPlayers();
            return (turn && players.get(0).equals(player)) || (!(turn) && players.get(1).equals(player));
        }
        return false;
    }

    protected ArrayList<Player> getPlayers() {
        ArrayList<Player> list = new ArrayList<>();
        list.add(player1);
        list.add(player2);
        return list;
    }

    protected boolean isInGame(Player search) {
        return getPlayers().contains(search);
    }

    private void placeChip(int column, Player player) {
        if (column < 1 || column > 7) {
            return;
        }
        int slot = 46;
        slot += column;

        while (isSlotEmpty(slot)) {
            //setUniversalItem(slot, );

            slot -= 9;
            if (slot < 0) {
                break;
            }
        }
    }

    private boolean isSlotEmpty(int slot) {
        return window1.getItem(slot) == null;
    }

    private void switchTurn() {
        this.turn = !this.turn;
        updateTurnItems();
    }
}
