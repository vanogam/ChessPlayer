package ge.chessplayer.controllers;

import ge.chessplayer.model.Board;
import ge.chessplayer.model.Piece;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class MainPage {

    @GetMapping(path = "/")
    public String load(ModelMap model) {
        Board.instance = new Board(Board.STARTING_POSITION);

        model.addAttribute("board", Board.instance);

        return "abc";
    }
}
