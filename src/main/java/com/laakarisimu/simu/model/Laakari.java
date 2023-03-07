package com.laakarisimu.simu.model;

import com.laakarisimu.eduni.distributions.ContinuousGenerator;
import com.laakarisimu.simu.framework.Kello;
import com.laakarisimu.simu.framework.Tapahtuma;
import com.laakarisimu.simu.framework.Tapahtumalista;
import com.laakarisimu.simu.framework.Trace;

import java.util.LinkedList;


public class Laakari {

    private static int palvellutAsiakkaat;
    private final LinkedList<Asiakas> jono = new LinkedList<>();
    private final ContinuousGenerator generator;
    private final Tapahtumalista tapahtumalista;
    private final TapahtumanTyyppi skeduloitavanTapahtumanTyyppi;
    private int palkka;
    private double tyotunnit;
    private boolean varattu = false;


    public Laakari(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
        this.tapahtumalista = tapahtumalista;
        this.generator = generator;
        this.skeduloitavanTapahtumanTyyppi = tyyppi;
        this.palkka = 42;

    }

    public void lisaaJonoon(Asiakas a) {
        jono.add(a);

    }

    public Asiakas otaJonosta() {
        varattu = false;
        return jono.poll();
    }

    public void aloitaPalvelu() {
        double palveluaika = generator.sample();
        jono.peek().setJonotusAika();
        jono.peek().setPalveluaika(palveluaika);
        if (jono.peek() != null) {
            Trace.out(Trace.Level.INFO, "Lääkärin Jonossa ei ketään");
        }
        Trace.out(Trace.Level.INFO, "Asiakas " + jono.peek().getId() + " Lääkärin vastaanotolla");
        varattu = true;
        tyotunnit += palveluaika;
        palvellutAsiakkaat++;
        tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika() + palveluaika));

    }

    public boolean onVarattu() {
        return varattu;
    }

    public boolean onJonossa() {
        return jono.size() != 0;
    }

    public void setTuntiPalkka(int palkka) {
        this.palkka = palkka;
    }

    public double getLaakarinKustannukset() {
        return tyotunnit * palkka;
    }

    public double getTyotunnit() {
        return tyotunnit;
    }

    public int getPalvellutAsiakkaat() {
        return palvellutAsiakkaat;
    }

}
