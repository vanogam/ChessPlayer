package ge.chessplayer.components;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Component
public class PersistenceManager {

    EntityManagerFactory emf;

    public PersistenceManager() {
        emf = Persistence.createEntityManagerFactory("chessPlayer");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
