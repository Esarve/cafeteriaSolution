package sample;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ItemList {

    @FXML
    void openDashboard(MouseEvent event) throws IOException {
        new Main().loadScene(Main.mainPanel);
    }
}
