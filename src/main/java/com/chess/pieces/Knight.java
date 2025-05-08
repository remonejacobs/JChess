package com.chess.pieces;

public class Knight extends Piece{

    public Knight(String color, int col, int row) {
        super(color, col, row);
    }

    @Override
    public int getValue() {
        return 3;
    }
}
