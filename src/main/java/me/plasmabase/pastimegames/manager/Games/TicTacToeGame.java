package me.plasmabase.pastimegames.manager.Games;

import me.plasmabase.pastimegames.PastimeGames;
import me.plasmabase.pastimegames.helper.Glow;
import me.plasmabase.pastimegames.helper.eventsystem.GameResultConnect4;
import me.plasmabase.pastimegames.helper.eventsystem.GameResultTicTacToe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class TicTacToeGame extends Game{
    private Inventory window1;
    private Inventory window2;
    private Player player1;
    private Player player2;
    private boolean turn;
    private boolean running;

    protected TicTacToeGame(@NotNull Player player1, @NotNull Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        window1 = Bukkit.createInventory(null, 5 * 9, PastimeGames.settingsManager().tictactoeInventoryTitle());
        window2 = Bukkit.createInventory(null, 5 * 9, PastimeGames.settingsManager().tictactoeInventoryTitle());
        determineFirstTurn();
        initMainWindow();
        updateTurnItems();
        running = true;
        openInventories();
    }

    protected TicTacToeGame(@NotNull Player player1, @NotNull Player player2, @NotNull String customInvTitle) {
        this.player1 = player1;
        this.player2 = player2;
        window1 = Bukkit.createInventory(null, 5 * 9, PastimeGames.settingsManager().tictactoeInventoryTitle());
        window2 = Bukkit.createInventory(null, 5 * 9, PastimeGames.settingsManager().tictactoeInventoryTitle());
        determineFirstTurn();
        initMainWindow();
        updateTurnItems();
        running = true;
        openInventories();
    }

    private void determineFirstTurn() {
        Random random = new Random();
        turn = random.nextBoolean();
    }

    @Override
    protected void initMainWindow() {
        //placholders
        setUniversalItem(0, Items._void());
        setUniversalItem(1, Items._void());
        setUniversalItem(2, Items._void());
        setUniversalItem(3, Items._void());
        setUniversalItem(4, Items._void());
        setUniversalItem(5, Items._void());
        setUniversalItem(6, Items._void());
        setUniversalItem(7, Items._void());
        setUniversalItem(8, Items._void());
        setUniversalItem(9, Items._void());
        setUniversalItem(10, Items._void());
        setUniversalItem(11, Items._void());
        setUniversalItem(15, Items._void());
        setUniversalItem(16, Items._void());
        setUniversalItem(17, Items._void());
        setUniversalItem(20, Items._void());
        setUniversalItem(24, Items._void());
        setUniversalItem(27, Items._void());
        setUniversalItem(28, Items._void());
        setUniversalItem(29, Items._void());
        setUniversalItem(33, Items._void());
        setUniversalItem(34, Items._void());
        setUniversalItem(35, Items._void());
        setUniversalItem(36, Items._void());
        setUniversalItem(37, Items._void());
        setUniversalItem(38, Items._void());
        setUniversalItem(39, Items._void());
        setUniversalItem(40, Items._void());
        setUniversalItem(41, Items._void());
        setUniversalItem(42, Items._void());
        setUniversalItem(43, Items._void());
        setUniversalItem(44, Items._void());

        //playerHeads
        ItemStack playerHead1 = Items.playerHead(player1);
        ItemStack playerHead2 = Items.playerHead(player2);

        setItemWindow1(19, playerHead1);
        setItemWindow2(19, playerHead2);
        setItemWindow1(25, playerHead2);
        setItemWindow2(25, playerHead1);
    }

    private void updateTurnItems() {
        if (turn) {
            //true
            setItemWindow1(9, Items.turnTrue());
            setItemWindow2(9, Items.turnFalse());
            setItemWindow1(18, Items.turnTrue());
            setItemWindow2(18, Items.turnFalse());
            setItemWindow1(27, Items.turnTrue());
            setItemWindow2(27, Items.turnFalse());
            //false
            setItemWindow1(17, Items.turnFalse());
            setItemWindow2(17, Items.turnTrue());
            setItemWindow1(26, Items.turnFalse());
            setItemWindow2(26, Items.turnTrue());
            setItemWindow1(35, Items.turnFalse());
            setItemWindow2(35, Items.turnTrue());
        }else {
            //true
            setItemWindow2(9, Items.turnTrue());
            setItemWindow1(9, Items.turnFalse());
            setItemWindow2(18, Items.turnTrue());
            setItemWindow1(18, Items.turnFalse());
            setItemWindow2(27, Items.turnTrue());
            setItemWindow1(27, Items.turnFalse());
            //false
            setItemWindow2(17, Items.turnFalse());
            setItemWindow1(17, Items.turnTrue());
            setItemWindow2(26, Items.turnFalse());
            setItemWindow1(26, Items.turnTrue());
            setItemWindow2(35, Items.turnFalse());
            setItemWindow1(35, Items.turnTrue());
        }
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

    @Override
    protected void openInventories() {
        player1.openInventory(window1);
        player2.openInventory(window2);
    }

    @Override
    protected void onClick(int slot, @NotNull Player player) {
        if (running && isTurn(player) && isInGameArea(slot) && isSlotEmpty(slot)) {
            placeChip(slot, player);
        }
    }

    private boolean isInGameArea(int slot) {
        switch (slot) {
            case 12: case 13: case 14: case 21: case 22: case 23: case 30: case 31: case 32:
                return true;
            default:
                return false;
        }
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

    /**
     * Checks if the Player even is in this game and then checks if the Player has to make the next move
     * @param player Player to check
     * @return true if the Player {@link TicTacToeGame#isInGame(Player) isInGame} and it is his turn to make a move
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

    private void placeChip(int slot, @NotNull Player player) {
        ItemStack chipTrue = Items.chipTrue();
        ItemStack chipFalse = Items.chipFalse();

        boolean isPlayer1 = player.equals(player1);
        if (isPlayer1) {
            setItemWindow1(slot, chipTrue);
            setItemWindow2(slot, chipFalse);
        }else {
            setItemWindow1(slot, chipFalse);
            setItemWindow2(slot, chipTrue);
        }

        checkForWinner(isPlayer1, slot);
        switchTurn();
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
        if (player1.getOpenInventory().getTitle().equalsIgnoreCase(PastimeGames.settingsManager().tictactoeInventoryTitle())) {
            player1.closeInventory();
        }
        if (player2.getOpenInventory().getTitle().equalsIgnoreCase(PastimeGames.settingsManager().tictactoeInventoryTitle())) {
            player2.closeInventory();
        }
        GameManager.removeGame(this);
        try {
            GameManager.gameEnded.call(new GameResultTicTacToe(winner, this.getPlayers()));
        }catch (IllegalStateException ignored) {}
    }

    private void winnerLogic(boolean winner, @Nullable ArrayList<Integer> slots) {
        running = false;
        Glow glow = new Glow(new NamespacedKey(PastimeGames.plugin(), "gloww"));
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
        Bukkit.getScheduler().scheduleSyncDelayedTask(PastimeGames.plugin(), () -> endGame(winner ? player1 : player2), 60);
    }

    private void checkForWinner(boolean p1or2, int slot) {
        ItemStack chipTrue = Items.chipTrue();
        Inventory inventory;
        ArrayList<Integer> slots = new ArrayList<>();

        if (p1or2) {
            inventory = window1;
        }else {
            inventory = window2;
        }

        //Rows and Columns
        for (int i = 0; i < 3; i++) {
            int startingSlot = 12 + 9 * i;
            slots.addAll(this.checkForWinnerRow(startingSlot, inventory));

            startingSlot = 12 + i;
            slots.addAll(this.checkForWinnerColumn(startingSlot, inventory));
        }

        //Diagonal +
        if (!isSlotEmpty(14) && inventory.getItem(14).equals(chipTrue)
                && !isSlotEmpty(22) && inventory.getItem(22).equals(chipTrue)
                && !isSlotEmpty(30) && inventory.getItem(30).equals(chipTrue)) {
            slots.add(14);
            slots.add(22);
            slots.add(30);
        }

        //Diagonal -
        if (!isSlotEmpty(12) && inventory.getItem(12).equals(chipTrue)
                && !isSlotEmpty(22) && inventory.getItem(22).equals(chipTrue)
                && !isSlotEmpty(32) && inventory.getItem(32).equals(chipTrue)) {
            slots.add(12);
            slots.add(22);
            slots.add(32);
        }

        if (slots.size() >= 3) {
            winnerLogic(p1or2, slots);
        }
    }

    private ArrayList<Integer> checkForWinnerRow(int startSlot, Inventory inventory) {
        ItemStack chipTrue = Items.chipTrue();
        ArrayList<Integer> slots = new ArrayList<>();
        if (!isSlotEmpty(startSlot) && inventory.getItem(startSlot).equals(chipTrue)
                && !isSlotEmpty(startSlot + 1) && inventory.getItem(startSlot + 1).equals(chipTrue)
                && !isSlotEmpty(startSlot + 2) && inventory.getItem(startSlot + 2).equals(chipTrue)) {
            slots.add(startSlot);
            slots.add(startSlot + 1);
            slots.add(startSlot + 2);
        }
        return slots;
    }

    private ArrayList<Integer> checkForWinnerColumn(int startSlot, Inventory inventory) {
        ItemStack chipTrue = Items.chipTrue();
        ArrayList<Integer> slots = new ArrayList<>();
        if (!isSlotEmpty(startSlot) && inventory.getItem(startSlot).equals(chipTrue)
                && !isSlotEmpty(startSlot + 9) && inventory.getItem(startSlot + 9).equals(chipTrue)
                && !isSlotEmpty(startSlot + 9 * 2) && inventory.getItem(startSlot + 9 * 2).equals(chipTrue)) {
            slots.add(startSlot);
            slots.add(startSlot + 9);
            slots.add(startSlot + 9 * 2);
        }
        return slots;
    }
}
