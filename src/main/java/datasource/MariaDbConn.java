package datasource;

import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;

import jakarta.persistence.*;

public class MariaDbConn {

    public static EntityManager getEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Potilastiedot");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        return entityManager;
    }
}
