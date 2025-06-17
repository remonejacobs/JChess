package com.chess.pieces;

import com.chess.board.Board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Piece {

    private final String color;     // piece color
    private Position position;         // piece position

    public Piece(String color, int x, int y) {
        this.color = color;
        this.position = new Position(x, y);
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
    public abstract List<Position> moves(Board board);

    /**
     * Returns a range from one number to the next.
     * @param a - start of range.
     * @param b - end of range.
     * @return ArrayList of all numbers in between.
     */
    protected static ArrayList<Integer> range(int a, int b) {
        ArrayList<Integer> rangeList = new ArrayList<>();
        boolean reverse = false;
        int x = a;
        int y = b;

        if (a > b) {
            x = b;
            y = a;
            reverse = true;
        }
        for (int i = x; i < y + 1; i++) {
            rangeList.add(i);
        }

        if (reverse) {
            Collections.reverse(rangeList);
        }

        return rangeList;
    }
}
