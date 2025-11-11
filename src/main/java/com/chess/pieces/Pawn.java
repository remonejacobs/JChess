package com.chess.pieces;

import com.chess.board.Board;
import com.chess.board.Move;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private static final int WHITE_START_RANK = 6;
    private static final int BLACK_START_RANK = 1;

    public Pawn(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public List<Move> moves(Board tempBoard) {
        List<Move> allMoves = new ArrayList<>();
        Piece[][] board = tempBoard.getBoard();

        int x = getPosition().getX();
        int y = getPosition().getY();

        // Direction: white moves up (-1), black moves down (+1)
        int dir = getColor().equals("white") ? -1 : 1;

        // Starting rank check for double move
        int startRank = getColor().equals("white") ? WHITE_START_RANK : BLACK_START_RANK;

        // Single forward move
        if (isInBounds(x, y + dir) && board[y + dir][x] == null) {
            allMoves.add(new Move(getPosition(), new Position(x, y + dir), this, null));

            // Double move if still at starting position
            if (y == startRank && isInBounds(x, y + 2 * dir) && board[y + 2 * dir][x] == null) {
                allMoves.add(new Move(getPosition(), new Position(x, y + 2 * dir), this, null));
            }
        }

        // Captures (diagonals)
        addCaptureMove(board, allMoves, x + 1, y + dir);
        addCaptureMove(board, allMoves, x - 1, y + dir);

        return allMoves;
    }

    private void addCaptureMove(Piece[][] board, List<Move> allMoves, int targetX, int targetY) {
        if (isInBounds(targetX, targetY)) {
            Piece target = board[targetY][targetX];
            if (target != null && !target.getColor().equals(this.getColor())) {
                allMoves.add(new Move(getPosition(), new Position(targetX, targetY), this, target));
            }
        }
    }
}


//🧠 Why This Is Better
//Issue	Old	New
//Boundary checking	Implicit, hidden in try/catch	Explicit and safe
//Readability	Two big if-blocks for color	One unified logic
//Maintainability	Hard to extend for en passant	Easier to add
//Magic numbers	1 and 6	Named constants
//Code reuse	Duplicate capture logic	Single helper method