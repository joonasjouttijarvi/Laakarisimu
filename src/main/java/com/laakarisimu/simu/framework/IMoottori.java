package com.laakarisimu.simu.framework;

public interface IMoottori {

    // Kontrolleri käyttää tätä rajapintaa

    void setSimulointiaika(double aika);

    long getViive();

    void setViive(long aika);

    double getProgress();

    double getAsiakkaanSaapumisTiheys();

    double getSairaanhoitajanPalveluaika();

    double getLaakarinPalveluaika();

    double getKassanPalveluaika();
}
