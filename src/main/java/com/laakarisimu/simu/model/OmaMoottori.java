package com.laakarisimu.simu.model;


import com.laakarisimu.controller.IKontrolleriMtoV;
import com.laakarisimu.eduni.distributions.Negexp;
import com.laakarisimu.eduni.distributions.Normal;

import com.laakarisimu.simu.framework.*;

public class OmaMoottori extends Moottori {
	
	private Saapumisprosessi saapumisprosessi;


	public OmaMoottori(IKontrolleriMtoV kontrolleri){

		super(kontrolleri);

		sairaanhoitaja = new Sairaanhoitaja(new Normal(6,7), tapahtumalista, TapahtumanTyyppi.SAIRAANHOITAJAN_PALVELU);
		laakari= new Laakari(new Normal(10,6), tapahtumalista, TapahtumanTyyppi.LAAKARIN_PALVELU);
		kassa = new Kassa(new Normal(10,6), tapahtumalista, TapahtumanTyyppi.KASSAN_PALVELU);
		
		saapumisprosessi = new Saapumisprosessi(new Negexp(getNopeus(),2), tapahtumalista, TapahtumanTyyppi.SAAPUMINEN);

	}

	@Override
	protected void alustukset() {
		saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
	}
	
	
	@Override
	protected void suoritaTapahtuma(Tapahtuma t){  // B-vaiheen tapahtumat

		Asiakas a;
		switch (t.getTyyppi()){
			
			case SAAPUMINEN:
				a = new Asiakas();
				sairaanhoitaja.lisaaJonoon(a);
				saapumisprosessi.generoiSeuraava();
				break;
			case SAIRAANHOITAJAN_PALVELU:
				a = sairaanhoitaja.otaJonosta();
				if(a.getHoidontarve() == Asiakas.hoidontarve.KOHTALAINEN 
				|| a.getHoidontarve() == Asiakas.hoidontarve.VAKAVA){
					laakari.lisaaJonoon(a);
				}
				else{
					kassa.lisaaJonoon(a);
				}
				break;
			case LAAKARIN_PALVELU:
				a = laakari.otaJonosta();
				kassa.lisaaJonoon(a);
				break;
			case KASSAN_PALVELU:
				a = kassa.otaJonosta();
				a.setPoistumisaika(Kello.getInstance().getAika());
				a.raportti();
				kontrolleri.naytaProgress(getProgress());
				break;
			default:
				break;
		}	
	}

	@Override
	protected void tulokset() {	
		System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());
		Trace.out(Trace.Level.INFO,"Lääkäri palveli " + laakari.getPalvellutAsiakkaat() + " potilasta.");
		Trace.out(Trace.Level.INFO,"Sairaanhoitaja palveli " + sairaanhoitaja.getPalvellutAsiakkaat() + " potilasta.");
		Trace.out(Trace.Level.INFO,"Lääkärin kustannukset " + laakari.getLaakarinKustannukset() + " euroa.");
		Trace.out(Trace.Level.INFO,"Sairaanhoitajan kustannukset " + sairaanhoitaja.getSairaanhoitajanKustannukset() + " euroa.");
		kontrolleri.naytaLoppuaika(Kello.getInstance().getAika());
		kontrolleri.naytaPalvellutAsiakkaat(sairaanhoitaja.getPalvellutAsiakkaat());
	}
	@Override
	public double getNopeus() {
		return kontrolleri.setNopeus();
	}
}
