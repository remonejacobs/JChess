package com.chess.pieces;

import com.chess.board.Board;
import com.chess.board.Move;
import java.util.List;

public class Queen extends Piece {

    public Queen(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public int getValue() {
        return 9;
    }

    @Override
    public List<Move> moves(Board board) {
        // Directions: diagonals + straight lines
        int[][] directions = {
                {1, 1},   // down-right
                {-1, -1}, // up-left
                {-1, 1},  // down-left
                {1, -1},  // up-right
                {1, 0},   // right
                {-1, 0},  // left
                {0, 1},   // down
                {0, -1}   // up
        };

        return generateSlidingMoves(board, directions);
    }
}
