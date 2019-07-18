package sample;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Modify implements Initializable {

    private Main accessMain = new Main();
    private Connection connection = DatabaseManager.DBconnect();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private final ObservableList optionsforSuppliers = FXCollections.observableArrayList();
    private final ObservableList optionsfortypes = FXCollections.observableArrayList();

    @FXML
    private JFXTextField code;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField qtty;

    @FXML
    private JFXTextField price;

    @FXML
    private JFXComboBox<?> suppliers;

    @FXML
    private JFXComboBox<?> type;

    @FXML
    private JFXDatePicker date;

    @FXML
    private JFXTextField suppcode;

    @FXML
    private JFXTextField suppname;

    @FXML
    private JFXTextField supType;

    @FXML
    private JFXTextField contact;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initialize();
    }

    private void initialize(){
        fillcomboboxforSuppliers();
        suppliers.setItems(optionsforSuppliers);
        fillcomboboxfortypes();
        type.setItems(optionsfortypes);
    }


    private void fillcomboboxforSuppliers(){
        try {
            preparedStatement = connection.prepareStatement("select supp_name from suppliers");
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                optionsforSuppliers.add(resultSet.getString("supp_name"));
            }
            preparedStatement.close();
            resultSet.close();
        }catch (Exception e){
            System.err.println(e);
        }
    }

    private void fillcomboboxfortypes(){
        try {
            preparedStatement = connection.prepareStatement("select DISTINCT type from suppliers");
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                optionsfortypes.add(resultSet.getString("type"));
            }
            preparedStatement.close();
            resultSet.close();
        }catch (Exception e){
            System.err.println(e);
        }
    }

    @FXML
    void addItems(ActionEvent event) {
        String itemName = name.getText();
        int itemCode = Integer.parseInt(code.getText());
        int itemPrice = Integer.parseInt(price.getText());
        int itemQtty = Integer.parseInt(qtty.getText());
        String itemSupp = suppliers.getValue().toString();
        String itemType = type.getValue().toString();
        LocalDate exp = date.getValue();

        itemToDB(itemName,itemCode,itemPrice,itemQtty,itemSupp,itemType,exp);

    }

    private void itemToDB(String name, int code, int price, int qtty, String supplier, String type, LocalDate date){
        String sql1 = "Insert into item values (?,?,?)";
        String sql2 = "insert into item_details values (?,?,?,?,?)";
        Date todate = java.sql.Date.valueOf(date);
        int suppID = getSuppID(supplier);
        try {
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setInt(1,code);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,type);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            resultSet.close();
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setInt(1,code);
            preparedStatement.setInt(2,qtty);
            preparedStatement.setDate(3,todate);
            preparedStatement.setInt(4,price);
            preparedStatement.setInt(5,suppID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            resultSet.close();
            new Dashboard().showPopup("Item added Successfully!");
        }catch (Exception e){
            System.err.println(e);
        }
    }

    private int getSuppID(String supplier){
        String sql = "select supp_id from suppliers where supp_name=?";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,supplier);
            resultSet=preparedStatement.executeQuery();
            String output = null;
            while (resultSet.next()){
                output=resultSet.getString("supp_id");
            }
            preparedStatement.close();
            resultSet.close();

            return Integer.parseInt(output);
        }catch (Exception e) {
            System.err.println(e);
        }
        return 0;
    }

    @FXML
    void addSupplier(ActionEvent event) {
        String name = suppname.getText();
        int code = Integer.parseInt(suppcode.getText());
        String type = supType.getText();
        String number = contact.getText();
        suppliersToDB(name,code,type,number);
    }

    private void suppliersToDB(String name, int code, String type, String number){
        String sql = "Insert into suppliers values (?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,code);
            preparedStatement.setString(2,type);
            preparedStatement.setString(3,name);
            preparedStatement.setString(4,number);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            resultSet.close();
            new Dashboard().showPopup("Supplier Added!");
            initialize();
        }catch (Exception e){
            System.err.println(e);
        }
    }

    @FXML
    void openDashboard(MouseEvent event) throws IOException {
        accessMain.loadScene(Main.mainPanel);
    }

    @FXML
    void openItemList(MouseEvent event) throws IOException{
        accessMain.loadScene(Main.itemPanel);
    }

    @FXML
    void openLoginPanel(MouseEvent event) throws IOException{
        accessMain.loadScene(Main.loginPanel);
    }

    @FXML
    void openSuppliers(MouseEvent event) throws IOException {
        accessMain.loadScene(Main.suppliersPanel);
    }

    @FXML
    void openExpired(MouseEvent event) throws IOException {
        accessMain.loadScene(Main.expiredPanel);
    }

    @FXML
    void openSales(MouseEvent event) throws IOException {
        accessMain.loadScene(Main.salesPanel);
    }

}
