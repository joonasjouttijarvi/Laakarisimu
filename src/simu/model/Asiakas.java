package simu.model;

import simu.framework.Kello;
import simu.framework.Trace;



public class Asiakas {
	private double saapumisaika;
	private double poistumisaika;
	private int id;
	private static int i = 1;
	private static long sum = 0;
	private boolean tyytyvainen;
	double jonotusAika;
    
	
	public Asiakas(){
	    id = i++;
		tyytyvainen = true;
		jonotusAika = 0;
	    
		saapumisaika = Kello.getInstance().getAika();
		Trace.out(Trace.Level.INFO, "Uusi asiakas nro " + id + " saapui klo "+saapumisaika);
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
	//calculate total time spent in queue
	public void setJonotusAika() {
		jonotusAika =+ Kello.getInstance().getAika() - saapumisaika;
	}
	
	public int getId() {
		return id;
	}
	
	public void raportti(){
		Trace.out(Trace.Level.INFO, "\nAsiakas "+id+ " valmis! ");
		Trace.out(Trace.Level.INFO, "Asiakas "+id+ " saapui: " +saapumisaika);
		Trace.out(Trace.Level.INFO,"Asiakas "+id+ " poistui: " +poistumisaika);
		Trace.out(Trace.Level.INFO,"Asiakas "+id+ " viipyi: " +(poistumisaika-saapumisaika));
		sum += (poistumisaika-saapumisaika);
		double keskiarvo = sum/id;
		Trace.out(Trace.Level.INFO,"Asiakkaiden läpimenoaikojen keskiarvo tähän asti "+ keskiarvo);
		Trace.out(Trace.Level.INFO,"Asiakas "+id+ " oli jonossa: " +jonotusAika);
		Trace.out(Trace.Level.INFO,"Asiakas "+id+" oli tyytyvainen:"+onTyytyvainen());
	}

	public boolean onTyytyvainen() {// jos asiakas on jonottanut yli 40 aikaa hän ei ole tyytyväinen
		if(jonotusAika > 40){
			tyytyvainen = false;
		}
		return tyytyvainen;	
	}
}
		

