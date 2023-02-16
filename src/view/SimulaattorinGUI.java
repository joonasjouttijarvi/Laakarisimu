package view;


import java.lang.System.Logger.Level;
import java.text.DecimalFormat;

import controller.Kontrolleri;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import simu.framework.Trace;
import javafx.scene.*;
import javafx.scene.control.*;

public class SimulaattorinGUI extends Application implements ISimulaattorinUI {

	// Käyttöliittymäkomponentit:
	@FXML
	private TextField aika;
	@FXML
	private TextField viive;
	@FXML TextField tulos;
	private Kontrolleri kontrolleri;

	@Override
	public void start(Stage primaryStage) {
		// Käyttöliittymän rakentaminen
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Program.fxml"));
			Scene scene = new Scene(root);
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
		DecimalFormat formatter = new DecimalFormat("#0.00");
		this.tulos.setText(formatter.format(aika));
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
}
	
