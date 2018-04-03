package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class LoginController {


    @FXML
    public AnchorPane loginPane;

    @FXML
    public void openMainPanel(ActionEvent event) throws Exception {
        new Main().loadScene(Main.mainPanel);

    }
}