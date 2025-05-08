package com.chess.pieces;

public class Bishop extends Piece{

    public Bishop(String color) {
        super(color);
    }

    @Override
    public int getValue() {
        return 3;
    }
}
