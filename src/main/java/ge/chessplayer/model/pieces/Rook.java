package ge.chessplayer.model.pieces;

import ge.chessplayer.model.*;

public class Rook extends Piece {

    private boolean moved;

    public Rook(PieceColor color, int x, int y) {
        super(PieceType.ROOK, color, x, y);

        moved = false;
    }


    public Rook(PieceColor color, int x, int y, boolean moved) {
        super(PieceType.KING, color, x, y);

        this.moved = moved;
    }

    public boolean hasMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
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
        System.out.println("!@!@");
        return Math.min(deltaX, deltaY) == 0 && !checkInterference(endPoint, board);
    }
}
