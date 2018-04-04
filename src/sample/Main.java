package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    public static final String loginPanel = "login.fxml";
    public static final String mainPanel= "dashboard.fxml";
    public static final String itemPanel= "itemList.fxml";
    public static Stage parentWindow;

    @Override
    public void start(Stage primaryStage) throws Exception{
        parentWindow = primaryStage;
        loadScene(Main.loginPanel);
        primaryStage.setTitle("Cafeteria Solution");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void loadScene(String fxml)throws IOException {
        Parent loginWindow;
        loginWindow = FXMLLoader.load(getClass().getResource(fxml));
        Scene newScene = new Scene(loginWindow);
        parentWindow.setScene(newScene);
    }

}
