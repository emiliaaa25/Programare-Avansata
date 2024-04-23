package org.example;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final int numPlayers;
    private int currentPlayerIndex = 0;
    private TimeKeeper timekeeperThread;
    private boolean timeLimitExceeded = false;
    private final List<Player> players = new ArrayList<>();
    List<Integer> points = new ArrayList<>();
    private final long timeLimitMillis;

    public Game(int n, int numPlayers, long timeLimitMillis) {
        Bag bag = new Bag(n);
        this.numPlayers = numPlayers;
        this.timeLimitMillis = timeLimitMillis;

        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player("Player " + (i + 1), bag, n, this));
            points.add(0);
        }
    }

    public synchronized boolean isTimeLimitExceeded() {
        return timeLimitExceeded;
    }

    public synchronized void setTimeLimitExceeded(boolean timeLimitExceeded) {
        this.timeLimitExceeded = timeLimitExceeded;
    }

    public void startGame() {
        timekeeperThread = new TimeKeeper(this, timeLimitMillis);
        timekeeperThread.start();

        for (Player player : players) {
            player.start();
        }
        try {
            for (Player player : players) {
                player.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!timekeeperThread.isTimeLimitExceeded()) {
            updatePoints();
            determineWinner();
        }
    }


    public void updatePoints() {
        for (int i = 0; i < numPlayers; i++) {
            points.set(i, players.get(i).getPoints());
        }
    }

    public synchronized Player getCurrentPlayer() {
            return players.get(currentPlayerIndex);
    }

    public synchronized void playerFinishedTurn() {
        currentPlayerIndex =(currentPlayerIndex+1)%numPlayers;
        notifyAll();

    }

    public void determineWinner() {
        List<Player> winners = new ArrayList<>();
        int maxPoints = 0;
        int winner = -1;
        for (int i = 0; i < numPlayers; i++) {
            if (points.get(i) > maxPoints) {
                maxPoints = points.get(i);
                winner = i;
            }
        }
        for(int i = 0; i < numPlayers; i++){
            if(points.get(i) == maxPoints) {
                winners.add(players.get(i));
            }
        }
        if (winner == -1) {
            System.out.println("No winner! All players have 0 points");
            System.out.println("The winner is " + players.get(winner).getNamePlayer());
        } else if(winners.size() == 1){
            System.out.println("The winner is " + winners.get(0).getNamePlayer());

        }
        else{
            System.out.println("The winners are: ");
            for(Player player: winners){
                System.out.println(player.getNamePlayer());
            }
        }
        stopGame();
    }

    public void stopGame() {
        if (timekeeperThread != null && timekeeperThread.isAlive()) {
            timekeeperThread.interrupt();
        }
        synchronized (this) {
            notifyAll();
        }
    }
}


