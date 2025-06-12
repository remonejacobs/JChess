package com.chess.board;

import com.chess.pieces.*;
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
     *
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
     *
     * @param col - column
     * @param row - row
     */
    private void removePiece(int col, int row, Piece newPiece) {
        pieces.removeIf(piece -> piece.getPosition().getY() == col && piece.getPosition().getX() == row);
        pieces.add(newPiece);
    }

    /**
     * play the move
     * @param move - move to play
     */
    public void validMove(String move) throws Exception {
        boolean valid = false;
        if (move.contains("x")) {
            if (Character.isUpperCase(move.toCharArray()[0])) {
                move = move.replace("x", "");
            } else {
                move = String.valueOf(move.toCharArray()[2]) + move.toCharArray()[3];
                System.out.println(move);
            }

        }
        switch (move.toCharArray()[0]) {
            case 'K':
                for (Piece piece : pieces) {
                    if (piece instanceof King) {
                        if (replacePieces(piece, move)) {
                            valid = true;
                            break;
                        }

                    }
                }
                break;
            case 'N':
                for (Piece piece : pieces) {
                    if (piece instanceof Knight) {
                        if (replacePieces(piece, move)) {
                            valid = true;
                            break;
                        }
                    }
                }
                break;
            case 'Q':
                for (Piece piece : pieces) {
                    if (piece instanceof Queen) {
                        if (replacePieces(piece, move)) {
                            valid = true;
                            break;
                        }
                    }
                }
                break;
            case 'R':
                for (Piece piece : pieces) {
                    if (piece instanceof Rook) {
                        if (replacePieces(piece, move)) {
                            valid = true;
                            break;
                        }
                    }
                }
                break;
            case 'B':
                for (Piece piece : pieces) {
                    if (piece instanceof Bishop) {
                        if (replacePieces(piece, move)) {
                            valid = true;
                            break;
                        }
                    }
                }
                break;
            default:
                for (Piece piece : pieces) {
                    if (piece instanceof Pawn) {
                        if (replacePieces(piece, move)) {
                            valid = true;
                            break;
                        }
                    }
                }
        }
        if (valid) {
            board.botMove();
        } else {
            System.out.println("INVALID MOVE!");
        }
    }

    /**
     * move the piece based on input
     * @param piece - piece to move
     * @param move - input
     * @return - whether move was possible
     */
    private boolean replacePieces(Piece piece, String move) throws Exception {
        List<Position> allMoves = piece.moves(board.getBoard());
        int xVal;
        int yVal;
        if (piece instanceof Pawn) {
            xVal = (int) move.toCharArray()[0] - 97;
            yVal = 8 - Integer.parseInt(String.valueOf(move.toCharArray()[1]));
        } else {
            xVal = ((int) move.toCharArray()[1] - 97);
            yVal = 8 - Integer.parseInt(String.valueOf(move.toCharArray()[2]));
        }

        if (allMoves.stream().anyMatch(pos -> pos.getX() == xVal && pos.getY() == yVal)) {

            board.setBoard(piece.getPosition().getY(), piece.getPosition().getX(), null);
            if (board.incheck()) {
                board.setBoard(piece.getPosition().getY(), piece.getPosition().getX(), piece);
                throw new Exception("");
            }
            // removing a piece and adding new one
            removePiece(piece.getPosition().getY(), piece.getPosition().getX(), piece);
            board.setBoard(yVal, xVal, piece);
            return true;
        }
        return false;
    }
}
