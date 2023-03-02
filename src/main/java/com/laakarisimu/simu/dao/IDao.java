package com.laakarisimu.simu.dao;

import com.laakarisimu.simu.model.Asiakas;
import entity.Potilaat;

import java.util.List;

public interface IDao {

    void lisaaPotilas(Asiakas a);
    void clearDatabase();
    List<Potilaat> getKaikkiPotilaat();
    void getPotilasById(int id);
}
