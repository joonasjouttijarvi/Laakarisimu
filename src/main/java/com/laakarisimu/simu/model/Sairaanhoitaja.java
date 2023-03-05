package com.laakarisimu.simu.model;

import com.laakarisimu.eduni.distributions.ContinuousGenerator;
import com.laakarisimu.simu.dao.IDao;
import com.laakarisimu.simu.dao.PotilasDao;
import com.laakarisimu.simu.framework.Kello;
import com.laakarisimu.simu.framework.Tapahtuma;
import com.laakarisimu.simu.framework.Tapahtumalista;
import com.laakarisimu.simu.framework.Trace;

import java.util.LinkedList;

public class Sairaanhoitaja {

	private LinkedList<Asiakas> jono = new LinkedList<Asiakas>(); // Tietorakennetoteutus
	
	private ContinuousGenerator generator;
	private Tapahtumalista tapahtumalista;
	private TapahtumanTyyppi skeduloitavanTapahtumanTyyppi; 
	private double palkka;
	private int tyotunnit;
	private int palvellutAsiakkaat;

	private boolean varattu = false;

	

	public Sairaanhoitaja(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi){
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;
		this.palkka=24;				
	}


	public void lisaaJonoon(Asiakas a){
		jono.add(a);
		
	}

	public Asiakas otaJonosta(){
		varattu = false;
		return jono.poll();
	}

	public void aloitaPalvelu(){
		double palveluaika = generator.sample();
		jono.peek().setJonotusAika();
		jono.peek().setPalveluaika(palveluaika);
		Trace.out(Trace.Level.INFO, "Asiakas " + jono.peek().getId()+" on sairaanhoitajan luona");
		varattu = true;
		tyotunnit+=palveluaika;
		palvellutAsiakkaat++;
		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika()+palveluaika));
	}

	public boolean onVarattu(){
		return varattu;
	}
	public boolean onJonossa(){
		return jono.size() != 0;
	}
	public void setTuntiPalkka(int palkka){
        this.palkka = palkka;
	}
	public double getTuntiPalkka(){
		return palkka;
	}
	public double getSairaanhoitajanKustannukset(){
        return tyotunnit*palkka;
    }
	public int getPalvellutAsiakkaat(){
		return palvellutAsiakkaat;
	}
	public int getTyotunnit(){
		return tyotunnit;
	}

}
