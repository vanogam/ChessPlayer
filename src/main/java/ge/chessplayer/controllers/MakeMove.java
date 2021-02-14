package ge.chessplayer.controllers;

import ge.chessplayer.model.chess.Board;
import ge.chessplayer.model.chess.Piece;
import ge.chessplayer.model.chess.Point;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MakeMove {

    @PostMapping(path = "/make-move", produces = "application/x-www-form-urlencoded")
    @ResponseBody
    public String makeMove(@RequestParam("boardId")int boardId,
             @RequestParam("pieceId")String pieceId,
             @RequestParam("targetX")int targetX,
             @RequestParam("targetY")int targetY) {
        Board board = Board.getBoard(boardId);
        Integer index = Integer.parseInt(pieceId.substring(pieceId.indexOf("_") + 1));
        Piece piece = board.getPiece(index);
        System.out.println(boardId + "  " + pieceId + " " + index + "  " + targetX + " " + targetY);
        if (piece.isMoveValid(new Point(targetX, targetY), board)) {

            return "1&" + board.makeMove(piece, new Point(targetX, targetY));
        }
        return "0";
    }
}
