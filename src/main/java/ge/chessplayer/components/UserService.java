package ge.chessplayer.components;

import ge.chessplayer.exception.ChessPlayerException;
import ge.chessplayer.model.user.SystemUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class UserService {

    @Autowired
    PersistenceManager persistenceManager;

    @Autowired
    HttpSession session;


    private final static String userAttrName = "user";

    public void register(SystemUser user) throws ChessPlayerException {
        EntityManager em = persistenceManager.getEntityManager();
        if (getUserByUsername(user.getUsername()) != null) {
            throw new ChessPlayerException("userAlreadyExists");
        }
        user.setPassword(DigestUtils.sha1Hex(user.getPassword()));
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    private SystemUser getUserByUsername(String username) {
        List<SystemUser> users = persistenceManager.getEntityManager().createQuery("SELECT su FROM SystemUser su WHERE su.username = :username", SystemUser.class)
                .setParameter("username", username)
                .getResultList();

        if (users.size() == 0) {
            return null;
        }
        return users.get(0);
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
