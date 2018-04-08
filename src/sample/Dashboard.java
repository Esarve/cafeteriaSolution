package sample;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {

    Main accessMain = new Main();
    Connection connection = DatabaseManager.DBconnect();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    public final ObservableList optionsforitems = FXCollections.observableArrayList();
    public final ObservableList optionsforquantity = FXCollections.observableArrayList();

    @FXML
    private JFXComboBox<?> cb1;


    @FXML
    private JFXComboBox<?> cb2;


    @FXML
    private JFXComboBox<?> cb3;

    @FXML
    private JFXComboBox<?> cb4;


    @FXML
    private JFXComboBox<?> cb5;


    @FXML
    private JFXComboBox<?> cb6;



    public void fillcomboboxforItems(){
        try {
            preparedStatement = connection.prepareStatement("select item_name from item");
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                optionsforitems.add(resultSet.getString("item_name"));
            }
            preparedStatement.close();
            resultSet.close();
        }catch (Exception e){
            System.err.println(e);
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillcomboboxforItems();
        cb1.setItems(optionsforitems);
        cb2.setItems(optionsforitems);
        cb3.setItems(optionsforitems);
        cb4.setItems(optionsforitems);
        cb5.setItems(optionsforitems);
        cb6.setItems(optionsforitems);
    }


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
