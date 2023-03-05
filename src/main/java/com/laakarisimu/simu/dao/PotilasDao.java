package com.laakarisimu.simu.dao;

import com.laakarisimu.simu.model.Asiakas;
import datasource.MariaDbConn;
import entity.Potilaat;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public class PotilasDao implements IDao {

    EntityManager em = MariaDbConn.getEntityManager();
    EntityTransaction tx = em.getTransaction();
    Session session = em.unwrap(Session.class);

    @Override
    public void lisaaPotilas(Asiakas a) {
        try {
            tx.begin();
            Potilaat p = new Potilaat();
            p.setHoidontarve(a.getHoidontarve().toString());
            p.setId(a.getId());
            p.setJonotusaika(a.getJonotusAika());
            p.setPalveluaika(a.getPalveluaika());
            em.merge(p);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearDatabase() {
        try {
            tx.begin();
            em.createQuery("DELETE FROM Potilaat").executeUpdate();
            tx.commit();
            em.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Potilaat> getKaikkiPotilaat() {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Potilaat> cq = cb.createQuery(Potilaat.class);
        Root<Potilaat> rootEntry = cq.from(Potilaat.class);
        CriteriaQuery<Potilaat> all = cq.select(rootEntry);

        TypedQuery<Potilaat> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void getPotilasById(int id) {
        try {
            tx.begin();
            em.createQuery("SELECT * FROM Potilaat WHERE p.id = :id").setParameter("id", id).getResultList();
            tx.commit();
            em.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
