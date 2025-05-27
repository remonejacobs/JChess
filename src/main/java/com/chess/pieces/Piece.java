package com.chess.pieces;

import com.chess.board.Board;

import java.util.List;
import java.util.Objects;

public abstract class Piece {

    private final String color;     // piece color
    private Position position;         // piece position

    public Piece(String color, int col, int row) {
        this.color = color;
        this.position = new Position(row, col);
    }

    /**
     * get the value of the piece
     * @return - value of the piece
     */
    public int getValue() {
        return 1;
    }

    /**
     *
     * @return - value of the piece
     */
    public String getColor() {
        return color;
    }

    /**
     * change the position of the piece after a move
     * @param col - column
     * @param row - row
     */
    public void changePosition(int col, int row) {
        this.position = new Position(row, col);
    }

    /**
     *
     * @return - the position of the piece
     */
    public Position getPosition() {
        return position;
    }

    /**
     * find the playable moves of the pieces
     * @return - list of available moves
     */
    public abstract List<Position> moves(Object[][] board);
}
