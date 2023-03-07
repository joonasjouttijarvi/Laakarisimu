package com.laakarisimu.simu.framework;

public class Kello {

    private static Kello instance;
    private double aika;

    private Kello() {
        aika = 0;
    }

    public static Kello getInstance() {
        if (instance == null) {
            instance = new Kello();
        }
        return instance;
    }

    public double getAika() {
        return aika;
    }

    public void setAika(double aika) {
        this.aika = aika;
    }
}
