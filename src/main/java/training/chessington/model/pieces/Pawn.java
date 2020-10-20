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
        List<Move> results = new ArrayList<>();
        Piece piece = board.get(from);
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


        if (getColour() == PlayerColour.WHITE) {
            if (up.getRow() >= 0 && board.get(up) == null) {
                try {
                    results.add(moveUp);
                } catch (ArrayIndexOutOfBoundsException e) {
                    return null;
                }
                if (from.getRow() == 6 && board.get(twoUp) == null) {
                    results.add(moveTwoUp);
                }
            }
            if (pieceDiagonalUpLeft != null && pieceDiagonalUpLeft.getColour() != PlayerColour.WHITE) {
                results.add(diagonalUpLeft);
            }
            if (pieceDiagonalUpRight != null && pieceDiagonalUpRight.getColour() != PlayerColour.WHITE) {
                results.add(diagonalUpRight);
            }
        }


        if (getColour() == PlayerColour.BLACK) {
            if (down.getRow() <= 7 && board.get(down) == null) {
                try {
                    results.add(moveDown);
                } catch(ArrayIndexOutOfBoundsException e) {
                    return null;
                }
                if (from.getRow() == 1 && board.get(twoDown) == null) {
                    results.add(moveTwoDown);
                }
                if (pieceDiagonalDownLeft != null && pieceDiagonalDownLeft.getColour() != PlayerColour.BLACK) {
                    results.add(diagonalDownLeft);
                }
                if (pieceDiagonalDownRight != null && pieceDiagonalDownRight.getColour() != PlayerColour.BLACK) {
                    results.add(diagonalDownRight);
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