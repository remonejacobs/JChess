package com.chess.pieces;

import java.util.List;

public class Queen extends Piece{

    public Queen(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public int getValue() {
        return 9;
    }

    @Override
    public List<Position> moves(Object[][] board) {
        return List.of();
    }
}
