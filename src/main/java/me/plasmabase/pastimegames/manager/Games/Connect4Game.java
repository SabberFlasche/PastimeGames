package me.plasmabase.pastimegames.manager.Games;

import me.plasmabase.pastimegames.Main;
import me.plasmabase.pastimegames.helper.Glow;
import me.plasmabase.pastimegames.helper.eventsystem.GameResultConnect4;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Connect4Game extends Game {
    private Inventory window1;
    private Inventory window2;
    private Player player1;
    private Player player2;
    private ItemStack turnTrue;
    private ItemStack turnFalse;
    private ItemStack chipTrue;
    private ItemStack chipFalse;
    private boolean turn;
    private boolean running;
    private boolean placing;

    protected Connect4Game(@NotNull Player player1, @NotNull Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        window1 = Bukkit.createInventory(null, 6 * 9, Main.settingsManager().connect4InventoryTitle());
        window2 = Bukkit.createInventory(null, 6 * 9, Main.settingsManager().connect4InventoryTitle());
        determineFirstTurn();
        initMainWindow();
        updateTurnItems();
        running = true;
        placing = false;
        openInventories();
    }

    protected Connect4Game(@NotNull Player player1, @NotNull Player player2, @NotNull String customInvTitle) {
        this.player1 = player1;
        this.player2 = player2;
        window1 = Bukkit.createInventory(null, 6 * 9, customInvTitle);
        window2 = Bukkit.createInventory(null, 6 * 9, customInvTitle);
        determineFirstTurn();
        initMainWindow();
        updateTurnItems();
        running = true;
        placing = false;
        openInventories();
    }

    private void determineFirstTurn() {
        Random random = new Random();
        turn = random.nextBoolean();
    }

    @Override
    protected void initMainWindow() {
        //init placeholder-item
        ItemStack _void = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta _void_meta = _void.getItemMeta();
        _void_meta.setDisplayName(Main.settingsManager().connect4PlaceholderItemDisplayName());
        //ArrayList<String> _void_lore = new ArrayList<>();
        //_void_meta.setLore(_void_lore);
        _void.setItemMeta(_void_meta);

        //init turn-items
        turnTrue = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta turnTrue_meta = turnTrue.getItemMeta();
        turnTrue_meta.setDisplayName(Main.settingsManager().connect4YourTurnItemDisplayName());
        turnTrue.setItemMeta(turnTrue_meta);

        turnFalse = new ItemStack(Material.GLASS_PANE);
        ItemMeta turnFalse_meta = turnFalse.getItemMeta();
        turnFalse_meta.setDisplayName(Main.settingsManager().connect4OpponentsTurnItemDisplayName());
        turnFalse.setItemMeta(turnFalse_meta);

        //init chip-items
        chipTrue = new ItemStack(Material.ENDER_PEARL);
        ItemMeta chipTrue_meta = chipTrue.getItemMeta();
        chipTrue_meta.setDisplayName(Main.settingsManager().connect4YourChipItemDisplayName());
        chipTrue.setItemMeta(chipTrue_meta);

        chipFalse = new ItemStack(Material.SNOWBALL);
        ItemMeta chipFalse_meta = chipFalse.getItemMeta();
        chipFalse_meta.setDisplayName(Main.settingsManager().connect4OpponentsChipItemDisplayName());
        chipFalse.setItemMeta(chipFalse_meta);

        //init Player-Heads
        ItemStack head_player1 =new ItemStack(Material.PLAYER_HEAD);
        SkullMeta head_player1_meta = (SkullMeta) head_player1.getItemMeta();
        head_player1_meta.setDisplayName(player1.getName());
        head_player1_meta.setOwningPlayer(player1);
        head_player1.setItemMeta(head_player1_meta);

        ItemStack head_player2 =new ItemStack(Material.PLAYER_HEAD);
        SkullMeta head_player2_meta = (SkullMeta) head_player2.getItemMeta();
        head_player2_meta.setDisplayName(player2.getName());
        head_player2_meta.setOwningPlayer(player2);
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

    private void setUniversalItem(int slot, @Nullable ItemStack item) {
        setItemWindow1(slot, item);
        setItemWindow2(slot, item);
    }

    private void setItemWindow1(int slot, @Nullable ItemStack item) {
        window1.setItem(slot, item);
    }
    private void setItemWindow2(int slot, @Nullable ItemStack item) {
        window2.setItem(slot, item);
    }

    private void updateTurnItems() {
        if (turn) {
            setItemWindow1(18, turnTrue);
            setItemWindow1(27, turnTrue);
            setItemWindow2(18, turnFalse);
            setItemWindow2(27, turnFalse);
        }else {
            setItemWindow1(18, turnFalse);
            setItemWindow1(27, turnFalse);
            setItemWindow2(18, turnTrue);
            setItemWindow2(27, turnTrue);
        }
    }

    @Override
    protected void openInventories() {
        player1.openInventory(window1);
        player2.openInventory(window2);
    }

    @Override
    protected void onClick(int slot, @NotNull Player player) {
        int column = getColumns(slot);

        if (column == 0)
            return;

        if (!running)
            return;

        if (placing)
            return;

        if (!isTurn(player))
            return;

        placeChip(column, player);

    }

    @Override
    protected void onCloseInventory(@NotNull Player player) {
        GameManager.removeGame(this);
        if (player.equals(player1)) {
            try {
                player2.closeInventory();
            }catch (Exception ignored) {}
        }else {
            try {
                player1.closeInventory();
            }catch (Exception ignored) {}
        }
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

    /**
     * Checks if the Player even is in this game and then checks if the Player has to make the next move
     * @param player Player to check
     * @return true if the Player {@link Connect4Game#isInGame(Player) isInGame} and it is his turn to make a move
     */
    @Override
    public boolean isTurn(@NotNull Player player) {
        if (isInGame(player)) {
            Player[] players = getPlayers();
            return (turn && players[0].equals(player)) || (!(turn) && players[1].equals(player));
        }
        return false;
    }

    /**
     * Gets List of all Players who participate in this game
     * @return {@link Array} of all Players
     */
    @Override
    public @NotNull Player[] getPlayers() {
        Player[] list = new Player[2];
        list[0] = player1;
        list[1] = player2;
        return list;
    }

    /**
     * @param search player to search for
     * @return true if the player is in this particular game
     */
    @Override
    public boolean isInGame(@NotNull Player search) {
        for (Player player : getPlayers()) {
            if (player.equals(search)) {
                return true;
            }
        }
        return false;
    }

    private void placeChip(int column, @NotNull Player player) {
        placing = true;
        if (column < 1 || column > 7) {
            return;
        }
        int currentSlot = 1;
        currentSlot += column;
        boolean isPlayer1 = player.equals(player1);
        int x = 0;
        int delay = 3;

        if (player.getOpenInventory().getItem(currentSlot) != null) {
            placing = false;
            return;
        }
        while (isSlotEmpty(currentSlot)) {
            int finalSlot = currentSlot;
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin(), ()-> {
                if (isPlayer1) {
                    setItemWindow1(finalSlot, chipTrue);
                    setItemWindow2(finalSlot, chipFalse);
                }else {
                    setItemWindow2(finalSlot, chipTrue);
                    setItemWindow1(finalSlot, chipFalse);
                }
                if (finalSlot > 10) {
                    setUniversalItem(finalSlot - 9, null);
                }
            }, delay * x);

            currentSlot += 9;
            x++;
            if (currentSlot > 53) {
                break;
            }
        }
        int finalCurrentSlot = currentSlot - 9;
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin(), () -> {
            checkForWinner(isPlayer1, finalCurrentSlot);
            switchTurn();
            placing = false;
        }, delay * x);
    }

    private boolean isSlotEmpty(int slot) {
        return window1.getItem(slot) == null;
    }

    private void switchTurn() {
        this.turn = !this.turn;
        updateTurnItems();
    }

    /**
     * Closes all inventories and removes the game from {@link GameManager#getAllGames()}.
     * At the end the Event {@link GameManager#gameEnded} is called
     * @param winner
     */
    @Override
    public void endGame(@Nullable Player winner) {
        if (player1.getOpenInventory().getTitle().equalsIgnoreCase(Main.settingsManager().connect4InventoryTitle())) {
            player1.closeInventory();
        }
        if (player2.getOpenInventory().getTitle().equalsIgnoreCase(Main.settingsManager().connect4InventoryTitle())) {
            player2.closeInventory();
        }
        GameManager.removeGame(this);
        GameManager.gameEnded.call(new GameResultConnect4(winner, this.getPlayers()));
    }

    private void winnerLogic(boolean winner, @Nullable ArrayList<Integer> slots) {
        running = false;
        Glow glow = new Glow(new NamespacedKey(Main.plugin(), "gloww"));
        try {
            for (int slot : slots) {
                ItemStack stack1 = window1.getItem(slot);
                stack1.addUnsafeEnchantment(glow, 1);
                ItemStack stack2 = window2.getItem(slot);
                stack2.addUnsafeEnchantment(glow, 1);
            }
        }catch (NullPointerException e) {}

        //playsound minecraft:item.totem.use ambient @p ~ ~ ~ 1 1
        //playsound minecraft:entity.player.levelup ambient @p ~ ~ ~ 2 1
        if (winner) {
            player1.playSound(player1.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 1);
            player2.playSound(player2.getLocation(), Sound.ITEM_TOTEM_USE, 1, 1);
        }else {
            player1.playSound(player1.getLocation(), Sound.ITEM_TOTEM_USE, 1, 1);
            player2.playSound(player2.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 1);
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin(), () -> endGame(winner ? player1 : player2), 60);
    }

    private void checkForWinner(boolean p1or2, int slot) {
        //System.out.println(p1or2);
        Inventory inventory;
        ArrayList<Integer> slots = new ArrayList<>();

        if (p1or2) {
            inventory = window1;
        }else {
            inventory = window2;
        }
        //Rows
        ArrayList<Integer> tmpSlots = new ArrayList<>();
        int currentSlot = slot;
        while (inventory.getItem(currentSlot) != null && inventory.getItem(currentSlot).equals(chipTrue)) {
            tmpSlots.add(currentSlot);
            currentSlot++;
            if (currentSlot > 53) {
                break;
            }
        }
        currentSlot = slot;
        currentSlot--;
        while (inventory.getItem(currentSlot) != null && inventory.getItem(currentSlot).equals(chipTrue)) {
            tmpSlots.add(currentSlot);
            currentSlot--;
            if (currentSlot < 2) {
                break;
            }
        }
        if (tmpSlots.size() >= 4) {
            slots.addAll(tmpSlots);
        }

        //Columns
        tmpSlots = new ArrayList<>();
        currentSlot = slot;
        while (inventory.getItem(currentSlot) != null && inventory.getItem(currentSlot).equals(chipTrue)) {
            tmpSlots.add(currentSlot);
            currentSlot += 9;
            if (currentSlot > 53) {
                break;
            }
        }
        if (tmpSlots.size() >= 4) {
            slots.addAll(tmpSlots);
        }

        //diagonal 45°
        tmpSlots = new ArrayList<>();
        currentSlot = slot;
        while (inventory.getItem(currentSlot) != null && inventory.getItem(currentSlot).equals(chipTrue)) {
            tmpSlots.add(currentSlot);
            currentSlot += 8;
            if (currentSlot > 53) {
                break;
            }
        }
        currentSlot = slot;
        currentSlot -= 8;
        while (currentSlot > 1 && inventory.getItem(currentSlot) != null && inventory.getItem(currentSlot).equals(chipTrue)) {
            tmpSlots.add(currentSlot);
            currentSlot -= 8;
            if (currentSlot < 2) {
                break;
            }
        }
        if (tmpSlots.size() >= 4) {
            slots.addAll(tmpSlots);
        }

        //diagonal -45°
        tmpSlots = new ArrayList<>();
        currentSlot = slot;
        while (inventory.getItem(currentSlot) != null && inventory.getItem(currentSlot).equals(chipTrue)) {
            tmpSlots.add(currentSlot);
            currentSlot += 10;
            if (currentSlot > 53) {
                break;
            }
        }
        currentSlot = slot;
        currentSlot -= 10;
        while (currentSlot > 1 && inventory.getItem(currentSlot) != null && inventory.getItem(currentSlot).equals(chipTrue)) {
            tmpSlots.add(currentSlot);
            currentSlot -= 10;
            if (currentSlot < 2) {
                break;
            }
        }
        if (tmpSlots.size() >= 4) {
            slots.addAll(tmpSlots);
        }



        //General winner-logic
        if (slots.size() >= 4) {
            winnerLogic(p1or2, slots);
        }
    }
}
