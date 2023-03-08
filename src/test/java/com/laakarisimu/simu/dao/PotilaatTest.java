package com.laakarisimu.simu.dao;

import entity.Potilaat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.laakarisimu.simu.model.Asiakas.hoidontarve;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PotilaatTest {


        private PotilasDao potilasDao;
        private Potilaat potilas1;
        private Potilaat potilas2;

        @BeforeEach
        public void setUp() {
            potilasDao = mock(PotilasDao.class);
            potilas1 = new Potilaat();
            potilas1.setId(1);
            potilas1.setHoidontarve(hoidontarve.VAKAVA.toString());
            potilas2 = new Potilaat();
            potilas2.setId(2);
            potilas2.setHoidontarve(hoidontarve.KOHTALAINEN.toString());
        }

        @Test
        public void testGetKaikkiPotilaat() {
            when(potilasDao.getKaikkiPotilaat())
                    .thenReturn(FXCollections.observableArrayList(Arrays.asList(potilas1, potilas2)));

            ObservableList<Potilaat> potilaat = potilasDao.getKaikkiPotilaat();

            assertEquals(2, potilaat.size());
            assertEquals(potilas1, potilaat.get(0));
            assertEquals(potilas2, potilaat.get(1));
            assertEquals(hoidontarve.VAKAVA.toString(), potilaat.get(0).getHoidontarve());
            assertEquals(hoidontarve.KOHTALAINEN.toString(), potilaat.get(1).getHoidontarve());

        }
    }
