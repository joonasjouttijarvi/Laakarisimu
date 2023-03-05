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
	BarChart<String, Number> palvellutAsiakkaatChart;
	@FXML
	BarChart<String, Number> hoidontarveChart;
	@FXML
	BarChart<String, Number> hoidonkestoChart;
	@FXML
	BarChart<String, Number> palkkaChart;
	
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

	@Override
	public void start(Stage primaryStage) {

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
		return Double.parseDouble(asiakkaanSaapumisTiheys.getText());
	}

	@FXML
	public double getSairaanhoitajanPalveluaika() {
		return Double.parseDouble(sairaanhoitajanPalveluaika.getText());
	}

	@FXML
	public double getLaakarinPalveluaika() {
		return Double.parseDouble(laakarinPalveluaika.getText());
	}

	@FXML
	public double getKassanPalveluaika() {
		return Double.parseDouble(kassanPalveluaika.getText());
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
}
