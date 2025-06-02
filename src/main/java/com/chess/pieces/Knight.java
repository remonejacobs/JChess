package com.chess.pieces;

import java.util.List;

public class Knight extends Piece{

    public Knight(String color, int x, int y) {
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
