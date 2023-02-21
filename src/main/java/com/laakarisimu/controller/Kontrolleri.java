package com.laakarisimu.controller;


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
	public double setNopeus() {
		return ui.getNopeus();
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
	public void naytaProgress(double progress) {
		ui.setProgress(progress);
	}

}
