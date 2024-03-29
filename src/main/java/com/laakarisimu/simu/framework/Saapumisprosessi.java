package com.laakarisimu.simu.framework;

import com.laakarisimu.eduni.distributions.ContinuousGenerator;
import com.laakarisimu.simu.model.TapahtumanTyyppi;

public class Saapumisprosessi {

    private final ContinuousGenerator generaattori;
    private final Tapahtumalista tapahtumalista;
    private final TapahtumanTyyppi tyyppi;

    public Saapumisprosessi(ContinuousGenerator g, Tapahtumalista tl, TapahtumanTyyppi tyyppi) {
        this.generaattori = g;
        this.tapahtumalista = tl;
        this.tyyppi = tyyppi;
    }

    public void generoiSeuraava() {
        Tapahtuma t = new Tapahtuma(tyyppi, Kello.getInstance().getAika() + generaattori.sample());
        tapahtumalista.lisaa(t);
    }
}
