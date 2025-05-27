package com.chess.pieces;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{

    public Pawn(String color, int col, int row) {
        super(color, col, row);
    }

    @Override
    public List<Position> moves(Object[][] board) {
        List<Position> allMoves = new ArrayList<>();

        for (int i = 1; i < 3; i++) {
            if (board[getPosition().getCol() + i][getPosition().getRow()] == null) {
                allMoves.add(new Position(getPosition().getCol() + i, getPosition().getRow()));
            }
        }

        if (board[getPosition().getCol() + 1][getPosition().getRow() + 1] != null) {
            allMoves.add(new Position(getPosition().getCol() + 1, getPosition().getRow() + 1));
        } else if (board[getPosition().getCol() + 1][getPosition().getRow() - 1] != null) {
            allMoves.add(new Position(getPosition().getCol() + 1, getPosition().getRow() - 1));
        }
        return allMoves;
    }

}