package com.chess;

import com.chess.board.Board;
import com.chess.pieces.Piece;
import com.chess.pieces.Position;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class RookMoves {

    @Test
    public void testRookMoves() {
        Board board = new Board();

        List<Position> allMoves = ((Piece) board.getBoard()[0][0]).moves(board.getBoard());

        assertEquals(0, allMoves.size());
    }
}
