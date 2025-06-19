package com.chess;

import com.chess.board.Board;
import com.chess.board.Player;
import com.chess.pieces.Piece;
import com.chess.pieces.Position;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class QueenMoves {

    @Test
    public void testQueenMoves() {
        Board board = new Board();

        List<Position> moves = (board.getBoard()[0][3]).moves(board);

        assertEquals(0, moves.size());
    }

    @Test
    public void testMoreQueenMoves() throws Exception {
        Player player = new Player("white");

        player.validMove("e4");

        List<Position> moves = player.getBoard().getBoard()[7][3].moves(player.getBoard());

        assertEquals(4, moves.size());
    }
}
