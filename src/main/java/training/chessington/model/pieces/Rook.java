package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Rook extends AbstractPiece {
    public Rook(PlayerColour colour) {
        super(PieceType.ROOK, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {

        List<Coordinates> coordList = new ArrayList<>();
        return null;

    }
}







//    Pawn Stuff
//        declare and define
//        List<Move> results = new ArrayList<>();
//        Coordinates coords = new Coordinates(from.getRow(), from.getCol());
//        List<Coordinates> coordList = new ArrayList<>();
//
//        Coordinates up = coords.plus(-1, 0);
//        Coordinates down = coords.plus(1, 0);
//        Coordinates twoUp = coords.plus(-2, 0);
//        Coordinates twoDown = coords.plus(2, 0);
//
//        Move moveUp = new Move(coords, up);
//        Move moveDown = new Move(coords, down);
//        Move moveTwoUp = new Move(coords, twoUp);
//        Move moveTwoDown = new Move(coords, twoDown);
//        // White rules
//        if (getColour() == PlayerColour.WHITE) {
//            if (up.getRow() >= 0 && board.get(up) == null) { // Rook can't leave the board and there's nothing in front of it
//
//        }
//
//        // Black rules
//        if (getColour() == PlayerColour.BLACK) {
//            if (down.getRow() <= 7 && board.get(down) == null) { // Rook can't leave the board and there's nothing in front of it
//
//        }
//        return results;
//    }
//
//    private Piece safeBoardGet(Board board, Coordinates coordinates) {
//        try {
//            return board.get(coordinates);
//        } catch (ArrayIndexOutOfBoundsException e) {
//            return null;
//        }
//    }
//}