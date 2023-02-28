package com.laakarisimu.simu.framework;

public interface IMoottori {
		
	// Kontrolleri käyttää tätä rajapintaa
	
	public void setSimulointiaika(double aika);
	public void setViive(long aika);
	public long getViive();
	public double getProgress();
	public double getAsiakkaanSaapumisTiheys();
	public double getSairaanhoitajanPalveluaika();
	public double getLaakarinPalveluaika();
	public double getKassanPalveluaika();
}
