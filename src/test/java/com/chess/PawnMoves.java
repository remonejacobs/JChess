package com.chess;

import com.chess.board.Board;
import com.chess.pieces.Piece;
import com.chess.pieces.Position;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PawnMoves {

    @Test
    public void testPawnMoves() {
        Board board = new Board();

        List<Position> moves = ((Piece) board.getBoard()[1][2]).moves(board.getBoard());

        assertTrue(moves.getFirst().equals(new Position(2, 2)));
        assertTrue(moves.getLast().equals(new Position(2, 3)));

    }
}
