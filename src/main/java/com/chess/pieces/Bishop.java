package com.chess.pieces;

public class Bishop extends Piece{

    public Bishop(String color, int col, int row) {
        super(color, col, row);
    }

    @Override
    public int getValue() {
        return 3;
    }
}
