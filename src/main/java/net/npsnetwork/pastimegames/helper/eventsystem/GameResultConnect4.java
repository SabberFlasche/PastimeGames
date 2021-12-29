package net.npsnetwork.pastimegames.helper.eventsystem;

import net.npsnetwork.pastimegames.manager.GameManager;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

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
}
