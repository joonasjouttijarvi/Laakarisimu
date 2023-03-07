package com.laakarisimu.simu.dao;

import com.laakarisimu.simu.model.Asiakas;
import entity.Potilaat;
import javafx.collections.ObservableList;

import java.util.List;

public interface IDao {

    void lisaaPotilas(Asiakas a);

    void clearDatabase();

    List<Potilaat> getKaikkiPotilaat();

    ObservableList<Potilaat> getPotilasById(int id);
}
