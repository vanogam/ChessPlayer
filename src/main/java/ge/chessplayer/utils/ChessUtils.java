package ge.chessplayer.utils;

import ge.chessplayer.model.chess.Piece;
import ge.chessplayer.model.chess.Point;

import java.util.List;

public class ChessUtils {



    public static boolean checkMove(List<Piece> position, Point startingPosition, Point destination) {
        Piece movedPiece = null;
        if (position.contains(startingPosition)) {
            movedPiece = position.get(position.indexOf(startingPosition));
        }

        if (movedPiece == null) {
            return false;
        }

        Piece killedPiece = null;
        if (position.contains(destination)) {
            killedPiece = position.get(position.indexOf(destination));
        }

        if(killedPiece != null && killedPiece.getColor() == movedPiece.getColor()) {
            return false;
        }


        return true;
    }
}
