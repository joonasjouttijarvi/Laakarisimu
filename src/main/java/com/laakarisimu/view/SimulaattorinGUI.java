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
	private TextField aika;
	@FXML
	private TextField viive;

	@FXML 
	private Label tulos;
	@FXML
	private Label laakariPalvellut;
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

	@Override
	public void setProgress(double progress) {
		progressBar.setProgress(progress);
		
	}
}
	
