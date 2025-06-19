package com.chess;

import com.chess.board.Board;
import com.chess.board.Player;
import com.chess.pieces.Piece;
import com.chess.pieces.Position;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class KingMoves {

    @Test
    public void testKingMoves() {
        Board board = new Board();

        List<Position> moves = (board.getBoard()[0][4]).moves(board);

        assertEquals(0, moves.size());
    }

    @Test
    public void testMoreKingMoves() throws Exception {
        Player player = new Player("white");

        player.validMove("e4");
        player.validMove("d4");

        List<Position> moves = player.getBoard().getBoard()[7][4].moves(player.getBoard());

        assertEquals(2, moves.size());
    }
}
