package com.chess.pieces;

import com.chess.board.Board;

import java.util.ArrayList;
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
    public List<Position> moves(Board tempBoard) {
        Piece[][] board = tempBoard.getBoard();
        List<Position> allMoves = new ArrayList<>();

         // will cover every move
        int[][] adds = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {-1, 2}, {1, 2}, {1, -2}, {-1, -2}};

        for (int[] row: adds) {
            try {
                if (board[getPosition().getY() + row[0]][getPosition().getX() + row[1]] != null) {
                    Piece piece = board[getPosition().getY() + row[0]][getPosition().getX() + row[1]];
                    if (!piece.getColor().equals(getColor())) {
                        allMoves.add(piece.getPosition());
                    }
                } else {
                    allMoves.add(new Position(getPosition().getX() + row[1], getPosition().getY() + row[0]));
                }
            } catch (Exception ignored) {
            }
        }

        return allMoves;
    }
}
