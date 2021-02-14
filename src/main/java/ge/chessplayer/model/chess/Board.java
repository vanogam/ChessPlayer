package ge.chessplayer.model.chess;

import ge.chessplayer.model.chess.pieces.*;
import ge.chessplayer.model.user.SystemUser;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Board implements Serializable {
    public static final List<Piece> STARTING_POSITION = Arrays.asList(
            new Rook(PieceColor.WHITE, 0, 0),
            new Knight(PieceColor.WHITE, 0, 1),
            new Bishop(PieceColor.WHITE, 0, 2),
            new King(PieceColor.WHITE, 0, 4),
            new Queen(PieceColor.WHITE, 0, 3),
            new Bishop(PieceColor.WHITE, 0, 5),
            new Knight(PieceColor.WHITE, 0, 6),
            new Rook(PieceColor.WHITE, 0, 7),

            new Pawn(PieceColor.WHITE, 1, 0),
            new Pawn(PieceColor.WHITE, 1, 1),
            new Pawn(PieceColor.WHITE, 1, 2),
            new Pawn(PieceColor.WHITE, 1, 3),
            new Pawn(PieceColor.WHITE, 1, 4),
            new Pawn(PieceColor.WHITE, 1, 5),
            new Pawn(PieceColor.WHITE, 1, 6),
            new Pawn(PieceColor.WHITE, 1, 7),


            new Rook(PieceColor.BLACK, 7, 0),
            new Knight(PieceColor.BLACK, 7, 1),
            new Bishop(PieceColor.BLACK, 7, 2),
            new King(PieceColor.BLACK, 7, 4),
            new Queen(PieceColor.BLACK, 7, 3),
            new Bishop(PieceColor.BLACK, 7, 5),
            new Knight(PieceColor.BLACK, 7, 6),
            new Rook(PieceColor.BLACK, 7, 7),

            new Pawn(PieceColor.BLACK, 6, 0),
            new Pawn(PieceColor.BLACK, 6, 1),
            new Pawn(PieceColor.BLACK, 6, 2),
            new Pawn(PieceColor.BLACK, 6, 3),
            new Pawn(PieceColor.BLACK, 6, 4),
            new Pawn(PieceColor.BLACK, 6, 5),
            new Pawn(PieceColor.BLACK, 6, 6),
            new Pawn(PieceColor.BLACK, 6, 7)
    );
    private List<Piece> piecesList;

    private Piece[][] board;

    int id;

    private static int maxId = 0;

    private SystemUser whitePlayer;

    private SystemUser blackPlayer;

    private int whiteTimeMillis;

    private int blackTimeMillis;

    public static Board instance;



    public Board(List<Piece> pieceList) {
        piecesList = pieceList;
        board = new Piece[8][8];
        id = ++ maxId;
        for(Piece p : pieceList) {
            if (p == null) {
                continue;
            }
            board[p.getPosition().getX()][p.getPosition().getY()] = p;
        }
    }

    public static Board getBoard(int boardId) {
        return instance;
    }

    public int getId() {
        return id;
    }

    public Piece getPiece(Point p) {
        return board[p.getX()][p.getY()];
    }

    public Piece getPiece(Integer index) {
        return piecesList.get(index);
    }

    public List<Piece> getPieces() {
        return piecesList;
    }

    public Piece getPiece(int x, int y) {
        return board[x][y];
    }

    public String toJson() {
        StringBuilder json = new StringBuilder();
        for (Piece piece : piecesList) {
            if (piece != null) {
                json.append(piece.toJson()).append(", ");
            } else {
                System.out.println("###");
                json.append("X").append(", ");
            }
        }
        return json.toString();
    }

    private Piece getKing(PieceColor color) {
        /*for(Piece p : board) {
            if(p.getColor() == color && p.getName().equals(PieceType.KING.getName())) {
                return p;
            }
        }*/

        return null;
    }

    public int makeMove(Piece piece, Point point) {
        int ret = -1;
        if (this.board[point.getX()][point.getY()] != null) {
            ret = this.piecesList.indexOf(this.board[point.getX()][point.getY()]);
            this.piecesList.set(ret, null);
        }
        this.board[point.getX()][point.getY()] = piece;
        this.board[piece.getPosition().getX()][piece.getPosition().getY()] = null;
        piece.makeMove(point);
        return ret;
    }

    public boolean isInCheck(PieceColor color) {
        if(color == null) {
            return false;
        }
        Piece king = getKing(color);
        return true;
    }

}
