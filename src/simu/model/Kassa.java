package simu.model;

import java.util.LinkedList;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;
import simu.framework.Trace;
import simu.model.Asiakas;


public class Kassa {

	private LinkedList<Asiakas> jono = new LinkedList<Asiakas>(); // Tietorakennetoteutus
	
	private ContinuousGenerator generator;
	private Tapahtumalista tapahtumalista;
	private TapahtumanTyyppi skeduloitavanTapahtumanTyyppi; 
    private double tulo;


	
	private boolean varattu = false;

	public Kassa(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi){
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;
				
	}
	public void lisaaJonoon(Asiakas a){   // Jonon 1. asiakas aina palvelussa
        jono.add(a);
	}

	public Asiakas otaJonosta(){  // Poistetaan palvelussa ollut
		varattu = false;
		return jono.poll();
	}

	public void aloitaPalvelu(){  //Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
		double palveluaika = generator.sample();
		jono.peek().setJonotusAika();
		if(jono.peek() != null){
			Trace.out(Trace.Level.INFO, "Kassalla ei ketään");
		}
		Trace.out(Trace.Level.INFO, "Asiakas" + jono.peek().getId()+" Maksaa ja lähtee");
		varattu = true;

		//TODO:
		//laakariaseman tulot(asiakkaiden maksamat maksut)
        tulo+= palveluaika*4;
		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi,Kello.getInstance().getAika()+palveluaika));
	}

	public boolean onVarattu(){
		return varattu;
	}

	public boolean onJonossa(){
		return jono.size() != 0;
	}

    public double getKassanTulo(){
        return tulo;
	
    }
}