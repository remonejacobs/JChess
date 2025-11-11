package com.chess.pieces;

import com.chess.board.Board;
import com.chess.board.Move;

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
    public abstract List<Move> moves(Board board);

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

    /**
     * Generates all valid sliding moves (used by Rook, Bishop, Queen).
     * @param board The current board
     * @param directions An array of directions, e.g. { {1,1}, {-1,-1}, {1,-1}, {-1,1} }
     * @return A list of all possible moves in those directions
     */
    protected List<Move> generateSlidingMoves(Board board, int[][] directions) {
        List<Move> allMoves = new ArrayList<>();
        Piece[][] squares = board.getBoard();

        int startX = position.getX();
        int startY = position.getY();

        for (int[] dir : directions) {
            int dx = dir[0];
            int dy = dir[1];

            for (int step = 1; step < 8; step++) {
                int newX = startX + dx * step;
                int newY = startY + dy * step;

                if (!isInBounds(newX, newY)) break;

                Piece target = squares[newY][newX];

                if (target == null) {
                    // Empty square — add move
                    allMoves.add(new Move(position, new Position(newX, newY), this, null));
                } else {
                    // Occupied square — capture only if opposite color
                    if (!target.getColor().equals(this.color)) {
                        allMoves.add(new Move(position, target.getPosition(), this, target));
                    }
                    break; // can't move past another piece
                }
            }
        }

        return allMoves;
    }

    protected boolean isInBounds(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    /**
     * Generates all valid sliding moves (used by King, Knight).
     * @param board The current board
     * @param directions An array of directions, e.g. { {1,1}, {-1,-1}, {1,-1}, {-1,1} }
     * @return A list of all possible moves in those directions
     */
    protected List<Move> generateLeapingMoves(Board board, int[][] directions) {
        List<Move> allMoves = new ArrayList<>();
        Piece[][] squares = board.getBoard();

        int startX = getPosition().getX();
        int startY = getPosition().getY();

        for (int[] dir : directions) {
            int newX = startX + dir[0];
            int newY = startY + dir[1];

            if (!isInBounds(newX, newY)) continue;

            Piece target = squares[newY][newX];

            if (target == null) {
                allMoves.add(new Move(getPosition(), new Position(newX, newY), this, null));
            } else if (!target.getColor().equals(this.getColor())) {
                allMoves.add(new Move(getPosition(), target.getPosition(), this, target));
            }
        }

        return allMoves;
    }
}
