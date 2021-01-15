package ge.chessplayer.model.pieces;

import ge.chessplayer.model.*;

public class King extends Piece {

    private boolean moved;

    public King(PieceColor color, int x, int y) {
        super(PieceType.KING, color, x, y);
        if(x == 0 && y == 4 && color == PieceColor.WHITE) moved = false;
        else if(x == 7 && y == 3 && color == PieceColor.BLACK) moved = false;
        else moved = true;
    }


    public King(PieceColor color, int x, int y, boolean moved) {
        super(PieceType.KING, color, x, y);

        this.moved = moved;
    }

    public boolean hasMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public boolean doCastle(Point point) {
        if(point == null || !point.isValid()) return false;
        int deltaX = Math.abs(getPosition().getX() - point.getX());
        int deltaY = Math.abs(getPosition().getY() - point.getY());
        if(moved || deltaY != 0 || deltaX != 2) {
            return false;
        }
        super.makeMove(point);
        moved = true;
        return true;
    }

    @Override
    public void makeMove(Point point) {
        super.makeMove(point);
        moved = true;
    }

    @Override
    public boolean isMoveValid(Point endPoint, Board board) {

        if(endPoint == null || !endPoint.isValid()) {
            return false;
        }

        int deltaX = Math.abs(getPosition().getX() - endPoint.getX());
        int deltaY = Math.abs(getPosition().getY() - endPoint.getY());

        return Math.max(deltaX, deltaY) == 1;
    }
}
