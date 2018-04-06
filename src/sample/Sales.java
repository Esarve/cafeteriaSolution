package sample;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class Sales {

    Main accessMain = new Main();

    @FXML
    void openDashboard(MouseEvent event) throws IOException {
        accessMain.loadScene(Main.mainPanel);
    }

    @FXML
    void openExpired(MouseEvent event)throws IOException {
        accessMain.loadScene(Main.expiredPanel);
    }

    @FXML
    void openItemList(MouseEvent event)throws IOException {
        accessMain.loadScene(Main.itemPanel);
    }

    @FXML
    void openLoginPanel(MouseEvent event)throws IOException {
        accessMain.loadScene(Main.loginPanel);
    }

    @FXML
    void openSuppliers(MouseEvent event) throws IOException{
        accessMain.loadScene(Main.suppliersPanel);
    }

    @FXML
    void openModify(MouseEvent event) throws IOException {
        accessMain.loadScene(Main.modifyPanel);
    }
}
