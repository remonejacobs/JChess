package com.chess.pieces;

public class Position {

    private final int row;
    private final int col;

    public Position(int x, int y) {
        this.row = x;
        this.col = y;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
