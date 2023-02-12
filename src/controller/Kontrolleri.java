package controller;

import javafx.application.Platform;
import simu.framework.IMoottori;
import simu.model.OmaMoottori;
import view.ISimulaattorinUI;

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
		ui.getVisualisointi().tyhjennaNaytto();
		((Thread)moottori).start();

	}
	@Override
	public void hidasta() { // hidastetaan moottorisäiettä
		moottori.setViive((long)(moottori.getViive()*1.10));
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
	public void visualisoiAsiakas() {
		Platform.runLater(new Runnable(){
			public void run(){
				ui.getVisualisointi().uusiAsiakas();
			}
		});
	}
}
