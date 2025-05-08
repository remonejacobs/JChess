package com.chess.board;

import com.chess.pieces.Piece;

import java.util.ArrayList;

public class Player {

    private final ArrayList<Piece> pieces;        // all the pieces the player has

    public Player(ArrayList<Piece> pieces) {
        this.pieces = pieces;
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
        pieces.removeIf(piece -> piece.getPosition().getCol() == col && piece.getPosition().getRow() == row);
    }
}
