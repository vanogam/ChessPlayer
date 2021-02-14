package ge.chessplayer.model.chess.pieces;

import ge.chessplayer.model.chess.*;

public class Knight extends Piece {

    public Knight(PieceColor color, int x, int y) {
        super(PieceType.KNIGHT, color, x, y);

    }



    @Override
    public boolean isMoveValid(Point endPoint, Board board) {

        if(endPoint == null || !endPoint.isValid()) {
            return false;
        }

        int deltaX = Math.abs(getPosition().getX() - endPoint.getX());
        int deltaY = Math.abs(getPosition().getY() - endPoint.getY());

        return Math.min(deltaX, deltaY) == 1 && Math.max(deltaX, deltaY) == 2 && !checkInterference(endPoint, board);
    }
}
