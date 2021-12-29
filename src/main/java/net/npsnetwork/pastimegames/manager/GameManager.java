package net.npsnetwork.pastimegames.manager;

import net.npsnetwork.pastimegames.helper.eventsystem.EventManager;
import net.npsnetwork.pastimegames.helper.eventsystem.GameResult;
import net.npsnetwork.pastimegames.manager.connectfour.Connect4Game;
import net.npsnetwork.pastimegames.manager.connectfour.Connect4Manager;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class GameManager {

    /**
     * Creates a game of connect 4 and starts it directly
     * The player who begins is random
     * If the player1 and player2 are the same or one player is already in a game the game won't be
     * created and returns null
     * @param player1 player1
     * @param player2 player2
     * @return The {@link Connect4Game Connect4Game} if player1 and player2 are different Player,
     * and they are not already in a game
     */
    public static @Nullable Connect4Game createConnect4Game(@NotNull Player player1, @NotNull Player player2) {
        return Connect4Manager.createGame(player1, player2);
    }

    /**
     * Gets an ArrayList with all Connect4 games
     * @return All Connect4 games
     */
    public static @NotNull ArrayList<Connect4Game> getAllConnect4Games() {
        return Connect4Manager.getGames();
    }

    /**
     * Gets the {@link Connect4Game Connect4Game}, the {@link Player Player} plays
     * @param player the Player to search for in all games
     * @return the Game where the Player is in if he is in one, null otherwise
     */
    public static @Nullable Connect4Game getConnect4GameByPlayer(@NotNull Player player) {
        return Connect4Manager.getGameByPlayer(player);
    }

    /**
     * Event GameEnded:
     * Called when any Game ends after the game has been removed from all Managers
     * Use GameManger.gameEnded.subscribe(new YourClass()) to add a listener (YourClass has to implement
     * {@linkplain net.npsnetwork.pastimegames.helper.eventsystem.EventListener<GameResult> EventListener<GameResult>}
     */
    public static EventManager<GameResult> gameEnded = new EventManager<>();
}
