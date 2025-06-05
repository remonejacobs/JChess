package com.chess.pieces;

import java.util.ArrayList;
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
        List<Position> allMoves = new ArrayList<>();

        try {
            // checking each diagonal
            if (board[getPosition().getY() + 1][getPosition().getX() + 1] != null) {
                Piece piece = (Piece) board[getPosition().getY() + 1][getPosition().getX() + 1];

                if (!piece.getColor().equals(getColor())) {
                    allMoves.add(piece.getPosition());
                }
            } else {
                allMoves.add(new Position(getPosition().getX() + 1, getPosition().getY() + 1));
            }

            if (board[getPosition().getY() - 1][getPosition().getX() - 1] != null) {
                Piece piece = (Piece) board[getPosition().getY() - 1][getPosition().getX() - 1];

                if (!piece.getColor().equals(getColor())) {
                    allMoves.add(piece.getPosition());
                }
            } else {
                allMoves.add(new Position(getPosition().getX() - 1, getPosition().getY() - 1));
            }

            if (board[getPosition().getY() + 1][getPosition().getX() - 1] != null) {
                Piece piece = (Piece) board[getPosition().getY() + 1][getPosition().getX() - 1];

                if (!piece.getColor().equals(getColor())) {
                    allMoves.add(piece.getPosition());
                }
            } else {
                allMoves.add(new Position(getPosition().getX() - 1, getPosition().getY() + 1));
            }

            if (board[getPosition().getY() - 1][getPosition().getX() + 1] != null) {
                Piece piece = (Piece) board[getPosition().getY() - 1][getPosition().getX() + 1];

                if (!piece.getColor().equals(getColor())) {
                    allMoves.add(piece.getPosition());
                }
            } else {
                allMoves.add(new Position(getPosition().getX() + 1, getPosition().getY() - 1));
            }
        } catch (Exception ignored) {
        }
        return allMoves;
    }
}
