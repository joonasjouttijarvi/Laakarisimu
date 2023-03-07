package com.laakarisimu.simu.framework;


import com.laakarisimu.controller.IKontrolleriMtoV;
import com.laakarisimu.simu.model.Asiakas;
import com.laakarisimu.simu.model.Kassa;
import com.laakarisimu.simu.model.Laakari;
import com.laakarisimu.simu.model.Sairaanhoitaja;

public abstract class Moottori extends Thread implements IMoottori {

    protected Tapahtumalista tapahtumalista;
    protected Sairaanhoitaja sairaanhoitaja;
    protected Laakari laakari;
    protected Kassa kassa;
    protected Asiakas asiakas;
    protected IKontrolleriMtoV kontrolleri;
    private double simulointiaika = 0;
    private long viive = 0;
    private final Kello kello;


    public Moottori(IKontrolleriMtoV kontrolleri) {

        this.kontrolleri = kontrolleri;

        kello = Kello.getInstance();

        tapahtumalista = new Tapahtumalista();

    }

    public void setSimulointiaika(double aika) {
        simulointiaika = aika;
    }


    @Override
    public long getViive() {
        return viive;
    }

    @Override
    public void setViive(long viive) {
        this.viive = viive;
    }

    @Override
    public double getProgress() {
        return kello.getAika() / simulointiaika;
    }

    @Override
    public void run() {
        alustukset();
        while (simuloidaan()) {
            viive();
            kello.setAika(nykyaika());
            suoritaBTapahtumat();
            yritaCTapahtumat();
        }
        tulokset();

    }

    private void suoritaBTapahtumat() {
        while (tapahtumalista.getSeuraavanAika() == kello.getAika()) {
            suoritaTapahtuma(tapahtumalista.poista());
        }
    }

    private void yritaCTapahtumat() {
        if (!sairaanhoitaja.onVarattu() && sairaanhoitaja.onJonossa()) {
            sairaanhoitaja.aloitaPalvelu();
        }
        if (!laakari.onVarattu() && laakari.onJonossa()) {
            laakari.aloitaPalvelu();

        }
        if (!kassa.onVarattu() && kassa.onJonossa()) {
            kassa.aloitaPalvelu();
        }

    }

    private double nykyaika() {
        return tapahtumalista.getSeuraavanAika();
    }

    private boolean simuloidaan() {
        return kello.getAika() < simulointiaika;
    }

    private void viive() { // UUSI
        Trace.out(Trace.Level.INFO, "Viive " + viive);
        try {
            sleep(viive);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected abstract void alustukset();

    protected abstract void suoritaTapahtuma(Tapahtuma t);

    protected abstract void tulokset();

}