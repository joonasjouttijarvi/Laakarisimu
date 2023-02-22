package com.laakarisimu.controller;

public interface IKontrolleriMtoV {
	
		// Rajapinta, joka tarjotaan moottorille:
		
		public void naytaLoppuaika(double aika);
		public void naytaPalvellutAsiakkaat(int palvellutAsiakkaat);
		public void naytaLaakarinPalvelemat(int laakarinPalvelematAs);
		public void naytaLaakarinPalkka(double palkka);
		public void naytaSairaanhoitajanPalkka(double palkka);
		public void naytaProgress(double progress);
		public void naytaHoidontarveLieva(double lieva);
		public void naytaHoidontarveKohtalainen(double kohtalainen);
		public void naytaHoidontarveVakava(double vakava);
		public double getAsiakkaanSaapumisTiheys();
		public double getSairaanhoitajanPalveluaika();
		public double getLaakarinPalveluaika();
		public double getKassanPalveluaika();
}
