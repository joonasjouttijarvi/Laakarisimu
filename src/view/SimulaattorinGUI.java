package view;


import java.text.DecimalFormat;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;

public class SimulaattorinGUI extends Application implements ISimulaattorinUI{

	// Käyttöliittymäkomponentit:
	private TextField aika;
	private TextField viive;
	private Label tulos;

	@Override
	public void start(Stage primaryStage) {
		// Käyttöliittymän rakentaminen
		try {			
			Parent root = FXMLLoader.load(getClass().getResource("Program.fxml"));        
	        Scene scene = new Scene(root);
	        primaryStage.setScene(scene);
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	//Käyttöliittymän rajapintametodit (kutsutaan kontrollerista)

	@Override
	public double getAika(){
		return Double.parseDouble(aika.getText());
	}

	@Override
	public long getViive(){
		return Long.parseLong(viive.getText());
	}

	@Override
	public void setLoppuaika(double aika){
		 DecimalFormat formatter = new DecimalFormat("#0.00");
		 this.tulos.setText(formatter.format(aika));
	}

	// JavaFX-sovelluksen (käyttöliittymän) käynnistäminen

	public static void main(String[] args) {
		launch(args);
	}
}
