package com.laakarisimu.view;

import com.laakarisimu.simu.model.Asiakas;

import javafx.scene.chart.BarChart;

public interface ISimulaattorinUI {

	public double getAika();

	public long getViive();

	double getAsiakkaanSaapumistiheys();

	double getSairaanhoitajanPalveluaika();

	double getLaakarinPalveluaika();

	double getKassanPalveluaika();

	public void setKaikkiPalvellut(int palvellutAsiakkaat);

	public void setSairaanhoitajanPalvelemat(int sairaanhoitajanPalvelematAs);

	public void setLaakarinPalvelemat(int laakarinPalvelematAs);

	public void setLaakarinPalkka(double palkka);

	public void setSairaanhoitajanPalkka(double palkka);

	public void setHoidontarveLieva(double lieva);

	public void setHoidontarveKohtalainen(double kohtalainen);

	public void setHoidontarveVakava(double vakava);

	public void setPalvellutAsiakkaatChart(String nimi, int maara);

	public void setHoidontarveChart(String nimi, double maara);

	public void setHoidonkestoChart(String nimi, int kesto);

	public void setPalkkaChart(String nimi, double palkka);

	public void tietokanta();

	public void setLoppuaika(double aika);

	public void setProgress(double progress);
	public void clearDatabase();

}
