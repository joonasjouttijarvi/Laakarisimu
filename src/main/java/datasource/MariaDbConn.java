package datasource;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MariaDbConn {

    public static EntityManager getEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Potilastiedot");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }
}
