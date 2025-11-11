package com.chess.board;

import com.chess.pieces.*;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private final Piece[][] board = new Piece[8][8];
    private String turn = "white";

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
     * Switch turn between players
     */
    public void switchTurn() {
        turn = turn.equals("white") ? "black" : "white";
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
    public JSONObject botMove() {

        for (Piece[] row : board) {
            for (Piece piece : row) {
                if (piece != null && piece.getColor().equals("black")) {
                    List<Move> possibleMoves = piece.moves(this); // assuming moves() now returns Move objects

                    for (Move move : possibleMoves) {
                        if (!isMoveSafe(move)) { // renamed from checking()
                            JSONObject jsonMove = new JSONObject();
                            Position from = move.getFrom();
                            Position to = move.getTo();

                            jsonMove.put("fromBot", String.valueOf((char) (from.getX() + 97)) + (8 - from.getY()));
                            jsonMove.put("toBot", String.valueOf((char) (to.getX() + 97)) + (8 - to.getY()));
                            jsonMove.put("piece", converter(piece).toUpperCase());

                            // Execute the move on the board
                            setBoard(from.getY(), from.getX(), null);
                            setBoard(to.getY(), to.getX(), piece);
                            piece.changePosition(to.getX(), to.getY()); // update piece position

                            return jsonMove;
                        }
                    }
                }
            }
        }

        return null;
    }


    // -------------------------------
    // Check if moving a piece would put the king in check
    // -------------------------------
    public boolean isMoveSafe(Move move) {
        Piece movingPiece = move.getMovedPiece();
        Position from = movingPiece.getPosition();
        Position to = move.getTo();
        Piece capturedPiece = board[to.getY()][to.getX()];

        // Execute move temporarily
        board[to.getY()][to.getX()] = movingPiece;
        board[from.getY()][from.getX()] = null;
        movingPiece.changePosition(to.getX(), to.getY());

        boolean check = isInCheck(movingPiece.getColor());

        // Undo move
        board[from.getY()][from.getX()] = movingPiece;
        board[to.getY()][to.getX()] = capturedPiece;
        movingPiece.changePosition(from.getX(), from.getY());

        return !check;
    }

    // -------------------------------
    // Check if a color is in check
    // -------------------------------
    public boolean isInCheck(String color) {
        King king = Arrays.stream(board)
                .flatMap(Arrays::stream)
                .filter(piece -> piece instanceof King && piece.getColor().equals(color))
                .map(piece -> (King) piece)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(color + " king not found"));

        for (Piece[] row : board) {
            for (Piece piece : row) {
                if (piece != null && !piece.getColor().equals(color)) {
                    List<Move> moves = piece.moves(this); // use Move objects

                    for (Move move : moves) {
                        if (move.getTo().equals(king.getPosition())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // -------------------------------
    // Check for checkmate
    // -------------------------------
    public boolean isCheckMate(String color) {
        for (Piece[] row : board) {
            for (Piece piece : row) {
                if (piece != null && piece.getColor().equals(color)) {
                    List<Move> moves = piece.moves(this);

                    for (Move move : moves) {
                        if (isMoveSafe(move)) {
                            return false; // at least one move is safe
                        }
                    }
                }
            }
        }
        return isInCheck(color);
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
