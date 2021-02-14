package ge.chessplayer.controllers;

import ge.chessplayer.components.UserService;
import ge.chessplayer.exception.ChessPlayerException;
import ge.chessplayer.model.user.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestParam String username,
                        @RequestParam String password) {
        try {
            userService.login(username, password);
            return "0";
        } catch (ChessPlayerException e) {
            return e.getMessage();
        }
    }


    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String firstName,
                           @RequestParam String lastName) {
        try {
            System.out.println(username + " " + password + " " + firstName + " " + lastName);
            userService.register(new SystemUser(username, password, firstName, lastName));
            return "0";
        } catch (ChessPlayerException e) {
            return e.getMessage();
        }

    }
    @PostMapping("/getuserinfo")
    @ResponseBody
    public String getuserinfo() {
        SystemUser user = userService.getCurrentUser();
        if (user == null) return "";
        return user.getUsername() + "," + user.getRating().toString() + "," + user.getFirstName() + " " + user.getLastName();
    }
}
