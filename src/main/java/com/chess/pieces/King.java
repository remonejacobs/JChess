package com.chess.pieces;

import com.chess.board.Board;
import com.chess.board.Move;
import java.util.List;

public class King extends Piece {

    public King(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public int getValue() {
        return 1000;
    }

    @Override
    public List<Move> moves(Board board) {
        // Directions: all 8 surrounding squares
        int[][] directions = {
                {1, 0},   // right
                {-1, 0},  // left
                {0, 1},   // down
                {0, -1},  // up
                {1, 1},   // down-right
                {1, -1},  // up-right
                {-1, 1},  // down-left
                {-1, -1}  // up-left
        };

        return generateLeapingMoves(board, directions);
    }
}
