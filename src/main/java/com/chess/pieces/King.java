package com.chess.pieces;

import java.util.List;

public class King extends Piece{

    public King(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public int getValue() {
        return 1000;
    }

    @Override
    public List<Position> moves(Object[][] board) {
        return List.of();
    }
}
