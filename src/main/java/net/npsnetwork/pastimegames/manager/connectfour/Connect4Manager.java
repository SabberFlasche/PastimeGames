package net.npsnetwork.pastimegames.manager.connectfour;

import net.npsnetwork.pastimegames.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class Connect4Manager {
    private static ArrayList<Connect4Game> games = new ArrayList<>();

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
    public static @Nullable Connect4Game createGame(@NotNull Player player1, @NotNull Player player2) {
        for (Connect4Game game : games) {
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

        Connect4Game game = new Connect4Game(player1, player2);
        games.add(game);
        return game;
    }

    public static void removeGame(@NotNull Connect4Game game) {
        games.remove(game);
    }

    public static ArrayList<Connect4Game> getGames() {
        return games;
    }

    public static @Nullable Connect4Game getGameByPlayer(@NotNull Player player) {
        for (Connect4Game game : games) {
            if (game.isInGame(player)) {
                return game;
            }
        }
        return null;
    }

    public static boolean onClick(int slot, @NotNull Player player) {
        for (Connect4Game game : games) {
            if (game.isInGame(player)) {
                game.onClick(slot, player);
                return true;
            }
        }
        return false;
    }

    public static void onCloseInventory(@NotNull Player player) {
        for (Connect4Game game : games) {
            if (game.isInGame(player)) {
                game.onCloseInventory(player);
            }
        }
    }

    public static boolean onDrag(@NotNull Player player) {
        return player.getOpenInventory().getTitle().equalsIgnoreCase(
                ChatColor.translateAlternateColorCodes('&',
                        Main.getPlugin().getConfig().getString("connectfour.invTitle")));
    }
}
