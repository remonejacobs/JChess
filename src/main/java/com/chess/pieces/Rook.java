package com.chess.pieces;

public class Rook extends Piece{

    public Rook(String color, int col, int row) {
        super(color, col, row);
    }

    @Override
    public int getValue() {
        return 5;
    }
}
