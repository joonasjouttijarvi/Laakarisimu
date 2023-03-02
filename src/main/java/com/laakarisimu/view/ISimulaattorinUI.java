package com.laakarisimu.view;

public interface ISimulaattorinUI {
	
	// Kontrolleri tarvitsee syötteitä, jotka se välittää Moottorille
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

	
	//Kontrolleri antaa käyttöliittymälle tuloksia, joita Moottori tuottaa 
	public void setLoppuaika(double aika);
	public void setProgress(double progress);
	
}
