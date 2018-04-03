package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
public class LoginController {


    @FXML
    public void openMainPanel(ActionEvent event) throws Exception {
        new Main().loadScene(Main.mainPanel);
    }
}