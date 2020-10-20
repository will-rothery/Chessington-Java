package training.chessington.model.pieces;

import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        // declare and define
        List<Move> results = new ArrayList<>();
        Coordinates coords = new Coordinates(from.getRow(), from.getCol());

        Coordinates up = coords.plus(-1, 0);
        Coordinates down = coords.plus(1, 0);
        Coordinates twoUp = coords.plus(-2, 0);
        Coordinates twoDown = coords.plus(2, 0);

        Coordinates upLeft = coords.plus(-1, -1);
        Coordinates downLeft = coords.plus(1, -1);
        Coordinates upRight = coords.plus(-1, 1);
        Coordinates downRight = coords.plus(1, 1);

        Move moveUp = new Move(coords, up);
        Move moveDown = new Move(coords, down);
        Move moveTwoUp = new Move(coords, twoUp);
        Move moveTwoDown = new Move(coords, twoDown);

        Move diagonalUpLeft = new Move(coords, upLeft);
        Move diagonalDownLeft = new Move(coords, downLeft);
        Move diagonalUpRight = new Move(coords, upRight);
        Move diagonalDownRight = new Move(coords, downRight);

        Piece pieceDiagonalUpLeft = safeBoardGet(board, from.plus(-1, -1));
        Piece pieceDiagonalDownLeft = safeBoardGet(board, from.plus(1, -1));
        Piece pieceDiagonalUpRight = safeBoardGet(board, from.plus(-1, 1));
        Piece pieceDiagonalDownRight = safeBoardGet(board, from.plus(1, 1));

        // White rules
        if (getColour() == PlayerColour.WHITE) {
            if (up.getRow() >= 0 && board.get(up) == null) { // pawn can't leave the board and there's nothing in front of it
                try {
                    results.add(moveUp); // move one space
                } catch (ArrayIndexOutOfBoundsException e) {
                    return null;
                }
                if (from.getRow() == 6 && board.get(twoUp) == null) { // if the pawn is in the starting block and there's nothing in front of it
                    results.add(moveTwoUp); // move two spaces
                }
            }
            if (pieceDiagonalUpLeft != null && pieceDiagonalUpLeft.getColour() != PlayerColour.WHITE) { // if there's something in the left diagonal space and it's not white
                results.add(diagonalUpLeft); // move diagonally left
            }
            if (pieceDiagonalUpRight != null && pieceDiagonalUpRight.getColour() != PlayerColour.WHITE) { // if there's something in the left diagonal space and it's not white
                results.add(diagonalUpRight); // move diagonally right
            }
        }

        // Black rules
        if (getColour() == PlayerColour.BLACK) {
            if (down.getRow() <= 7 && board.get(down) == null) { // pawn can't leave the board and there's nothing in front of it
                try {
                    results.add(moveDown); // move one space
                } catch(ArrayIndexOutOfBoundsException e) {
                    return null;
                }
                if (from.getRow() == 1 && board.get(twoDown) == null) { // if the pawn is in the starting block and there's nothing in front of it
                    results.add(moveTwoDown); // move two spaces
                }
                if (pieceDiagonalDownLeft != null && pieceDiagonalDownLeft.getColour() != PlayerColour.BLACK) { // if there's something in the left diagonal space and it's not black
                    results.add(diagonalDownLeft); // move diagonally left
                }
                if (pieceDiagonalDownRight != null && pieceDiagonalDownRight.getColour() != PlayerColour.BLACK) { // if there's something in the left diagonal space and it's not black
                    results.add(diagonalDownRight); // move diagonally right
                }
            }
        }
        return results;
    }

    private Piece safeBoardGet(Board board, Coordinates coordinates) {
        try {
            return board.get(coordinates);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}