package com.chess.board;

import com.chess.pieces.*;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final Object[][] board = new Object[8][8];
    private ArrayList<Piece> white = new ArrayList<>();
    private ArrayList<Piece> black = new ArrayList<>();
//    private Player player = new Player(board.getHand("white"));

    public Board() {
        createBoard();
        setHands();
    }

    /**
     * create the board
     */
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

    /**
     * getter for the board
     * @return - board
     */
    public Object[][] getBoard() {
        return board;
    }

    /**
     * setter for the board
     * @param y - column
     * @param x - row
     * @param piece - piece to place there
     */
    public void setBoard(int y, int x, Piece piece) {
        if (board[y][x] != null && piece != null) {
            if (((Piece) board[y][x]).getColor().equals("white")) {
                white.remove((Piece) board[y][x]);
            } else {
                black.remove((Piece) board[y][x]);
            }
        }
        if (piece != null) {
            piece.changePosition(y, x);
        }
        board[y][x] = piece;
    }

    /**
     * distribute the hands to keep track of overall piece value
     */
    private void setHands() {
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

    /**
     * returns all the pieces a player has
     * @param color - color of hand
     * @return - all pieces
     */
    public ArrayList<Piece> getHand(String color) {
        if (color.equals("white")) {
            return white;
        }
        return black;
    }

    /**
     * the bot makes a move
     */
    public void botMove() {
        for (Piece piece: black) {
            List<Position> movable = piece.moves(board);
            if (!movable.isEmpty()) {
                setBoard(piece.getPosition().getY(), piece.getPosition().getX(), null);
                setBoard(movable.getFirst().getY(), movable.getFirst().getX(), piece);
                break;
            }
        }
    }

    /**
     * checks if in check
     * @return - either in check or not
     */
    public boolean inCheck() {
        King king = new King("white", 0, 0);

        for (Piece piece: white) {
            if (piece instanceof King) {
                king = (King) piece;
            }
        }
        for (Piece piece: black) {
            List<Position> movable = piece.moves(board);

            for (Position position: movable) {
                if (position.equals(king.getPosition())) {
                    System.out.println("CHECK!");
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * convert piece into fen string
     * @param piece - piece to use
     * @return - fen string value
     */
    private String converter(Piece piece) {
        return switch (piece) {
            case Rook rook -> "r";
            case Bishop bishop -> "b";
            case King king -> "k";
            case Queen queen -> "q";
            case Knight knight -> "n";
            default -> "p";
        };
    }

    /**
     * display the board
     * @return - board in string value
     */
    public String toString() {
        StringBuilder val = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            val.append((8 - i)).append("| ");
            for (int j = 0; j < 8; j++) {
                try {
                    if (((Piece) board[i][j]).getColor().equals("white")) {
                        val.append(converter((Piece) board[i][j]).toUpperCase()).append(" ");
                    } else {
                        val.append(converter((Piece) board[i][j])).append(" ");
                    }

                } catch (Exception e) {
                    val.append(". ");
                }
            }
            val.append("\n");
        }
        val.append("   ---------------\n");
        val.append("   a b c d e f g h");
        return val.toString();
    }
}
