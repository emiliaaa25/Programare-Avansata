package org.example;

public class Main {
    public static void main(String[] args) {
        int n = 10;
        int numPlayers = 3;
        long timeLimitMillis = 50000;
        Game game = new Game(n, numPlayers,timeLimitMillis);
        game.startGame();
    }
}