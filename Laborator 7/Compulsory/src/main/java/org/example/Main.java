package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int n = 4;
        int numPlayers = 3;
        Bag bag = new Bag(n);
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player("Player " + (i + 1), bag, n));
        }

        for (Player player : players) {
            player.start();
        }

        for (Player player : players) {
            try {
                player.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}