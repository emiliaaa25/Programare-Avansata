package org.example;

public class BoardPair {
    public Board board1;
    public Board board2;

    public BoardPair(Board board1, Board board2) {
        this.board1 = board1;
        this.board2 = board2;
        this.board1.turn = 1;
        this.board2.turn = 2;
    }

    public void deletePair() {
        this.board1.isTaken = false;
        this.board2.isTaken = false;
        NeedAlive.gamesStarted.remove(this);
    }
}
