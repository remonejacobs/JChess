package com.chess;

import com.chess.board.Board;
import com.chess.board.Player;
import com.chess.pieces.Piece;
import com.chess.pieces.Position;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BishopMoves {

    @Test
    public void testBishopMoves() {
        Board board = new Board();

        List<Position> moves = (board.getBoard()[0][2]).moves(board);

        assertEquals(0, moves.size());
    }

    @Test
    public void testMoreBishopMoves() throws Exception {
        Player player = new Player("white");

        player.validMove("e4");

        List<Position> moves = player.getBoard().getBoard()[7][5].moves(player.getBoard());

        assertEquals(5, moves.size());
    }
}
