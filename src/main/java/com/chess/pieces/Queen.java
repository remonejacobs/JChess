package com.chess.pieces;

public class Queen extends Piece{

    public Queen(String color) {
        super(color);
    }

    @Override
    public int getValue() {
        return 9;
    }
}
