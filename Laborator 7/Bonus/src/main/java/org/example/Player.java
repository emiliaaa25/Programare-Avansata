package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player extends Thread {
    public final String namePlayer;
    public final Bag bag;
    public int points = 0;
    public final int n;
    public final Game game;
    public final List<Token> sequence;


    public List<Token> getSequence() {
        return sequence;
    }

    public Player(String namePlayer, Bag bag, int n, Game game) {
        this.namePlayer = namePlayer;
        this.bag = bag;
        this.n = n;
        this.game = game;
        this.sequence = new ArrayList<>();
    }

    public int getPoints() {
        return points;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    @Override
    public void run() {
        while (!bag.isEmpty() && points < n && !game.isTimeLimitExceeded()) {
            synchronized (game) {
                while (game.getCurrentPlayer() == this) {
                    try {
                        game.wait();
                    } catch (InterruptedException e) {
                        System.out.println("Player thread interrupted.");
                        return;
                    }
                }
            }

            Token token = bag.extract();

            if (token != null) {
                sequence.add(token);

                while (sequence.size() < n) {
                    Token nextToken = bag.extract();
                    if (nextToken != null && isNextTokenValid(sequence, nextToken)) {
                        sequence.add(nextToken);
                    } else {
                        break;
                    }
                }
                points = sequence.size();
                System.out.println(namePlayer + " created sequence " + sequence + " and has " + points + " points.");
                game.playerFinishedTurn();
                synchronized (game) {
                    game.notifyAll();
                }
            }
        }
    }

    private boolean isNextTokenValid(List<Token> sequence, Token nextToken) {
        if (sequence.isEmpty()) {
            return true;
        } else {
            Token lastToken = sequence.get(sequence.size() - 1);
            return lastToken.second == nextToken.first;
        }
    }
}
