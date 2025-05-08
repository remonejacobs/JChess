package com.chess.pieces;

public class Knight extends Piece{

    public Knight(String color) {
        super(color);
    }

    @Override
    public int getValue() {
        return 3;
    }
}
