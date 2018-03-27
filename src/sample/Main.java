package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent scene1 = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Cafeteria Solution");
        primaryStage.setScene(new Scene(scene1));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
