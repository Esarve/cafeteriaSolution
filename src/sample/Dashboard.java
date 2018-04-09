package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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

    /* FOR DATABASE AND ITEM FETCHING STUFFS */

    Main accessMain = new Main();
    Connection connection = DatabaseManager.DBconnect();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    public final ObservableList optionsforitems = FXCollections.observableArrayList();

    /* UI VARIABLES */

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
    private JFXTextField qtty1;

    @FXML
    private JFXTextField qtty2;

    @FXML
    private JFXTextField qtty3;

    @FXML
    private JFXTextField qtty4;

    @FXML
    private JFXTextField qtty5;

    @FXML
    private JFXTextField qtty6;

    @FXML
    private Label total;


    /* ORDER BUTTON ACTION */

    public void orderbuttonaction(ActionEvent event) {

        /* Variables for setting items from combobox and textfield */

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

        selectedItem1 = getvaluefromcb1();  //fetches item from combobox 1
        selectedItem2 = getvaluefromcb2();  //fetches item from combobox 2
        selectedItem3 = getvaluefromcb3();  //fetches item from combobox 3
        selectedItem4 = getvaluefromcb4();  //fetches item from combobox 4
        selectedItem5 = getvaluefromcb5();  //fetches item from combobox 5
        selectedItem6 = getvaluefromcb6();  //fetches item from combobox 6


        // GETS PRICE FROM DATABASE AND QUANTITY FROM TEXT FIELD
        price1 = getPrice(selectedItem1) * getQuantity1();
        price2 = getPrice(selectedItem2) * getQuantity2();
        price3 = getPrice(selectedItem3) * getQuantity3();
        price4 = getPrice(selectedItem4) * getQuantity4();
        price5 = getPrice(selectedItem5) * getQuantity5();
        price6 = getPrice(selectedItem6) * getQuantity6();

        totalbiill = price1+price2+price3+price4+price5+price6;
        total.setText(Double.toString(totalbiill));

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

    /* POPULATES THE COMBOBOXES WHILE INITIALIZATION */

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

    /* Get values from Comboboxes and returns them as Strings */

    private String getvaluefromcb1(){
        try{
            return cb1.getValue().toString();
        }catch (Exception e){
            return null;
        }
    }

    private String getvaluefromcb2(){
        try{
           return cb2.getValue().toString();
        }catch (Exception e){
            return null;
        }
    }

    private String getvaluefromcb3(){
        try{
            return cb3.getValue().toString();
        }catch (Exception e){
            return null;
        }
    }

    private String getvaluefromcb4(){
        try{
            return cb4.getValue().toString();
        }catch (Exception e){
            return null;
        }
    }

    private String getvaluefromcb5(){
        try{
            return cb5.getValue().toString();
        }catch (Exception e){
            return null;
        }
    }

    private String getvaluefromcb6(){
        try{
            return cb6.getValue().toString();
        }catch (Exception e){
            return null;
        }
    }

    /* Get values form the Textfields and returns them as Ints */

    private int getQuantity1(){
        try {
            int qutty;
            qutty = Integer.parseInt(qtty1.getText());
            return qutty;

        }catch (Exception e){
            System.out.println(e);
            return 1;
        }
    }

    private int getQuantity2(){
        try {
            return Integer.parseInt(qtty2.getText());
        }catch (Exception e){
            return 1;
        }
    }

    private int getQuantity3(){
        try {
            return Integer.parseInt(qtty3.getText());
        }catch (Exception e){
            return 1;
        }
    }

    private int getQuantity4(){
        try {
            return Integer.parseInt(qtty4.getText());
        }catch (Exception e){
            return 1;
        }
    }

    private int getQuantity5(){
        try {
            return Integer.parseInt(qtty5.getText());
        }catch (Exception e){
            return 1;
        }
    }

    private int getQuantity6(){
        try {
            return Integer.parseInt(qtty6.getText());
        }catch (Exception e){
            return 1;
        }
    }


    /* UI navigation methods */

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
