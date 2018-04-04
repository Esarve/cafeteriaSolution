package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class Dashboard {

    @FXML
    void openLoginPanel(MouseEvent event) throws IOException {
        new Main().loadScene(Main.loginPanel);
    }

    @FXML
    void openItemPanel(MouseEvent event) throws IOException {
        new Main().loadScene(Main.itemPanel);
    }
}
