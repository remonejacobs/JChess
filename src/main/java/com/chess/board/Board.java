package com.chess.board;

import com.chess.pieces.Piece;

public class Board {

    private final Object[][] board = new Object[8][8];

    public Board() {
    }

    private void createBoard() {

    }

    public Object[][] getBoard() {
        return board;
    }

    public void setBoard(int col, int row, Piece piece) {
        board[col][row] = piece;
    }
}
