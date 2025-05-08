package com.chess.pieces;

public class King extends Piece{

    public King(String color, int col, int row) {
        super(color, col, row);
    }

    @Override
    public int getValue() {
        return 1000;
    }
}
