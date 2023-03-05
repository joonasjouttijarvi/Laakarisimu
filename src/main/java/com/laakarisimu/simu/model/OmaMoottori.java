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
	private IDao dao = new PotilasDao();


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
				a.setHoidontarve();
				if(a.getHoidontarve() != Asiakas.hoidontarve.LIEVA) {
					laakari.lisaaJonoon(a);
				} else {
					sairaanhoitaja.lisaaJonoon(a);
				}
				saapumisprosessi.generoiSeuraava();
				break;
			case SAIRAANHOITAJAN_PALVELU:
				a = sairaanhoitaja.otaJonosta();
				kassa.lisaaJonoon(a);
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
				dao.lisaaPotilas(a);
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
		kontrolleri.naytaKaikkiPalvellut(sairaanhoitaja.getPalvellutAsiakkaat() + laakari.getPalvellutAsiakkaat());
		kontrolleri.naytaSairaanhoitajanPalvelemat(sairaanhoitaja.getPalvellutAsiakkaat());
		kontrolleri.naytaLaakarinPalvelemat(laakari.getPalvellutAsiakkaat());
		kontrolleri.naytaLaakarinPalkka(laakari.getLaakarinKustannukset());
		kontrolleri.naytaSairaanhoitajanPalkka(sairaanhoitaja.getSairaanhoitajanKustannukset());
		double lievat=(Asiakas.lieva)*100;
		double kohtalaiset=(Asiakas.kohtalainen)*100;
		double vakavat=(Asiakas.vakava)*100;
		kontrolleri.naytaHoidontarveLieva((lievat));
		kontrolleri.naytaHoidontarveKohtalainen((kohtalaiset));
		kontrolleri.naytaHoidontarveVakava((vakavat));
		kontrolleri.naytaPalvellutAsiakkaatChart("Sairaanhoitaja",sairaanhoitaja.getPalvellutAsiakkaat());
		kontrolleri.naytaPalvellutAsiakkaatChart("Lääkäri", laakari.getPalvellutAsiakkaat());
		kontrolleri.naytaPalvellutAsiakkaatChart("Yhteensä", sairaanhoitaja.getPalvellutAsiakkaat() + laakari.getPalvellutAsiakkaat());
		kontrolleri.naytaHoidontarveChart("Lievä", lievat);
		kontrolleri.naytaHoidontarveChart("Kohtalainen", kohtalaiset);
		kontrolleri.naytaHoidontarveChart("Vakava", vakavat);
		kontrolleri.naytaHoidontarveChart("Kokonaishoidontarve",lievat+kohtalaiset+vakavat);
		kontrolleri.naytaHoidonkestoChart("Lääkäri", (int) (laakari.getTyotunnit()/laakari.getPalvellutAsiakkaat()));
		kontrolleri.naytaHoidonkestoChart("Sairaanhoitaja", sairaanhoitaja.getTyotunnit()/sairaanhoitaja.getPalvellutAsiakkaat());
		kontrolleri.naytaPalkkaChart("lääkärin palkka", laakari.getLaakarinKustannukset());
		kontrolleri.naytaPalkkaChart("Sairaanhoitajan palkka", sairaanhoitaja.getSairaanhoitajanKustannukset());
		kontrolleri.naytaPalkkaChart("Kokonaiskustannukset", laakari.getLaakarinKustannukset()+sairaanhoitaja.getSairaanhoitajanKustannukset());
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
