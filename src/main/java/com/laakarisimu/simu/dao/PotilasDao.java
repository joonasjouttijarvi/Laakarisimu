package com.laakarisimu.simu.dao;

import com.laakarisimu.simu.model.Asiakas;
import datasource.MariaDbConn;
import entity.Potilaat;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PotilasDao {


    public void lisaaPotilas(Asiakas a) {

        EntityManager em = MariaDbConn.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Potilaat p = new Potilaat();
        p.setHoidontarve(a.getHoidontarve().toString());
        p.setId(a.getId());
        p.setJonotusaika(a.getJonotusAika());
        p.setPalveluaika(a.getPalveluaika());
        em.merge(p);
        tx.commit();
        em.close();
    }
}
