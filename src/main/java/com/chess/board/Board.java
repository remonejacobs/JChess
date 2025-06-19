package com.chess.board;

import com.chess.pieces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private final Piece[][] board = new Piece[8][8];

    public Board() {
        createBoard();
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

            board[i][3] = new Queen(color, 3, i);
            board[i][4] = new King(color, 4, i);
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
    public Piece[][] getBoard() {
        return board;
    }

    /**
     * setter for the board
     * @param y - column
     * @param x - row
     * @param piece - piece to place there
     */
    public void setBoard(int y, int x, Piece piece) {
        if (piece != null) {
            piece.changePosition(y, x);
        }
        board[y][x] = piece;
    }

    /**
     * returns all the pieces a player has
     * @param color - color of hand
     * @return - all pieces
     */
    public List<Piece> getHand(String color) {
        List<Piece> hand = new ArrayList<>();

        for (Piece[] row: board) {
            for (Piece piece: row) {
                if (piece != null && piece.getColor().equals(color)) {
                    hand.add(piece);
                }
            }
        }

        return hand;
    }

    /**
     * the bot makes a move
     */
    public void botMove() {

        for (Piece[] row: board) {
            for (Piece piece: row) {
                if (piece != null && piece.getColor().equals("black")) {
                    List<Position> movable = piece.moves(this);

                    for (Position pos: movable) {
                        if (!checking(pos, piece)) {
                            setBoard(piece.getPosition().getY(), piece.getPosition().getX(), null);
                            setBoard(pos.getY(), pos.getX(), piece);
                            return;
                        }
                    }
                }
            }
        }

    }

    /**
     * check if after the move is played you are in check
     * @param position - position to move to
     * @param piece - piece to move
     * @return - either in check or not
     */
    public boolean checking(Position position, Piece piece) {
        Piece pieceToPut = null;
        if (board[position.getY()][position.getX()] != null) {
            pieceToPut = board[position.getY()][position.getX()];
        }
        Position old = piece.getPosition();
        setBoard(position.getY(), position.getX(), piece);
        setBoard(old.getY(), old.getX(), null);

        boolean check = inCheck(piece.getColor());

        setBoard(old.getY(), old.getX(), piece);
        setBoard(position.getY(), position.getX(), pieceToPut);

        return check;
    }

    /**
     * checks if in check
     * @return - either in check or not
     */
    private boolean inCheck(String color) {

        King king = Arrays.stream(board).flatMap(Arrays::stream).filter(piece -> piece instanceof King && piece.getColor().equals(color))
                .findFirst().map(piece -> (King) piece).orElse(new King(color, 0, 0));

        for (Piece[] row: board) {
            for (Piece piece: row) {
                if (piece != null && !piece.getColor().equals(color)) {
                    List<Position> movable = piece.moves(this);

                    if (movable.stream().anyMatch(position -> position.equals(king.getPosition()))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * checks whether it is checkmate
     * @param color - color
     * @return - mate or not
     */
    public boolean checkMate(String color) {

        for (Piece[] row: board) {
            for (Piece piece: row) {
                if (piece != null && piece.getColor().equals(color)) {
                    List<Position> allMoves = piece.moves(this);

                    for (Position move: allMoves) {
                        if (!checking(move, piece)) {
                            return false;
                        }
                    }
                }
            }
        }

        return inCheck(color);
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
                    if ((board[i][j]).getColor().equals("white")) {
                        val.append(converter(board[i][j]).toUpperCase()).append(" ");
                    } else {
                        val.append(converter(board[i][j])).append(" ");
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
