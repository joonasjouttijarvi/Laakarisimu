package datasource;

import jakarta.persistence.*;

public class MariaDbConn {

   EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Potilastiedot");
   EntityManager entityManager = entityManagerFactory.createEntityManager();
   EntityTransaction entityTransaction = entityManager.getTransaction();{


   try {
    entityTransaction.begin();

    entityTransaction.commit();
   }finally{
    if (entityTransaction.isActive()) {
     entityTransaction.rollback();
    }
    entityManager.close();
    entityManagerFactory.close();
   }

}

public static EntityManager getEntityManager() {
   EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Potilastiedot");
   EntityManager entityManager = entityManagerFactory.createEntityManager();
   return entityManager;
}

}
