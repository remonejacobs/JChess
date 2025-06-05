package com.chess.pieces;

import java.util.ArrayList;
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
        List<Position> allMoves = new ArrayList<>();

        // we run for loops to test everything diagonally
        for (int i = 1; i < 8; i++) {
            try {
                if (board[getPosition().getY() + i][getPosition().getX() + i] != null) {
                    Piece piece = (Piece) board[getPosition().getY() + i][getPosition().getX() + i];

                    if (!piece.getColor().equals(getColor())) {
                        allMoves.add(piece.getPosition());
                    }
                    break;
                } else {
                    allMoves.add(new Position(getPosition().getX() + i, getPosition().getY() + i));
                }
            } catch (Exception ignored) {
            }
        }

        for (int i = 1; i < 8; i++) {
            try {
                if (board[getPosition().getY() - i][getPosition().getX() - i] != null) {
                    Piece piece = (Piece) board[getPosition().getY() - i][getPosition().getX() - i];

                    if (!piece.getColor().equals(getColor())) {
                        allMoves.add(piece.getPosition());
                    }
                    break;
                } else {
                    allMoves.add(new Position(getPosition().getX() - i, getPosition().getY() - i));
                }
            } catch (Exception ignored) {
            }
        }

        for (int i = 1; i < 8; i++) {
            try {
                if (board[getPosition().getY() + i][getPosition().getX() - i] != null) {
                    Piece piece = (Piece) board[getPosition().getY() + i][getPosition().getX() - i];

                    if (!piece.getColor().equals(getColor())) {
                        allMoves.add(piece.getPosition());
                    }
                    break;
                } else {
                    allMoves.add(new Position(getPosition().getX() - i, getPosition().getY() + i));
                }
            } catch (Exception ignored) {
            }
        }

        for (int i = 1; i < 8; i++) {
            try {
                if (board[getPosition().getY() - i][getPosition().getX() + i] != null) {
                    Piece piece = (Piece) board[getPosition().getY() - i][getPosition().getX() + i];

                    if (!piece.getColor().equals(getColor())) {
                        allMoves.add(piece.getPosition());
                    }
                    break;
                } else {
                    allMoves.add(new Position(getPosition().getX() + i, getPosition().getY() - i));
                }
            } catch (Exception ignored) {
            }
        }
        return allMoves;
    }
}
