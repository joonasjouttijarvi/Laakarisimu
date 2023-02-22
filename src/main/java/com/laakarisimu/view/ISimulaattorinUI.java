package com.laakarisimu.view;

public interface ISimulaattorinUI {
	
	// Kontrolleri tarvitsee syötteitä, jotka se välittää Moottorille
	public double getAika();
	public long getViive();
	double getAsiakkaanSaapumistiheys();
	double getSairaanhoitajanPalveluaika();
	double getLaakarinPalveluaika();
	double getKassanPalveluaika();

	public void setPalvellutAsiakkaat(int palvellutAsiakkaat);
	public void setLaakarinPalvelemat(int laakarinPalvelematAs);
	public void setLaakarinPalkka(double palkka);
	public void setSairaanhoitajanPalkka(double palkka);
	public void setHoidontarveLieva(int lieva);
	public void setHoidontarveKohtalainen(int kohtalainen);
	public void setHoidontarveVakava(int vakava);
	
	//Kontrolleri antaa käyttöliittymälle tuloksia, joita Moottori tuottaa 
	public void setLoppuaika(double aika);
	public void setProgress(double progress);
	
}
