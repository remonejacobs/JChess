package com.chess.pieces;

import com.chess.board.Board;
import com.chess.board.Move;
import java.util.List;

public class Knight extends Piece {

    public Knight(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public int getValue() {
        return 3;
    }

    @Override
    public List<Move> moves(Board board) {
        // All 8 L-shaped moves (relative coordinates)
        int[][] directions = {
                {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
                {1, 2}, {-1, 2}, {1, -2}, {-1, -2}
        };

        return generateLeapingMoves(board, directions);
    }
}
