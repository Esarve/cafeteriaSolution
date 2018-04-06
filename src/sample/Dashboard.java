package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class Dashboard {

    Main accessMain = new Main();

    @FXML
    void openLoginPanel(MouseEvent event) throws IOException {
        accessMain.loadScene(Main.loginPanel);
    }

    @FXML
    void openItemPanel(MouseEvent event) throws IOException {
        accessMain.loadScene(Main.itemPanel);
    }

    @FXML
    void openSuppliers(MouseEvent event) throws IOException{
        accessMain.loadScene(Main.suppliersPanel);
    }

    @FXML
    void openExpired(MouseEvent event) throws IOException{
        accessMain.loadScene(Main.expiredPanel);
    }

    @FXML
    void openModify(MouseEvent event) throws IOException {
        accessMain.loadScene(Main.modifyPanel);
    }

    @FXML
    void openSales(MouseEvent event) throws IOException {
        accessMain.loadScene(Main.salesPanel);
    }
}
