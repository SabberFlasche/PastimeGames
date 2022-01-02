package me.plasmabase.pastimegames.helper.eventsystem;

import me.plasmabase.pastimegames.manager.Games.GameType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class GameResult {
    /**
     * Gets the Winner
     * @return {@link Player Player} who won
     */
    public abstract @Nullable Player getWinner ();

    /**
     * Gets an Array of all Players who participated in the game
     * @return {@link java.lang.reflect.Array Array} with all {@link Player Players}
     */
    public abstract @NotNull Player[] getPlayers ();

    /**
     * Gets the GameType
     * @return GameType of the game which ended
     */
    public abstract @NotNull GameType getGameType();
}
