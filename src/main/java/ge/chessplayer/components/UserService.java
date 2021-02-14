package ge.chessplayer.components;

import ge.chessplayer.exception.ChessPlayerException;
import ge.chessplayer.model.user.SystemUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserService {

    private static final Map<String, SystemUser> USER_MAP = new HashMap<>();

    @Autowired
    HttpSession session;

    private final static String userAttrName = "user";

    public void register(SystemUser user) throws ChessPlayerException {
        if (getUserByUsername(user.getUsername()) != null) {
            throw new ChessPlayerException("userAlreadyExists");
        }
        user.setPassword(DigestUtils.sha1Hex(user.getPassword()));
        USER_MAP.put(user.getUsername(), user);
    }

    private SystemUser getUserByUsername(String username) {
        return USER_MAP.get(username);
    }

    public SystemUser getCurrentUser() {
        return (SystemUser) session.getAttribute(userAttrName);
    }

    public void login(String username, String password) throws ChessPlayerException {
        SystemUser user = getUserByUsername(username);
        if (user == null) {
            throw new ChessPlayerException("Invalid username/password combination");
        }
        else {
            if (!DigestUtils.sha1Hex(password).equals(user.getPassword())) {
                throw new ChessPlayerException("Invalid username/password combination");
            }
        }
        session.setAttribute(userAttrName, user);
    }
}
