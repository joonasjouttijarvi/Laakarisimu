package com.laakarisimu.simu.model;


import com.laakarisimu.eduni.distributions.Negexp;
import com.laakarisimu.simu.dao.PotilasDao;
import com.laakarisimu.simu.framework.Kello;
import com.laakarisimu.simu.framework.Trace;

public class Asiakas {


	public enum hoidontarve {
		LIEVA, KOHTALAINEN, VAKAVA
	}
	private double saapumisaika;
	private double poistumisaika;
	private final int id;

	public static int i = 1;
	private static long sum = 0;
	private double jonotusAika;

	private double palveluaika;
	private static com.laakarisimu.simu.model.Asiakas.hoidontarve hoidontarve;
	public static int lieva=0;
	public static int kohtalainen=0;
	public static int vakava=0;


	public Asiakas() {
		id = i++;
		jonotusAika = 0;
		hoidontarve = null;
		palveluaika=0;
		saapumisaika = Kello.getInstance().getAika();
		Trace.out(Trace.Level.INFO, "Uusi asiakas nro " + id + " saapui klo " + saapumisaika);
	}

	public double getPoistumisaika() {
		return poistumisaika;
	}

	public void setPoistumisaika(double poistumisaika) {
		this.poistumisaika = poistumisaika;
	}

	public double getSaapumisaika() {
		return saapumisaika;
	}

	public void setSaapumisaika(double saapumisaika) {
		this.saapumisaika = saapumisaika;
	}


	public double getJonotusAika() {
		return jonotusAika;
	}

	public double getPalveluaika() {
		return palveluaika;
	}

	public void setJonotusAika() {
		jonotusAika = +Kello.getInstance().getAika() - saapumisaika;
	}
	// calculate individual asiakas service time - time in queue
	public void setPalveluaika(double palveluaika) {
		this.palveluaika=+ palveluaika;
	}
	
	public int getId() {
		return id;
	}

	public void setHoidontarve() {
		Negexp negexp = new Negexp(0.3);
		double h = negexp.sample();
		if (h <= 0.5) {
			hoidontarve = Asiakas.hoidontarve.LIEVA;
			lieva++;
		} else if (h < 0.8) {
			hoidontarve = Asiakas.hoidontarve.KOHTALAINEN;
			kohtalainen++;
		} else {
			hoidontarve = Asiakas.hoidontarve.VAKAVA;
			vakava++;
		}
		
	}
	//getter for hoidontarve
	public hoidontarve getHoidontarve() {
		return hoidontarve;
	}
	
		
	

	public void raportti() {
		Trace.out(Trace.Level.INFO, "\nAsiakas " + id + " valmis! ");
		Trace.out(Trace.Level.INFO, "Asiakas " + id + " saapui: " + saapumisaika);
		Trace.out(Trace.Level.INFO, "Asiakas " + id + " poistui: " + poistumisaika);
		Trace.out(Trace.Level.INFO, "Asiakas " + id + " viipyi: " + (poistumisaika - saapumisaika));
		sum += (poistumisaika - saapumisaika);
		double keskiarvo = sum / id;
		Trace.out(Trace.Level.INFO, "Asiakkaiden läpimenoaikojen keskiarvo tähän asti " + keskiarvo);
		Trace.out(Trace.Level.INFO, "Asiakas " + id + " oli jonossa: " + jonotusAika);
		Trace.out(Trace.Level.INFO, "Asiakas " + id + " palveluaika: " + palveluaika);
		Trace.out(Trace.Level.INFO, "Asiakas " + id + " hoidontarve: " + hoidontarve);
		Trace.out(Trace.Level.INFO, "hoidontarpeet: lieva" + lieva + " kohtalainen" + kohtalainen + " vakava" + vakava);


		
		
	}
}

		

