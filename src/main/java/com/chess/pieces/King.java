package com.chess.pieces;

public class King extends Piece{

    public King(String color) {
        super(color);
    }

    @Override
    public int getValue() {
        return 1000;
    }
}
