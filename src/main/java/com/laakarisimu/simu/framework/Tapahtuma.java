package com.laakarisimu.simu.framework;


import com.laakarisimu.simu.model.TapahtumanTyyppi;

public class Tapahtuma implements Comparable<Tapahtuma> {

    private TapahtumanTyyppi tyyppi;
    private double aika;

    public Tapahtuma(TapahtumanTyyppi tyyppi, double aika) {
        this.tyyppi = tyyppi;
        this.aika = aika;
    }

    public TapahtumanTyyppi getTyyppi() {
        return tyyppi;
    }

    public void setTyyppi(TapahtumanTyyppi tyyppi) {
        this.tyyppi = tyyppi;
    }

    public double getAika() {
        return aika;
    }

    public void setAika(double aika) {
        this.aika = aika;
    }

    @Override
    public int compareTo(Tapahtuma arg) {
        if (this.aika < arg.aika) return -1;
        else if (this.aika > arg.aika) return 1;
        return 0;
    }
}
