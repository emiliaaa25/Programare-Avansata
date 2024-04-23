package org.example;

import java.util.List;

public class SmartPlayer extends Player {

    public SmartPlayer(String namePlayer, Bag bag, int n, Game game) {
        super(namePlayer, bag, n, game);
    }

    @Override
    public void run() {
        while (!bag.isEmpty() && ! game.isMaxPointsReached() && !game.isTimeLimitExceeded()) {
            synchronized (game) {
                while (game.getCurrentPlayer() == this&& game.isGameFinished()) {
                    try {
                        game.wait();
                    } catch (InterruptedException e) {
                        System.out.println("Player thread interrupted.");
                        return;
                    }
                }
            }

            makeSmartMove();

            game.playerFinishedTurn();
            synchronized (game) {
                game.notifyAll();
            }
        }
    }

    private void makeSmartMove() {
        List<Token> availableTiles = bag.getTokens();
        List<Token> currentSequence = getSequence();
        if (currentSequence.size() == n) {
            synchronized (game) {
                game.setMaxPointsReached(true);
                game.setWinner();
                game.notifyAll();
            }
            return;
        }
        if(game.isMaxPointsReached()){
            return;
        }

        if (currentSequence.size() < n) {
            Token bestToken = findBestToken(availableTiles, currentSequence);
            if (bestToken != null&&bestToken.first != bestToken.second) {
                sequence.add(bestToken);
                points = sequence.size();
                System.out.println(namePlayer + " created sequence " + sequence + " and has " + points + " points.");
            }
        }
    }

    private boolean findGoodToken(List<Token> currentSequence, Token nextToken) {
        for (Token token : currentSequence) {
            if (token.first != nextToken.second)
                return true;
        }
        return false;
    }

    private Token findBestToken(List<Token> availableTiles, List<Token> currentSequence) {
        for (Token token : availableTiles) {
            if (isNextTokenValid(currentSequence, token)&&token.first!=token.second) {
                return token;
            }
        }
        return null;
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
