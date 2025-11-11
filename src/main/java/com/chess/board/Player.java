package com.chess.board;

import com.chess.pieces.*;
import org.json.JSONObject;

import java.util.List;

public class Player {

    private final List<Piece> pieces;        // all the pieces the player has
    private Board board = new Board();
    private boolean checkmate = false;

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
     * find the total value of the player's pieces
     */
    public int boardValue() {
        return pieces.stream().mapToInt(Piece::getValue).sum();
    }

    /**
     * attempt to play a move
     * @param from - from in algebraic notation (e.g., "e2" or "f3")
     * @param to - to in algebraic notation (e.g., "e4" or "f3")
     */
    public JSONObject validMove(String from, String to) throws Exception {
        Move move = parseMove(from , to);
        System.out.println(move);

        if (move == null) {
            return createResponse("invalid", false, new JSONObject());
        }

        Piece movingPiece = move.getMovedPiece();

        // check if the move is valid
        if (!movingPiece.moves(board).contains(move)) {
            return createResponse("invalid", false, new JSONObject());
        }

        // check if the move puts the player in check
        if (!board.isMoveSafe(move)) {
            return createResponse("invalid", false, new JSONObject());
        }

        // execute the move
        board.makeMove(move);

        // check for checkmate
        checkmate = board.isCheckMate(movingPiece.getColor().equals("white") ? "black" : "white");

        // bot move if not checkmate
        JSONObject botMoves = new JSONObject();
        if (!checkmate) {
            botMoves = board.botMove();
        }

        return createResponse("valid", checkmate, botMoves);
    }

    /**
     * parses a move string into a Move object
     */
    private Move parseMove(String from, String to) {
        Position fromPos = Piece.notationToPosition(from);
        Position toPos = Piece.notationToPosition(to);
        Piece piece = board.getPieceAt(fromPos);

        if (piece == null) {
            return null; // No piece at the 'from' square
        }

        // Check if the piece can move to 'to' according to its rules
        System.out.println(piece.moves(board));
        System.out.println(toPos);
        boolean validMove = piece.moves(board).stream()
                .anyMatch(pos -> pos.getTo().equals(toPos));

        System.out.println(validMove);
        if (!validMove) {
            return null; // Illegal move
        }

        Piece captured = board.getPieceAt(toPos); // Piece being captured, if any
        return new Move(fromPos, toPos, piece, captured);
    }

    private JSONObject createResponse(String move, boolean checkmate, JSONObject botMoves) {
        return new JSONObject()
                .put("move", move)
                .put("checkmate", checkmate)
                .put("botMoves", botMoves);
    }
}
