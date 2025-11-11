package com.chess.pieces;

import com.chess.board.Board;
import com.chess.board.Move;
import java.util.List;

public class Rook extends Piece {

    public Rook(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public int getValue() {
        return 5;
    }

    @Override
    public List<Move> moves(Board board) {
        // Directions: straight lines (horizontal and vertical)
        int[][] directions = {
                {1, 0},   // right
                {-1, 0},  // left
                {0, 1},   // down
                {0, -1}   // up
        };

        return generateSlidingMoves(board, directions);
    }
}
