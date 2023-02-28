package com.laakarisimu.simu.dao;

import com.laakarisimu.simu.model.Asiakas;
import datasource.MariaDbConn;
import entity.Potilaat;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PotilasDao {


    public void lisaaPotilas(Asiakas a) {
        try {
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
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void clearDatabase() {
        try {
            EntityManager em = MariaDbConn.getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.createQuery("DELETE FROM Potilaat").executeUpdate();
            tx.commit();
            em.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getKaikkiPotilaat() {
        try {
            EntityManager em = MariaDbConn.getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.createQuery("SELECT * FROM Potilaat p").getResultList();
            tx.commit();
            em.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getPotilasById(int id) {
        try {
            EntityManager em = MariaDbConn.getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.createQuery("SELECT * FROM Potilaat p WHERE p.id = :id").setParameter("id", id).getResultList();
            tx.commit();
            em.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

