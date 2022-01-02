package me.plasmabase.pastimegames.helper.eventsystem;

import me.plasmabase.pastimegames.manager.Games.GameType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GameResultConnect4 extends GameResult{

    private Player winner;
    private Player[] players;

    public GameResultConnect4(@Nullable Player winner, @NotNull Player[] players) {
        this.winner = winner;
        this.players = players;
    }

    @Override
    public Player getWinner() {
        return winner;
    }

    @Override
    public Player[] getPlayers() {
        return players;
    }

    @Override
    public @NotNull GameType getGameType() {
        return GameType.CONNECT4;
    }
}
