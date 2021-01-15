package ge.chessplayer.model.pieces;

import ge.chessplayer.model.*;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {


    public Queen(PieceColor color, int x, int y) {
        super(PieceType.QUEEN, color, x, y);

    }


    @Override
    public boolean isMoveValid(Point endPoint, Board board) {
        if(endPoint == null || !endPoint.isValid()) {
            return false;
        }

        int deltaX = Math.abs(getPosition().getX() - endPoint.getX());
        int deltaY = Math.abs(getPosition().getY() - endPoint.getY());

        return (Math.min(deltaX, deltaY) == 0 || deltaX == deltaY) && !checkInterference(endPoint, board);
    }

}
