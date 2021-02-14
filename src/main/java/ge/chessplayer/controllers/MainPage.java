package ge.chessplayer.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ge.chessplayer.model.chess.Board;
import ge.chessplayer.model.chess.Piece;
import ge.chessplayer.model.chess.PieceColor;
import ge.chessplayer.model.chess.PieceType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainPage {

    @GetMapping(path = "/")
    public String load(ModelMap model) {
        Board.instance = new Board(Board.STARTING_POSITION);

        model.addAttribute("board", Board.instance);
        System.out.println(getBoard());
        return "abc";
    }

    @PostMapping(path = "/board")
    @ResponseBody
    public String getBoard() {

        return Board.instance.toJson();
    }
}
