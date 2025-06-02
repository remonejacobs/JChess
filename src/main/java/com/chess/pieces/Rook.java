package com.chess.pieces;

import java.util.ArrayList;
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
        List<Position> allMoves = new ArrayList<>();

        if (getColor().equals("black")) {
            // handles all moves vertical
            for (int i = 1; i < 8 - getPosition().getY(); i++) {
                allMoves.add(new Position(getPosition().getX(), getPosition().getY() + 1));
                if (board[getPosition().getY() + i][getPosition().getX()] != null) {
                    break;
                }
            }

            // handles all moves horizontal
            for (int i = 1; i < 8 - getPosition().getX(); i++) {
                allMoves.add(new Position(getPosition().getX() + 1, getPosition().getY()));
                if (board[getPosition().getY()][getPosition().getX() + i] != null) {
                    break;
                }
            }

        }

        return allMoves;
    }
}
