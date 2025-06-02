package com.chess.pieces;

import java.util.List;

public class Bishop extends Piece{

    public Bishop(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public int getValue() {
        return 3;
    }

    @Override
    public List<Position> moves(Object[][] board) {
        return List.of();
    }
}
