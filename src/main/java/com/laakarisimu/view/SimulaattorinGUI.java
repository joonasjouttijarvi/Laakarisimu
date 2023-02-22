package com.laakarisimu.view;


import com.laakarisimu.controller.IKontrolleriVtoM;
import com.laakarisimu.controller.Kontrolleri;
import com.laakarisimu.simu.framework.Trace;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.DecimalFormat;



public class SimulaattorinGUI extends Application implements ISimulaattorinUI {

	@FXML
    private TextField asiakkaanSaapumisTiheys;

	@FXML
	private TextField sairaanhoitajanPalveluaika;

	@FXML
	private TextField laakarinPalveluaika;

	@FXML
	private TextField kassanPalveluaika;

	@FXML
	private TextField aika;
	@FXML
	private TextField viive;

	@FXML 
	private Label tulos;
	@FXML
	private Label laakariPalvellut; // tämä nimi pitää vaihtaa kaikkiPalvellut tms.
	@FXML
	private Label laakarinPalvelemat;
	@FXML
	private Label laakarinPalkka;
	@FXML
	private Label sairaanhoitajanPalkka;
	@FXML
	private Label hoidontarveLieva;
	@FXML
	private Label hoidontarveKohtalainen;
	@FXML
	private Label hoidontarveVakava;

	@FXML
	private ProgressBar progressBar;
	
	private IKontrolleriVtoM kontrolleri;

	@Override
	public void start(Stage primaryStage) {
		// Käyttöliittymän rakentaminen
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../Program.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Lääkäriasema");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//Käyttöliittymän rajapintametodit (kutsutaan kontrollerista)

	@Override
	public double getAika() {
		return Double.parseDouble(aika.getText());
	}

	@Override
	public long getViive() {
		return Long.parseLong(viive.getText());
	}

	@Override
	public void setLoppuaika(double aika) {
		DecimalFormat df = new DecimalFormat("#.##");
		tulos.setText(df.format(aika));
	}
	@Override
	public void setPalvellutAsiakkaat(int palvellutAsiakkaat) {
		laakariPalvellut.setText(Integer.toString(palvellutAsiakkaat));
	}
	@Override
	public void setLaakarinPalvelemat(int laakarinPalvelematAs){
		laakarinPalvelemat.setText(Integer.toString(laakarinPalvelematAs));
	}
	@Override
	public void setLaakarinPalkka(double palkka){
		laakarinPalkka.setText(Double.toString(palkka));
	}
	@Override
	public void setSairaanhoitajanPalkka(double palkka){
		sairaanhoitajanPalkka.setText(Double.toString(palkka));
	}
	@Override
	public void setHoidontarveLieva(int lieva){
		hoidontarveLieva.setText(Integer.toString(lieva));
	}
	@Override
	public void setHoidontarveKohtalainen(int kohtalainen){
		hoidontarveKohtalainen.setText(Integer.toString(kohtalainen));
	}
	@Override
	public void setHoidontarveVakava(int vakava){
		hoidontarveVakava.setText(Integer.toString(vakava));
	}






	// JavaFX-sovelluksen (käyttöliittymän) käynnistäminen

	public static void main(String[] args) {
		launch(args);
	}

	@FXML
	private void initialize() {
		Trace.setTraceLevel(Trace.Level.INFO);
		kontrolleri = new Kontrolleri(this);
	}

	@FXML
	private void kaynnista() {
		kontrolleri.kaynnistaSimulointi();
	}

	@FXML
	private void hidasta() {
		kontrolleri.hidasta();
	}

	@FXML
	private void nopeuta() {
		kontrolleri.nopeuta();
	}
	@FXML
	public double getAsiakkaanSaapumistiheys(){
		return Double.parseDouble(asiakkaanSaapumisTiheys.getText());
	}

	@FXML
	public double getSairaanhoitajanPalveluaika(){
		return Double.parseDouble(sairaanhoitajanPalveluaika.getText());
	}

	@FXML
	public double getLaakarinPalveluaika(){
		return Double.parseDouble(laakarinPalveluaika.getText());
	}

	@FXML
	public double getKassanPalveluaika(){
		return Double.parseDouble(kassanPalveluaika.getText());
	}

	@Override
	public void setProgress(double progress) {
		progressBar.setProgress(progress);
		
	}
}
	
