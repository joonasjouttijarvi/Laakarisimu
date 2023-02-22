package com.laakarisimu.controller;


import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import com.laakarisimu.simu.framework.IMoottori;
import com.laakarisimu.simu.model.OmaMoottori;
import com.laakarisimu.view.ISimulaattorinUI;
import javafx.application.Platform;

public class Kontrolleri implements IKontrolleriVtoM, IKontrolleriMtoV{   // UUSI
	
	private IMoottori moottori;

	private ISimulaattorinUI ui;
	
	public Kontrolleri(ISimulaattorinUI ui) {
		this.ui = ui;
	}

	
	// Moottorin ohjausta:
		
	@Override
	public void kaynnistaSimulointi() {
		moottori = new OmaMoottori(this);
		moottori.setSimulointiaika(ui.getAika());
		moottori.setViive(ui.getViive());
		((Thread)moottori).start();

	}
	@Override
	public void hidasta() { // hidastetaan moottorisäiettä
		moottori.setViive((long)(moottori.getViive()*1.10));
	}

	@Override
	public double getAsiakkaanSaapumisTiheys() {
		return ui.getAsiakkaanSaapumistiheys();
	}

	@Override
	public double getSairaanhoitajanPalveluaika(){
		return ui.getSairaanhoitajanPalveluaika();
	}

	@Override 
	public double getLaakarinPalveluaika(){
		return ui.getLaakarinPalveluaika();
	}

	@Override
	public double getKassanPalveluaika(){
		return ui.getKassanPalveluaika();
	}

	@Override
	public void nopeuta() { // nopeutetaan moottorisäiettä
		moottori.setViive((long)(moottori.getViive()*0.9));
	}

	@Override
	public void naytaLoppuaika(double aika) {
		Platform.runLater(()->ui.setLoppuaika(aika));
	}
	@Override
	public void naytaPalvellutAsiakkaat(int palvellutAsiakkaat) {
		Platform.runLater(()->ui.setPalvellutAsiakkaat(palvellutAsiakkaat)); 
	}
	@Override
	public void naytaLaakarinPalvelemat(int laakarinPalvelematAs){
		Platform.runLater(() -> ui.setLaakarinPalvelemat(laakarinPalvelematAs));
	}
	@Override
	public void naytaLaakarinPalkka(double palkka){
		Platform.runLater(() -> ui.setLaakarinPalkka(palkka));
	}
	@Override
	public void naytaSairaanhoitajanPalkka(double palkka){
		Platform.runLater(() -> ui.setSairaanhoitajanPalkka(palkka));
	}
	@Override
	public void naytaHoidontarveLieva(int lieva){
		Platform.runLater(() -> ui.setHoidontarveLieva(lieva));
	}
	@Override
	public void naytaHoidontarveKohtalainen(int kohtalainen){
		Platform.runLater(() -> ui.setHoidontarveKohtalainen(kohtalainen));
	}
	@Override
	public void naytaHoidontarveVakava(int vakava){
		Platform.runLater(() -> ui.setHoidontarveVakava(vakava));
	}


	@Override
	public void naytaProgress(double progress) {
		ui.setProgress(progress);
	}

}
