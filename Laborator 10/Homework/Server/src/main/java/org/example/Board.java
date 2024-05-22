package org.example;

import java.util.Random;

public class Board {
    public static int id = 0;
    public boolean isTaken = false;
    public int boardId;
    public boolean isEnemyAlive = false;

    public int turn;

    int [][] board = new int[11][11];
    Board(){
        id++;
        boardId = id;
        isTaken = false;
        board = getRandomBoard();
    }

    public int[][] getRandomBoard(){
        Random rand = new Random();
        board = NeedAlive.matrixes[rand.nextInt(10)];
        return board;
    }
}
