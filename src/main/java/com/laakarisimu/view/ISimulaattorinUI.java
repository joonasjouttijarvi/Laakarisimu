package com.laakarisimu.view;

public interface ISimulaattorinUI {
	
	// Kontrolleri tarvitsee syötteitä, jotka se välittää Moottorille
	public double getAika();
	public long getViive();
	double getNopeus();
	public void setPalvellutAsiakkaat(int palvellutAsiakkaat);
	
	//Kontrolleri antaa käyttöliittymälle tuloksia, joita Moottori tuottaa 
	public void setLoppuaika(double aika);
	public void setProgress(double progress);
	
}
