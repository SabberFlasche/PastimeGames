package net.npsnetwork.pastimegames.manager.connectfour;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ConnectFourManager {
    private static ArrayList<CFGame> games = new ArrayList<>();
    public static void createGame(Player player1, Player player2) {
        CFGame game = new CFGame(player1, player2);

    }
}
