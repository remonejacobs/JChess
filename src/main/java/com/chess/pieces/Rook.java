package com.chess.pieces;

import com.chess.board.Board;

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
    public List<Position> moves(Board tempBoard) {
        Object[][] board = tempBoard.getBoard();
        List<Position> allMoves = new ArrayList<>();

        // handles all moves vertical
        for (int a = 0; a < 2; a++) {
            for (int i = 1; i < 8; i++) {
                int add = i;
                if (a > 0) {
                    add *= -1;
                }

                try {
                    if (board[getPosition().getY() + add][getPosition().getX()] != null) {
                        Piece piece = (Piece) board[getPosition().getY() + add][getPosition().getX()];
                        if (!piece.getColor().equals(getColor())
                                && !tempBoard.checking(new Position(getPosition().getX(), getPosition().getY() + add), getColor(), this)) {
                            allMoves.add(piece.getPosition());
                        }
                        break;
                    } else {
                        if (!tempBoard.checking(new Position(getPosition().getX(), getPosition().getY() + add), getColor(), this)) {
                            allMoves.add(new Position(getPosition().getX(), getPosition().getY() + add));
                        }
                    }
                } catch (Exception ignored) {
                }
            }

            // handles all moves horizontal
            for (int i = 1; i < 8; i++) {
                int add = i;
                if (a > 0) {
                    add *= -1;
                }
                try {

                    if (board[getPosition().getY()][getPosition().getX() + add] != null) {
                        Piece piece = (Piece) board[getPosition().getY()][getPosition().getX() + add];
                        if (!piece.getColor().equals(getColor())
                                && !tempBoard.checking(new Position(getPosition().getX() + add, getPosition().getY()), getColor(), this)) {
                            allMoves.add(piece.getPosition());
                        }
                        break;
                    } else {
                        if (!tempBoard.checking(new Position(getPosition().getX() + add, getPosition().getY()), getColor(), this)) {
                            allMoves.add(new Position(getPosition().getX() + add, getPosition().getY()));
                        }
                    }
                } catch (Exception ignored) {
                }

            }

        }

        return allMoves;
    }
}
