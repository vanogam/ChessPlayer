package ge.chessplayer.model.pieces;

import ge.chessplayer.model.*;

public class Bishop extends Piece {

    public Bishop(PieceColor color, int x, int y) {
        super(PieceType.BISHOP, color, x, y);

    }



    @Override
    public boolean isMoveValid(Point endPoint, Board board) {

        if(endPoint == null || !endPoint.isValid()) {
            return false;
        }

        int deltaX = Math.abs(getPosition().getX() - endPoint.getX());
        int deltaY = Math.abs(getPosition().getY() - endPoint.getY());

        return deltaX == deltaY && !checkInterference(endPoint, board);
    }
}
