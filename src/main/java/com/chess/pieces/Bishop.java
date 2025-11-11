package com.chess.pieces;

import com.chess.board.Board;
import com.chess.board.Move;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public int getValue() {
        return 3;
    }

    @Override
    public List<Move> moves(Board tempBoard) {

        // Directions: diagonals (dx, dy)
        int[][] directions = {
                {1, 1},   // down-right
                {-1, -1}, // up-left
                {-1, 1},  // down-left
                {1, -1}   // up-right
        };
        return generateSlidingMoves(tempBoard, directions);
    }
}

