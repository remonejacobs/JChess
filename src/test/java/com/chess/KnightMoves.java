package com.chess;

import com.chess.board.Board;
import com.chess.board.Player;
import com.chess.pieces.Piece;
import com.chess.pieces.Position;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class KnightMoves {

    @Test
    public void testKnightMoves() {
        Board board = new Board();

        List<Position> moves = (board.getBoard()[0][1]).moves(board);

        assertEquals(2, moves.size());
    }

    @Test
    public void testMoreKnightMoves() throws Exception {
        Player player = new Player("white");

        player.validMove("Nc3");

        List<Position> moves = player.getBoard().getBoard()[5][2].moves(player.getBoard());

        assertEquals(5, moves.size());
    }
}
