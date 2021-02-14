package ge.chessplayer.components;

import ge.chessplayer.exception.ChessPlayerException;
import ge.chessplayer.model.user.SystemUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserService {

    @PersistenceContext
    EntityManager em;

    public void register(SystemUser user) throws ChessPlayerException {
        if (getUserByUsername(user.getUsername()) != null) {
            throw new ChessPlayerException("userAlreadyExists");
        }
        user.setPassword(DigestUtils.sha1Hex(user.getPassword()));
        em.persist(user);
    }

    private SystemUser getUserByUsername(String username) {
        List<SystemUser> users = em.createQuery("SELECT su FROM SystemUser su WHERE su.username = :username", SystemUser.class)
                .setParameter("username", username)
                .getResultList();

        if (users.size() == 0) {
            return null;
        }
        return users.get(0);
    }

    public static UserDetails currentUserDetails(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            return principal instanceof UserDetails ? (UserDetails) principal : null;
        }
        return null;
    }
}
