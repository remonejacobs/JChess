package com.chess.board;

import com.chess.pieces.*;

public class Board {

    private final Object[][] board = new Object[8][8];

    public Board() {
        createBoard();
    }

    private void createBoard() {
        String color = "black";
        int[] royal = {0, 7};
        int[] pawns = {1, 6};

        for (int i: royal) {
            if (i == 7) {
                color = "white";
            }
            board[i][0] = new Rook(color, i, 0);
            board[i][1] = new Knight(color, i, 1);
            board[i][2] = new Bishop(color, i, 2);
            board[i][7] = new Rook(color, i, 7);
            board[i][6] = new Knight(color, i, 6);
            board[i][5] = new Bishop(color, i, 5);

            if (i == 0) {
                board[i][3] = new Queen(color, i, 3);
                board[i][4] = new King(color, i, 4);
            } else {
                board[i][4] = new Queen(color, i, 4);
                board[i][3] = new King(color, i, 3);
            }
        }

        for (int i : pawns) {
            if (i == 1) {
                color = "black";
            } else {
                color = "white";
            }
            for (int j = 0; j < 8; j++){
                board[i][j] = new Pawn(color, i, j);
            }
        }


    }

    public Object[][] getBoard() {
        return board;
    }

    public void setBoard(int col, int row, Piece piece) {
        board[col][row] = piece;
    }

    public String converter(Piece piece) {
        return switch (piece) {
            case Rook rook -> "r";
            case Bishop bishop -> "b";
            case King king -> "k";
            case Queen queen -> "q";
            case Knight knight -> "n";
            default -> "p";
        };
    }

    public String toString() {
        String val = "";

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                try {
                    if (((Piece) board[i][j]).getColor().equals("white")) {
                        val += converter((Piece) board[i][j]).toUpperCase() + " ";
                    } else {
                        val += converter((Piece) board[i][j]) + " ";
                    }

                } catch (Exception e) {
                    val += ". ";
                }
            }
            val += "\n";
        }
        return val;
    }
}
