package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ProgramGUI {

    @FXML
    private TextField aikaTextField;

    @FXML
    private TextField viiveTextField;

    @FXML
    void hidasta(ActionEvent event) {
        System.out.println("Hidastetaan simulointia!!");
    }

    @FXML
    void kaynnista(ActionEvent event) {
        System.out.println("Käynnistetään ohjelma!!");
    }

    @FXML
    void nopeuta(ActionEvent event) {
        System.out.println("Nopeutetaan simulointia!!");
    }

}
