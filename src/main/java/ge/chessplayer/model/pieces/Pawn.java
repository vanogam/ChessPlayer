package ge.chessplayer.model.pieces;

import ge.chessplayer.model.*;

public class Pawn extends Piece {

    public Pawn(PieceColor color, int x, int y) {
        super(PieceType.PAWN, color, x, y);

    }


    @Override
    public boolean isMoveValid(Point endPoint, Board board) {

        if(endPoint == null || !endPoint.isValid()) {
            return false;
        }

        int deltaX = getPosition().getX() - endPoint.getX();
        int deltaY = Math.abs(getPosition().getY() - endPoint.getY());
        System.out.println(deltaX + " " + deltaY);
        if (getColor() == PieceColor.BLACK){
            if (deltaX == 2 && getPosition().getX() == 6 && deltaY == 0){
                return !checkInterference(new Point(endPoint.getX() - 1, endPoint.getY()), board);
            }
            if (deltaX == 1 && deltaY == 0 && board.getPiece(endPoint) == null) {
                return true;
            }

            return deltaX == 1 && deltaY == 1 && board.getPiece(endPoint) != null && board.getPiece(endPoint).getColor() == PieceColor.WHITE;
        }
        else {
            if (deltaX == -2 && getPosition().getX() == 1 && deltaY == 0){
                return !checkInterference(new Point(endPoint.getX() + 1, endPoint.getY()), board);
            }
            if (deltaX == -1 && deltaY == 0 && board.getPiece(endPoint) == null) {
                return true;
            }
            return deltaX == -1 && deltaY == 1 && board.getPiece(endPoint) != null  && board.getPiece(endPoint).getColor() == PieceColor.BLACK;
        }




    }
}
