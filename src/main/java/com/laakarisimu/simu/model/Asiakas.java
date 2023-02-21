package com.laakarisimu.simu.model;


import com.laakarisimu.eduni.distributions.Negexp;
import com.laakarisimu.simu.framework.Kello;
import com.laakarisimu.simu.framework.Trace;

public class Asiakas {
	private double saapumisaika;
	private double poistumisaika;
	private int id;
	private static int i = 1;
	private static long sum = 0;
	private double jonotusAika;
	private double vamma;
	private int tyytyvaisyys;
	private double palveluaika;


	public Asiakas() {
		id = i++;
		jonotusAika = 0;
		vamma = 0;
		palveluaika=0;
		tyytyvaisyys=0;
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
	public double getVamma() {
		return vamma;
	}

	//calculate total time spent in queue
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


	//
	public void setVamma() {
		Negexp negexp = new Negexp(0.2);
		vamma = negexp.sample();
		if (vamma < 0.2) {
			Trace.out(Trace.Level.INFO, "Asiakas " + id + " on LIEVÄ");
		} else if (vamma < 0.8) {
			Trace.out(Trace.Level.INFO, "Asiakas " + id + " on KOHTALAINEN");
		} else {
			Trace.out(Trace.Level.INFO, "Asiakkaan  " + id + " on VAKAVA");
		}
	}
	public void setTyytyvaisyys() {
		if (jonotusAika + palveluaika > 40) {
			tyytyvaisyys ++;
		} else {
			tyytyvaisyys --;
		}
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
		Trace.out(Trace.Level.INFO, "Asiakas " + id + " " + tyytyvaisyys);
		Trace.out(Trace.Level.INFO, "Asiakas " + id + " palveluaika: " + palveluaika);
	}
}

		

