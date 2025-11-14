package com.chess.pieces;

import com.chess.board.Board;
import com.chess.board.Move;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
                allMoves.add(new Move(position, new Position(newX, newY), this, null));
            } else if (!target.getColor().equals(this.getColor())) {
                allMoves.add(new Move(position, target.getPosition(), this, target));
            }
        }

        return allMoves;
    }

    /**
     * Checks if this piece matches the algebraic move notation
     * For example:
     *  - King: "Ke1"
     *  - Knight: "Nf3"
     *  - Pawn: "e4" (pawns usually omit the letter "P")
     *
     * @param notation the move string
     * @return true if this piece could be the piece referred to by notation
     */
    public boolean matchesMoveNotation(String notation, Board board) {
        // Remove capture marker
        notation = notation.replace("x", "");

        char pieceChar = getPieceChar(); // 'K', 'Q', 'R', 'B', 'N' for standard pieces, pawns return 0
        int startIdx = 0;

        // Determine if notation includes piece letter
        if (Character.isUpperCase(notation.charAt(0)) && notation.charAt(0) != 'O') { // 'O' is castling
            if (notation.charAt(0) != pieceChar) {
                return false; // Not this piece type
            }
            startIdx = 1; // skip piece letter
        } else if (pieceChar != 0) {
            return false; // notation is for pawn, but this is not a pawn
        }

        // Next chars are target square
        if (notation.length() - startIdx < 2) {
            return false; // invalid square
        }

        char file = notation.charAt(startIdx);
        char rank = notation.charAt(startIdx + 1);

        int x = file - 'a';
        int y = 8 - Character.getNumericValue(rank);

        // Check if this piece can move to that square
        return this.moves(board).stream()
                .anyMatch(pos -> pos.getTo().getX() == x && pos.getTo().getY() == y);
    }

    /**
     * Returns the standard piece letter for notation.
     * Returns 0 for pawns.
     */
    protected char getPieceChar() {
        if (this instanceof King) return 'K';
        if (this instanceof Queen) return 'Q';
        if (this instanceof Rook) return 'R';
        if (this instanceof Bishop) return 'B';
        if (this instanceof Knight) return 'N';
        return 0; // pawn
    }

    /**
     * Converts standard algebraic notation to a Position object.
     *
     * @param notation e.g., "e4", "a1", "h8"
     * @return Position object with x (file) and y (rank)
     * @throws IllegalArgumentException if notation is invalid
     */
    public static Position notationToPosition(String notation) {
        if (Character.isUpperCase(notation.charAt(0))) {
            notation = notation.substring(1);
        }
        if (notation == null || notation.length() != 2) {
            throw new IllegalArgumentException("Invalid chess notation: " + notation);
        }

        char fileChar = notation.charAt(0);
        char rankChar = notation.charAt(1);

        // Convert file 'a'-'h' to 0-7
        int x = fileChar - 'a';
        if (x < 0 || x > 7) {
            throw new IllegalArgumentException("Invalid file in notation: " + notation);
        }

        // Convert rank '1'-'8' to 7-0 (board array index)
        int y = 8 - Character.getNumericValue(rankChar);
        if (y < 0 || y > 7) {
            throw new IllegalArgumentException("Invalid rank in notation: " + notation);
        }

        return new Position(x, y);
    }
}
