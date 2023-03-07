package com.laakarisimu.view;


public interface ISimulaattorinUI {

    double getAika();

    long getViive();

    double getAsiakkaanSaapumistiheys();

    double getSairaanhoitajanPalveluaika();

    double getLaakarinPalveluaika();

    double getKassanPalveluaika();

    void setKaikkiPalvellut(int palvellutAsiakkaat);

    void setSairaanhoitajanPalvelemat(int sairaanhoitajanPalvelematAs);

    void setLaakarinPalvelemat(int laakarinPalvelematAs);

    void setLaakarinPalkka(double palkka);

    void setSairaanhoitajanPalkka(double palkka);

    void setHoidontarveLieva(double lieva);

    void setHoidontarveKohtalainen(double kohtalainen);

    void setHoidontarveVakava(double vakava);

    void setPalvellutAsiakkaatChart(String nimi, int maara);

    void setHoidontarveChart(String nimi, double maara);

    void setHoidonkestoChart(String nimi, int kesto);

    void setPalkkaChart(String nimi, double palkka);

    void tietokanta();

    void setLoppuaika(double aika);

    void setProgress(double progress);

    void clearDatabase();

}
