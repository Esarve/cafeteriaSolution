package sample;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LoginController {

    @FXML
    JFXTextField username;
    @FXML
    JFXPasswordField passwd;
    @FXML
    public void openMainPanel(ActionEvent event) throws Exception {
        if (username.getText().equalsIgnoreCase("") && passwd.getText().equalsIgnoreCase("")) {
            System.out.println(username.getText()+" "+passwd.getText());
            new Main().loadScene(Main.mainPanel);
        }
    }

}