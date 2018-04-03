package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainPanel {

    @FXML
    void openLoginPanel(ActionEvent event) throws IOException {
        new Main().loadScene(Main.loginPanel);
    }
}
