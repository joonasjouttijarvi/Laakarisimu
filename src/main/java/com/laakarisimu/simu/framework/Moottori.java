package com.laakarisimu.simu.framework;


import com.laakarisimu.controller.IKontrolleriMtoV;
import com.laakarisimu.simu.model.Kassa;
import com.laakarisimu.simu.model.Laakari;
import com.laakarisimu.simu.model.Sairaanhoitaja;

public abstract class Moottori extends Thread implements IMoottori {
	
	private double simulointiaika = 0;
	private long viive=0;
	private Kello kello;
	
	protected Tapahtumalista tapahtumalista;
	protected Sairaanhoitaja sairaanhoitaja;
	protected Laakari laakari;
	protected Kassa kassa;
	protected IKontrolleriMtoV kontrolleri;
	

	public Moottori(IKontrolleriMtoV kontrolleri){

		this.kontrolleri=kontrolleri;

		kello = Kello.getInstance(); // Otetaan kello muuttujaan yksinkertaistamaan koodia
		
		tapahtumalista = new Tapahtumalista();
		
		// Palvelupisteet luodaan simu.model-pakkauksessa Moottorin aliluokassa 
		
		
	}

	public void setSimulointiaika(double aika) {
		simulointiaika = aika;
	}


	@Override // UUSI
	public long getViive() {
		return viive;
	}
	@Override // UUSI
	public void setViive(long viive) {
		this.viive = viive;
	}
	@Override
	public void run(){ // Entinen aja()
		alustukset(); // luodaan mm. ensimmäinen tapahtuma
		while (simuloidaan()){
			viive(); // UUSI
			kello.setAika(nykyaika());
			suoritaBTapahtumat();
			yritaCTapahtumat();
		}
		tulokset();

	}
	
	private void suoritaBTapahtumat(){
		while (tapahtumalista.getSeuraavanAika() == kello.getAika()){
			suoritaTapahtuma(tapahtumalista.poista());
		}
	}

	private void yritaCTapahtumat(){
			if (!sairaanhoitaja.onVarattu() && sairaanhoitaja.onJonossa()){
				sairaanhoitaja.aloitaPalvelu();
			}
			if (!laakari.onVarattu() && laakari.onJonossa()){
				laakari.aloitaPalvelu();

			}
			if (!kassa.onVarattu() && kassa.onJonossa()){
				kassa.aloitaPalvelu();
			}

	}
		
	private double nykyaika(){
		return tapahtumalista.getSeuraavanAika();
	}
	
	private boolean simuloidaan(){
		return kello.getAika() < simulointiaika;
	}
	private void viive() { // UUSI
		Trace.out(Trace.Level.INFO, "Viive " + viive);
		try {
			sleep(viive);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	protected abstract void alustukset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	
	protected abstract void suoritaTapahtuma(Tapahtuma t);  // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	
	protected abstract void tulokset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	
}