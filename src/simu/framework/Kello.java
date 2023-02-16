package simu.framework;

public class Kello {

	private double aika;
	private static Kello instance;
	
	private Kello(){
		aika = 0;
	}
	
	public static Kello getInstance(){
		if (instance == null){
			instance = new Kello();
		}
		return instance;
	}
	
	public void setAika(double aika){
		this.aika = aika;
	}

	public double getAika(){
		return aika;
	}
}
