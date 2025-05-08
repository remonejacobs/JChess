package com.chess.pieces;

public class Queen extends Piece{

    public Queen(String color, int col, int row) {
        super(color, col, row);
    }

    @Override
    public int getValue() {
        return 9;
    }
}
