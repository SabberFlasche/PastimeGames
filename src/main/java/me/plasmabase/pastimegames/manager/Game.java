package me.plasmabase.pastimegames.manager;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;

public abstract class Game {
    protected abstract void initMainWindow();
    protected abstract void openInventories();
    protected abstract void onClick(int slot, @NotNull Player player);
    protected abstract void onCloseInventory(@NotNull Player player);

    /**
     * Checks if the Player even is in this game and then checks if the Player has to make the next move
     * @param player Player to check
     * @return true if the Player {@link Connect4Game#isInGame(Player) isInGame} and it is his turn to make a move
     */
    public abstract boolean isTurn(@NotNull Player player);

    /**
     * Gets List of all Players who participate in this game
     * @return {@link Array} of all Players
     */
    public abstract @NotNull Player[] getPlayers();

    /**
     * @param search player to search for
     * @return true if the player is in this particular game
     */
    public abstract boolean isInGame(@NotNull Player search);

    /**
     * Closes all inventories and removes the game from {@link GameManager}.
     * At the end the Event {@link GameManager#gameEnded} is called
     * @param winner
     */
    public abstract void endGame(@Nullable Player winner);
}
