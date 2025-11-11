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
     * @param moveStr - move in algebraic notation (e.g., "e2e4" or "Nf3")
     */
    public JSONObject validMove(String moveStr) throws Exception {
        Move move = parseMove(moveStr);

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
    private Move parseMove(String moveStr) {
        moveStr = moveStr.replace("x", ""); // ignore capture notation

        for (Piece piece : pieces) {
            if (piece.matchesMoveNotation(moveStr, board)) {
                Position from = piece.getPosition();
                Position to = piece.notationToPosition(moveStr);
                return new Move(from, to, piece, board.getPieceAt(to));
            }
        }
        return null;
    }

    private JSONObject createResponse(String move, boolean checkmate, JSONObject botMoves) {
        return new JSONObject()
                .put("move", move)
                .put("checkmate", checkmate)
                .put("botMoves", botMoves);
    }
}
