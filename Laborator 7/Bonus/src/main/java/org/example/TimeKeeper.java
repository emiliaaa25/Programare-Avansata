package org.example;

import java.util.concurrent.TimeUnit;

public class TimeKeeper extends Thread {
    private final Game game;
    private final long timeLimitMillis;
    private final long startTimeMillis;


    public TimeKeeper(Game game, long timeLimitMillis) {
        this.game = game;
        this.timeLimitMillis = timeLimitMillis;
        this.startTimeMillis = System.currentTimeMillis();
        setDaemon(true);
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            long elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;
            if (elapsedTimeMillis >= timeLimitMillis) {
                System.out.println("Game time limit exceeded. Stopping the game.");
                game.stopGame();
                game.setTimeLimitExceeded(true);
                break;
            }

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public boolean isTimeLimitExceeded() {
        return false;
    }
}
