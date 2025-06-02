package com.chess.pieces;

import java.util.List;

public class Rook extends Piece{

    public Rook(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public int getValue() {
        return 5;
    }

    @Override
    public List<Position> moves(Object[][] board) {
        return List.of();
    }
}
