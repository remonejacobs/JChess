package com.chess.board;

import com.chess.pieces.*;

import java.util.ArrayList;

public class Board {

    private final Object[][] board = new Object[8][8];
    private ArrayList<Piece> white = new ArrayList<>();
    private ArrayList<Piece> black = new ArrayList<>();

    public Board() {
        createBoard();
        setHands();
    }

    private void createBoard() {
        String color = "black";
        int[] royal = {0, 7};
        int[] pawns = {1, 6};

        for (int i: royal) {
            if (i == 7) {
                color = "white";
            }
            board[i][0] = new Rook(color, 0, i);
            board[i][1] = new Knight(color, 1, i);
            board[i][2] = new Bishop(color, 2, i);
            board[i][7] = new Rook(color, 7, i);
            board[i][6] = new Knight(color, 6, i);
            board[i][5] = new Bishop(color, 5, i);

            if (i == 0) {
                board[i][3] = new Queen(color, 3, i);
                board[i][4] = new King(color, 4, i);
            } else {
                board[i][4] = new Queen(color, 4, i);
                board[i][3] = new King(color, 3, i);
            }
        }

        for (int i : pawns) {
            if (i == 1) {
                color = "black";
            } else {
                color = "white";
            }
            for (int j = 0; j < 8; j++){
                board[i][j] = new Pawn(color, j, i);
            }
        }


    }

    public Object[][] getBoard() {
        return board;
    }

    public void setBoard(int col, int row, Piece piece) {
        board[col][row] = piece;
    }

    public void setHands() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                try {
                    Piece piece = (Piece) board[i][j];
                    if (piece.getColor().equals("white")) {
                        white.add(piece);
                    } else {
                        black.add(piece);
                    }
                } catch (Exception ignored) {
                }

            }
        }
    }

    public ArrayList<Piece> getHand(String color) {
        if (color.equals("white")) {
            return white;
        }
        return black;
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
