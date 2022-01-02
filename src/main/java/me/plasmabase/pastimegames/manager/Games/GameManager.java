package me.plasmabase.pastimegames.manager.Games;

import me.plasmabase.pastimegames.PastimeGames;
import me.plasmabase.pastimegames.helper.eventsystem.EventListener;
import me.plasmabase.pastimegames.helper.eventsystem.EventManager;
import me.plasmabase.pastimegames.helper.eventsystem.GameResult;
import me.plasmabase.pastimegames.manager.SettingsManager;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class GameManager {

    private static final ArrayList<Game> games = new ArrayList<>();

    /**
     * Event GameEnded:
     * Called when any Game ends after the game has been removed from all Managers
     * Use GameManger.gameEnded.subscribe(new YourClass()) to add a listener (YourClass has to implement
     * {@linkplain EventListener<GameResult>}
     */
    public static EventManager<GameResult> gameEnded = new EventManager<>();

    /**
     * Creates a game of given type and starts it directly.
     * If the player1 and player2 are the same or one player is already in a game the game won't be
     * created and returns null.
     * @param player1 player1
     * @param player2 player2
     * @return The {@link Game} if player1 and player2 are different Players,
     * and they are not already in a game.
     */
    public static @Nullable Game createGame(@NotNull GameType gameType, @NotNull Player player1, @NotNull Player player2) {
        for (Game game : games) {
            if (game.isInGame(player1)) {
                return null;
            }
            if (game.isInGame(player2)) {
                return null;
            }
        }

        if (player1.equals(player2)) {
            return null;
        }
        Game game;
        switch (gameType) {
            case CONNECT4:
                game = new Connect4Game(player1, player2);
                break;
            case TICTACTOE:
                game = new TicTacToeGame(player1, player2);
                break;
            default:
                return null;
        }
        games.add(game);
        return game;
    }

    /**
     * Creates a game of given type and starts it directly.
     * If the player1 and player2 are the same or one player is already in a game the game won't be
     * created and returns null.
     * @param player1 player1
     * @param player2 player2
     * @param customInvTitle custom String to use as title in the inventory
     * @return The {@link Game} if player1 and player2 are different Players,
     * and they are not already in a game.
     */
    public static @Nullable Game createGame(@NotNull GameType gameType, @NotNull Player player1, @NotNull Player player2, @NotNull String customInvTitle) {
        for (Game game : games) {
            if (game.isInGame(player1)) {
                return null;
            }
            if (game.isInGame(player2)) {
                return null;
            }
        }

        if (player1.equals(player2)) {
            return null;
        }
        Game game;
        switch (gameType) {
            case CONNECT4:
                game = new Connect4Game(player1, player2, customInvTitle);
                break;
            case TICTACTOE:
                game = new TicTacToeGame(player1, player2, customInvTitle);
                break;
            default:
                return null;
        }
        games.add(game);
        return game;
    }

    /**
     * Gets an ArrayList with all {@link Game games}.
     * @return All Connect4 games
     */
    public static @NotNull ArrayList<Game> getAllGames() {
        return games;
    }

    /**
     * @return SettingsManager of PastimeGamesAPI
     */
    public static @NotNull SettingsManager getSettingsManager() {
        return PastimeGames.settingsManager();
    }

    /**
     * Gets the {@link Game Game}, the {@link Player Player} plays
     * @param player the Player to search for in all games
     * @return the Game where the Player is in if he is in one, null otherwise
     */
    public static @Nullable Game getGameByPlayer(@NotNull Player player) {
        for (Game game : games) {
            if (game.isInGame(player)) {
                return game;
            }
        }
        return null;
    }

    protected static void removeGame(@NotNull Game game) {
        games.remove(game);
    }

    public static boolean onClick(int slot, @NotNull Player player) {
        for (Game game : games) {
            if (game.isInGame(player)) {
                game.onClick(slot, player);
                return true;
            }
        }
        return false;
    }

    public static void onCloseInventory(@NotNull Player player) {
        try {
            for (Game game : games) {
                if (game.isInGame(player)) {
                    game.onCloseInventory(player);
                }
            }
        }catch (Exception ignored) {}
    }

    public static boolean onDrag(@NotNull Player player) {
        for (Game game : games) {
            if (game.isInGame(player)) {
                return true;
            }
        }
        return false;
    }
}
