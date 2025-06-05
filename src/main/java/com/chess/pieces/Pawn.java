package com.chess.pieces;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{

    public Pawn(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public List<Position> moves(Object[][] board) {
        List<Position> allMoves = new ArrayList<>();

        try {
            if (getColor().equals("black")) {
                for (int i = 1; i < 3; i++) {
                    if (board[getPosition().getY() + i][getPosition().getX()] == null) {
                        allMoves.add(new Position(getPosition().getX(), getPosition().getY() + i));
                    } else {
                        break;
                    }
                }

                if (board[getPosition().getY() + 1][getPosition().getX() + 1] != null) {
                    allMoves.add(new Position(getPosition().getX() + 1, getPosition().getY() + 1));
                } else if (board[getPosition().getY() + 1][getPosition().getX() - 1] != null) {
                    allMoves.add(new Position(getPosition().getX() - 1, getPosition().getY() + 1));
                }
            } else {
                for (int i = 1; i < 3; i++) {
                    if (board[getPosition().getY() - i][getPosition().getX()] == null) {
                        allMoves.add(new Position(getPosition().getX(), getPosition().getY() - i));
                    } else {
                        break;
                    }
                }

                if (board[getPosition().getY() - 1][getPosition().getX() + 1] != null) {
                    allMoves.add(new Position(getPosition().getX() + 1, getPosition().getY() - 1));
                } else if (board[getPosition().getY() - 1][getPosition().getX() - 1] != null) {
                    allMoves.add(new Position(getPosition().getX() - 1, getPosition().getY() - 1));
                }
            }
        } catch (Exception ignore) {
        }

        return allMoves;
    }

}