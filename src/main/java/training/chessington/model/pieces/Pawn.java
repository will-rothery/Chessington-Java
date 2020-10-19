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
        Coordinates coords = new Coordinates(from.getRow(), from.getCol());

        if (getColour() == PlayerColour.WHITE) {
            Coordinates newCoords = coords.plus(-1, 0);
            if (board.get(newCoords) == null) {
                Move move = new Move(coords, newCoords);
                newCoords = newCoords.plus(-1, 0);
                if (board.get(newCoords) == null && from.getRow() == 6) {
                    Move whiteStartMove = new Move(coords, newCoords);
                    results.add(whiteStartMove);
                }
                results.add(move);
            }
        }
        if (getColour() == PlayerColour.BLACK) {
            Coordinates newCoords = coords.plus(1, 0);
            if (board.get(newCoords) == null) {
                Move move = new Move(coords, newCoords);
                newCoords = newCoords.plus(1, 0);
                if (board.get(newCoords) == null && from.getRow() == 1) {
                    Move blackStartMove = new Move(coords, newCoords);
                    results.add(blackStartMove);
                }
                results.add(move);
            }
        }
        return results;
    }
}