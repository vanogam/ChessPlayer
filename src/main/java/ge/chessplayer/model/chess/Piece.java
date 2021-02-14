package ge.chessplayer.model.chess;


import ge.chessplayer.model.chess.pieces.Knight;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public abstract class Piece implements Serializable {

    protected PieceType name;

    protected PieceColor color;

    protected Point position;


    public Piece(PieceType pieceType, PieceColor color, int x, int y) {
        this.name = pieceType;
        this.position = new Point(x, y);
        this.color = color;
    }

    public String getName() {
        return name.getName();
    }

    public void setName(PieceType name) {
        this.name = name;
    }

    public void makeMove(Point point) {
        this.position = point;
    }

    public Point getPosition() {
        return position;
    }


    public PieceColor getColor() {
        return color;
    }

    public void setColor(PieceColor color) {
        this.color = color;
    }

    public String toJson() {
        return "{\"name\": \"" + getName() + "\""
            +  ",\"color\": \"" + getColor().getName() + "\""
            +  ",\"x\": \"" + getPosition().getX() + "\""
            +  ",\"y\": \"" + getPosition().getY() + "\"}";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Point) {
            return obj.equals(this.position);
        }
        if(obj instanceof Piece) {
            return ((Piece) obj).getPosition().equals(this.position);
        }
        return false;
    }

    protected int getDirectionIncrement(int start, int end) {
        if(start == end) return 0;
        if(start > end) return -1;
        return 1;
    }

    protected boolean checkInterference(Point endPoint, Board board) {
        if (board.getPiece(endPoint) != null && board.getPiece(endPoint).getColor() == this.getColor()) {
            return true;
        }
        if (this instanceof Knight) {
            return false;
        }
        int dX = getDirectionIncrement(getPosition().getX(), endPoint.getX());
        int dY = getDirectionIncrement(getPosition().getY(), endPoint.getY());

        for(int x = getPosition().getX() + dX, y = getPosition().getY() + dY;
            x != endPoint.getX() || y != endPoint.getY();
            x += dX, y += dY) {

                if(board.getPiece(new Point(x, y)) != null) {
                    return true;
                }
        }

        return false;
    }


    public abstract boolean isMoveValid(Point endPoint, Board board);

    public List<Point> getValidMoves(Board board) {
        List<Point> points = new ArrayList<>();

        for(int i = 0; i < 8; i ++){
            for(int j = 0; j < 8; j ++){
                if (isMoveValid(new Point(i, j), board)) {
                    points.add(new Point(i, j));
                }
            }
        }
        return points;
    }



}
