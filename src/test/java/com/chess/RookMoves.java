package com.chess;

import com.chess.board.Board;
import com.chess.board.Player;
import com.chess.pieces.Piece;
import com.chess.pieces.Position;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class RookMoves {

    @Test
    public void testRookMoves() {
        Board board = new Board();

        List<Position> allMoves = (board.getBoard()[0][0]).moves(board);

        assertEquals(0, allMoves.size());
    }

    @Test
    public void testMoreRookMoves() throws Exception {
        Player player = new Player("white");

        player.validMove("a4");

        List<Position> moves = player.getBoard().getBoard()[7][0].moves(player.getBoard());

        assertEquals(2, moves.size());
    }
}
