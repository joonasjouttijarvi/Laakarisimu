package com.laakarisimu.view;

import com.laakarisimu.controller.IKontrolleriVtoM;
import com.laakarisimu.controller.Kontrolleri;
import com.laakarisimu.simu.dao.PotilasDao;
import com.laakarisimu.simu.framework.Trace;
import entity.Potilaat;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private BarChart<String, Number> palvellutAsiakkaatChart;
	@FXML
	private BarChart<String, Number> hoidontarveChart;
	@FXML
	private BarChart<String, Number> hoidonkestoChart;
	@FXML
	private BarChart<String, Number> palkkaChart;

	@FXML
	private Label tulos;
	@FXML
	private Label kaikkiPalvellut;
	@FXML
	private Label sairaanhoitajanPalvelemat;
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
	private final Alert alert = new Alert(Alert.AlertType.ERROR);

	@Override
	public void start(Stage primaryStage) {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("../Program.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Lääkäriasema");
			primaryStage.setResizable(true);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public double getAika() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		if (aika.getText().isEmpty()) {
			alert.setTitle("Virhe");
			alert.setHeaderText("Aika ei voi olla tyhjä");
			alert.showAndWait();
		} else if (aika.getText().matches("[a-zA-Z]+")) {
			alert.setTitle("Virhe");
			alert.setHeaderText("Aika ei voi olla kirjaimia");
			alert.showAndWait();
		} else if (Integer.parseInt(aika.getText()) < 1 || Integer.parseInt(aika.getText()) > 1000000) {
			alert.setTitle("Virhe");
			alert.setHeaderText("Aika tulee olla välillä 1-100000");
			alert.showAndWait();
		} else {
			return Double.parseDouble(aika.getText());
		}
		return 0;
	}

	@Override
	public long getViive() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		if (viive.getText().isEmpty()) {
			alert.setTitle("Virhe");
			alert.setHeaderText("Viive ei voi olla tyhjä");
			alert.showAndWait();
		} else if (viive.getText().matches("[a-zA-Z]+")) {
			alert.setTitle("Virhe");
			alert.setHeaderText("Viive ei voi olla kirjaimia");
			alert.showAndWait();
		} else if (Integer.parseInt(viive.getText()) < 1 || Integer.parseInt(viive.getText()) > 1000) {
			alert.setTitle("Virhe");
			alert.setHeaderText("Viive tulee olla välillä 1-100");
			alert.showAndWait();
		} else {
			return Long.parseLong(viive.getText());
		}
		return 0;
	}

	@Override
	public void setLoppuaika(double aika) {
		DecimalFormat df = new DecimalFormat("#.##");
		tulos.setText(df.format(aika));
	}

	@Override
	public void setKaikkiPalvellut(int palvellutAsiakkaat) {
		kaikkiPalvellut.setText(Integer.toString(palvellutAsiakkaat));
	}

	@Override
	public void setSairaanhoitajanPalvelemat(int sairaanhoitajanPalvelematAs) {
		sairaanhoitajanPalvelemat.setText(Integer.toString(sairaanhoitajanPalvelematAs));
	}

	@Override
	public void setLaakarinPalvelemat(int laakarinPalvelematAs) {
		laakarinPalvelemat.setText(Integer.toString(laakarinPalvelematAs));
	}

	@Override
	public void setLaakarinPalkka(double palkka) {
		DecimalFormat df = new DecimalFormat("#.##");
		laakarinPalkka.setText(df.format(palkka));
	}

	@Override
	public void setSairaanhoitajanPalkka(double palkka) {
		sairaanhoitajanPalkka.setText(Double.toString(palkka));
	}

	@Override
	public void setHoidontarveLieva(double lieva) {
		hoidontarveLieva.setText(Double.toString(lieva));
	}

	@Override
	public void setHoidontarveKohtalainen(double kohtalainen) {
		hoidontarveKohtalainen.setText(Double.toString(kohtalainen));
	}

	@Override
	public void setHoidontarveVakava(double vakava) {
		hoidontarveVakava.setText(Double.toString(vakava));
	}


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
	public double getAsiakkaanSaapumistiheys() {
		if (asiakkaanSaapumisTiheys.getText().isEmpty()) {
			alert.setTitle("Virhe");
			alert.setHeaderText("Saapumistiheys ei voi olla tyhjä");
			alert.showAndWait();
		} else if (asiakkaanSaapumisTiheys.getText().matches("[a-zA-Z]+")) {
			alert.setTitle("Virhe");
			alert.setHeaderText("Saapumistiheys ei voi olla kirjaimia");
			alert.showAndWait();
		} else if (Double.parseDouble(viive.getText()) < 1 || Integer.parseInt(asiakkaanSaapumisTiheys.getText()) > 1000) {
			alert.setTitle("Virhe");
			alert.setHeaderText("Saapumistiheys tulee olla välillä 1-1000");
			alert.showAndWait();
		} else {
			return Long.parseLong(asiakkaanSaapumisTiheys.getText());
		}
		return 0;
	}

	@FXML
	public double getSairaanhoitajanPalveluaika() {
		if (sairaanhoitajanPalveluaika.getText().isEmpty()) {
			alert.setTitle("Virhe");
			alert.setHeaderText("Sairaanhoitajan palveluaika ei voi olla tyhjä");
			alert.showAndWait();
		} else if (sairaanhoitajanPalveluaika.getText().matches("[a-zA-Z]+")) {
			alert.setTitle("Virhe");
			alert.setHeaderText("Sairaanhoitajan palveluaika ei voi olla kirjaimia");
			alert.showAndWait();
		} else if (Integer.parseInt(sairaanhoitajanPalveluaika.getText()) < 1 || Integer.parseInt(laakarinPalveluaika.getText()) > 1000) {
			alert.setTitle("Virhe");
			alert.setHeaderText("Sairaanhoitajan palveluaika tulee olla välillä 1-1000");
			alert.showAndWait();
		} else {
			return Long.parseLong(viive.getText());
		}
		return 0;
	}

	@FXML
	public double getLaakarinPalveluaika() {
		if (laakarinPalveluaika.getText().isEmpty()) {
			alert.setTitle("Virhe");
			alert.setHeaderText("Aika ei voi olla tyhjä");
			alert.showAndWait();
		} else if (laakarinPalveluaika.getText().matches("[a-zA-Z]+")) {
			alert.setTitle("Virhe");
			alert.setHeaderText("Aika ei voi olla kirjaimia");
			alert.showAndWait();
		} else if (Integer.parseInt(laakarinPalveluaika.getText()) < 1 || Integer.parseInt(laakarinPalveluaika.getText()) > 1000) {
			alert.setTitle("Virhe");
			alert.setHeaderText("Aika tulee olla välillä 1-100000");
			alert.showAndWait();
		} else {
			return Long.parseLong(laakarinPalveluaika.getText());
		}
		return 0;
	}

	@FXML
	public double getKassanPalveluaika() {
		if (kassanPalveluaika.getText().isEmpty()) {
			alert.setTitle("Virhe");
			alert.setHeaderText("Kassan palveluaika ei voi olla tyhjä");
			alert.showAndWait();
		} else if (kassanPalveluaika.getText().matches("[a-zA-Z]+")) {
			alert.setTitle("Virhe");
			alert.setHeaderText("Kassan palveluaika ei voi olla kirjaimia");
			alert.showAndWait();
		} else if (Integer.parseInt(kassanPalveluaika.getText()) < 1 || Integer.parseInt(kassanPalveluaika.getText()) > 1000) {
			alert.setTitle("Virhe");
			alert.setHeaderText("Kassan palveluaika tulee olla välillä 1-1000");
			alert.showAndWait();
		} else {
			return Long.parseLong(kassanPalveluaika.getText());
		}
		return 0;
	}


	@Override
	public void setProgress(double progress) {
		progressBar.setProgress(progress);
	}

	
	@Override
	public void setPalvellutAsiakkaatChart(String nimi, int maara) {
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		series.setName(nimi);
		series.getData().add(new XYChart.Data<>("", maara));
		palvellutAsiakkaatChart.getData().add(series);
	}

	@Override
	public void setHoidontarveChart(String nimi, double maara) {
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		series.setName(nimi);
		series.getData().add(new XYChart.Data<>("", maara));
		hoidontarveChart.getData().add(series);
	}

	@Override
	public void setHoidonkestoChart(String nimi, int kesto) {
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		series.setName(nimi);
		series.getData().add(new XYChart.Data<>("", kesto));
		hoidonkestoChart.getData().add(series);
	}

	@Override
	public void setPalkkaChart(String nimi, double palkka) {
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		series.setName(nimi);
		series.getData().add(new XYChart.Data<>("", palkka));
		palkkaChart.getData().add(series);
	}
	@FXML
	private TableView<Potilaat> tietokantaView;
	@FXML
	private TableColumn<Potilaat, Integer> idColumn;
	@FXML
	private TableColumn<Potilaat, String> hoidontarveColumn;
	@FXML
	private TableColumn<Potilaat, Double> jonotusaikaColumn;
	@FXML
	private TableColumn<Potilaat, Double> palveluaikaColumn;

	PotilasDao potilasDao=new PotilasDao();

	@Override
	public void tietokanta() {
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		hoidontarveColumn.setCellValueFactory(new PropertyValueFactory<>("hoidontarve"));
		jonotusaikaColumn.setCellValueFactory(new PropertyValueFactory<>("jonotusaika"));
		palveluaikaColumn.setCellValueFactory(new PropertyValueFactory<>("palveluaika"));
		tietokantaView.setItems(potilasDao.getKaikkiPotilaat());

	}

	@Override
	public void clearDatabase(){
		potilasDao.clearDatabase();
		tietokanta();
	}
}
