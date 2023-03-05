package com.laakarisimu.controller;

public interface IKontrolleriMtoV {

		
		void naytaLoppuaika(double aika);
		void naytaKaikkiPalvellut(int palvellutAsiakkaat);
		void naytaSairaanhoitajanPalvelemat(int sairaanhoitajanPalvelematAs);
		void naytaLaakarinPalvelemat(int laakarinPalvelematAs);
		void naytaLaakarinPalkka(double palkka);
		void naytaSairaanhoitajanPalkka(double palkka);
		void naytaProgress(double progress);
		void naytaHoidontarveLieva(double lieva);
		void naytaHoidontarveKohtalainen(double kohtalainen);
		void naytaHoidontarveVakava(double vakava);
		double getAsiakkaanSaapumisTiheys();
		double getSairaanhoitajanPalveluaika();
		double getLaakarinPalveluaika();
		double getKassanPalveluaika();
		void naytaPalvellutAsiakkaatChart(String nimi, int maara);
		void naytaHoidontarveChart(String nimi,double maara);
		void naytaHoidonkestoChart(String nimi, int kesto);
		void naytaPalkkaChart(String nimi, double palkka);
		void tietokanta();

}
