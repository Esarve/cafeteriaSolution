package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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

    @FXML
    private JFXButton order;

    @FXML
    private Label total;

    String selectedItem1;
    String selectedItem2;
    String selectedItem3;
    String selectedItem4;
    String selectedItem5;
    String selectedItem6;
    int price1;
    int price2;
    int price3;
    int price4;
    int price5;
    int price6;
    double totalbiill;

    public void orderbuttonaction(ActionEvent event) {

        selectedItem1 = cb1.getValue().toString();
        selectedItem2 = cb2.getValue().toString();
        selectedItem3 = cb3.getValue().toString();
        selectedItem4 = cb4.getValue().toString();
        selectedItem5 = cb5.getValue().toString();
        selectedItem6 = cb6.getValue().toString();
        price1 = getPrice(selectedItem1);
        price2 = getPrice(selectedItem2);
        price3 = getPrice(selectedItem3);
        price4 = getPrice(selectedItem4);
        price5 = getPrice(selectedItem5);
        price6 = getPrice(selectedItem6);

        totalbiill = price1+price2+price3+price4+price5+price5;
        total.setText(Double.toString(totalbiill));

        System.out.println("The price is: "+price1);

    }

    public int getPrice(String item){
        String sql = "select item_price from item_details where item_code in (select item_code from item where item_name = ?)";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,item);
            resultSet=preparedStatement.executeQuery();
            String output = null;
            while (resultSet.next()){
                output=resultSet.getString(1);
            }
            return Integer.parseInt(output);
        }catch (Exception e) {
            System.err.println(e);
        }
        return 0;
    }

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
