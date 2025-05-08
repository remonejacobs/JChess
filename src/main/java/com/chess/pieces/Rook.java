package com.chess.pieces;

public class Rook extends Piece{

    public Rook(String color) {
        super(color);
    }

    @Override
    public int getValue() {
        return 5;
    }
}
