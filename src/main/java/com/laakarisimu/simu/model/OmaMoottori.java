package com.laakarisimu.simu.model;
import java.text.DecimalFormat;

import com.laakarisimu.controller.IKontrolleriMtoV;
import com.laakarisimu.eduni.distributions.Negexp;
import com.laakarisimu.eduni.distributions.Normal;
import com.laakarisimu.simu.dao.IDao;
import com.laakarisimu.simu.dao.PotilasDao;
import com.laakarisimu.simu.framework.*;

public class OmaMoottori extends Moottori {
	
	private Saapumisprosessi saapumisprosessi;
	PotilasDao dao = new PotilasDao();


	public OmaMoottori(IKontrolleriMtoV kontrolleri){

		super(kontrolleri);

		sairaanhoitaja = new Sairaanhoitaja(new Normal(getSairaanhoitajanPalveluaika(),7), tapahtumalista, TapahtumanTyyppi.SAIRAANHOITAJAN_PALVELU);
		laakari= new Laakari(new Normal(getLaakarinPalveluaika(),6), tapahtumalista, TapahtumanTyyppi.LAAKARIN_PALVELU);
		kassa = new Kassa(new Normal(getKassanPalveluaika(),6), tapahtumalista, TapahtumanTyyppi.KASSAN_PALVELU);
		
		saapumisprosessi = new Saapumisprosessi(new Negexp(getAsiakkaanSaapumisTiheys(),2), tapahtumalista, TapahtumanTyyppi.SAAPUMINEN);

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
				if(a.getHoidontarve() != Asiakas.hoidontarve.LIEVA) {
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
		kontrolleri.naytaLaakarinPalvelemat(laakari.getPalvellutAsiakkaat());
		kontrolleri.naytaLaakarinPalkka(laakari.getLaakarinKustannukset());
		kontrolleri.naytaSairaanhoitajanPalkka(sairaanhoitaja.getSairaanhoitajanKustannukset());
		double lievat=(Asiakas.lieva)*100;
		double kohtalaiset=(Asiakas.kohtalainen)*100;
		double vakavat=(Asiakas.vakava)*100;
		kontrolleri.naytaHoidontarveLieva((lievat));
		kontrolleri.naytaHoidontarveKohtalainen((kohtalaiset));
		kontrolleri.naytaHoidontarveVakava((vakavat));
		}

	@Override
	public double getAsiakkaanSaapumisTiheys() {
		return kontrolleri.getAsiakkaanSaapumisTiheys();
	}

	@Override
	public double getSairaanhoitajanPalveluaika(){
		return kontrolleri.getSairaanhoitajanPalveluaika();
	}

	@Override
	public double getLaakarinPalveluaika(){
		return kontrolleri.getLaakarinPalveluaika();
	}

	@Override
	public double getKassanPalveluaika(){
		return kontrolleri.getKassanPalveluaika();
	}

}
