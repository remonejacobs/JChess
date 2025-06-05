package com.chess.board;

import com.chess.pieces.Knight;
import com.chess.pieces.Piece;
import com.chess.pieces.Position;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final ArrayList<Piece> pieces;        // all the pieces the player has
    private Board board = new Board();

    public Player(String color) {
        this.pieces = board.getHand(color);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * find the value of the pieces on the board
     * @return - total value
     */
    public int boardValue() {
        int sumValue = 0;
        for (Piece piece : pieces) {
            sumValue += piece.getValue();
        }
        return sumValue;
    }

    /**
     * remove a piece from the player's pieces
     * @param col - column
     * @param row - row
     */
    private void removePiece(int col, int row) {
        pieces.removeIf(piece -> piece.getPosition().getY() == col && piece.getPosition().getX() == row);
    }

    private List<Position> validMove(String move) {
        List<Position> moves;
        switch (move.toCharArray()[0]) {
            case 'K':
                for (Piece piece: pieces) {
                    if (piece instanceof Knight) {
                        List<Position> allMoves = piece.moves(board.getBoard());
                        int xVal = (int) move.toCharArray()[1] - 97;
                        int yVal = Integer.parseInt(String.valueOf(move.toCharArray()[2]));
                        if (allMoves.stream().anyMatch(pos -> pos.getX() == xVal && pos.getY() == yVal)) {
                            board.setBoard(piece.getPosition().getY(), piece.getPosition().getX(), null);
                            board.setBoard(yVal, xVal, piece);
                        }
                    }
                }

        }
        List<Character> matches = List.of(new Character[]{'K', 'Q', 'R', 'B', 'N'});

        boolean bigPiece = matches.stream().anyMatch(x -> move.startsWith(String.valueOf(x)));

        return List.of();
    }

}
