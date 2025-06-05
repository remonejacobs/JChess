package com.chess;

import com.chess.board.Board;
import com.chess.pieces.Piece;
import com.chess.pieces.Position;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BishopMoves {

    @Test
    public void testBishopMoves() {
        Board board = new Board();

        List<Position> moves = ((Piece) board.getBoard()[0][2]).moves(board.getBoard());

        assertEquals(0, moves.size());
    }
}
