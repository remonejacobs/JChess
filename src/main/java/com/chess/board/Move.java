package com.chess.board;

import com.chess.pieces.Piece;
import com.chess.pieces.Position;

import java.util.Objects;

public class Move {
    private final Position from;
    private final Position to;
    private final Piece movedPiece;
    private final Piece capturedPiece; // nullable if no capture

    public Move(Position from, Position to, Piece movedPiece, Piece capturedPiece) {
        this.from = from;
        this.to = to;
        this.movedPiece = movedPiece;
        this.capturedPiece = capturedPiece;
    }

    // getters
    public Position getFrom() { return from; }
    public Position getTo() { return to; }
    public Piece getMovedPiece() { return movedPiece; }
    public Piece getCapturedPiece() { return capturedPiece; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return Objects.equals(from, move.from) && Objects.equals(to, move.to) && Objects.equals(movedPiece, move.movedPiece) && Objects.equals(capturedPiece, move.capturedPiece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, movedPiece, capturedPiece);
    }

    @Override
    public String toString() {
        return movedPiece + " from " + from + " to " + to +
                (capturedPiece != null ? " capturing " + capturedPiece : "");
    }
}
