package com.laakarisimu.simu.dao;

import com.laakarisimu.simu.model.Asiakas;

public interface IDao {

    void lisaaPotilas(Asiakas a);
    void clearDatabase();
    void getKaikkiPotilaat();
    void getPotilasById(int id);
}
