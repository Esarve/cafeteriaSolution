package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {

    /* FOR DATABASE AND ITEM FETCHING STUFFS */
    private boolean statelock = false;
    private Main accessMain = new Main();
    private Connection connection = DatabaseManager.DBconnect();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private final ObservableList optionsforitems = FXCollections.observableArrayList();
    private final ObservableList<Order> order = FXCollections.observableArrayList();
    private int finalPrice;
    private int totalQuantity;
    private int orderNumber=0;
    /* UI VARIABLES */

    @FXML
    private JFXComboBox<?> cb1;

    @FXML
    private JFXTextField qtty1;

    @FXML
    private Label total;

    @FXML
    private TableView<Order> orderlist;

    @FXML
    private TableColumn<Order, String> item;

    @FXML
    private TableColumn<Order, Integer> quantity;

    @FXML
    private TableColumn<Order, Integer> totalPrice;

    @FXML
    private TableColumn<Order, Integer> price;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillcomboboxforItems();
        cb1.setItems(optionsforitems);
        item.setCellValueFactory(new PropertyValueFactory<>("item"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    }

    // add to order button action

    public void addToOrder(ActionEvent actionEvent) {
        statelock = true;
        String selectedItem;
        selectedItem = getvaluefromcb1();
        int itemPrice;
        int quantity;
        int totalPrice;
        int remaining = getQuantityFromDB(selectedItem);
        quantity = getQuantity1();
        if (remaining >= quantity){
            itemPrice = getPrice(selectedItem);

            totalPrice = itemPrice * quantity;
            totalQuantity += quantity;
            finalPrice += totalPrice;
            order.add(
                    new Order(selectedItem, itemPrice, quantity, totalPrice)
            );
            orderlist.setItems(order);
            orderNumber++;
        }else{
            showPopup("Order number is more than remaining!");
        }

    }

    /* ORDER BUTTON ACTION */

    public void orderbuttonaction(ActionEvent event) {
        int totalItems;
        double totalbiill;
        totalbiill = finalPrice;
        totalItems = totalQuantity;
        updateNewQuantity();
        placeOrder((int)totalbiill, totalItems);
        total.setText(Double.toString(totalbiill));
        statelock = false;
    }

    private void updateNewQuantity(){
        String item;
        int quantity;
        String sql;
        for (int i = 0 ; i < orderNumber; i++){
            item = this.item.getCellObservableValue(i).getValue();
            quantity = getQuantityFromDB(item) - this.quantity.getCellObservableValue(i).getValue();
            try {
                sql = "UPDATE item_details SET item_quantity ="+quantity+" WHERE item_code in (select item_code from item where item_name = ?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,item);
                preparedStatement.executeUpdate();
            }catch (SQLException e){
                System.err.println(e);
                System.err.println("ERROR UPDATING DATA");
            }
        }
    }


    // adds the order info into the database

    private void placeOrder(int price, int orders){
        String sql = "Insert into orders values (?,?,?,?)";
        LocalDate today = new java.sql.Date( new java.util.Date().getTime() ).toLocalDate();
        java.sql.Date date = java.sql.Date.valueOf(today);
        int orderID = getOrderID();
        orderID++;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,orderID);
            preparedStatement.setDate(2,date);
            preparedStatement.setInt(3,orders);
            preparedStatement.setInt(4,price);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            resultSet.close();
            new Dashboard().showPopup("Order Added!");
        }catch (Exception e){
            System.err.println(e);
        }
    }

    // Clear button action
    public void clearOrder(){
        orderlist.getItems().clear();
        finalPrice = 0;
        totalQuantity = 0;
        qtty1.clear();
        statelock = false;
        orderNumber = 0;
    }

    private int getQuantityFromDB(String item){
        String sql = "select item_quantity from item_details where item_code in (select item_code from item where item_name = ?)";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,item);
            resultSet=preparedStatement.executeQuery();
            String output = null;
            while (resultSet.next()){
                output=resultSet.getString(1);
            }
            preparedStatement.close();
            resultSet.close();

            return Integer.parseInt(output);
        }catch (Exception e) {
            System.err.println(e);
        }
        return 0;
    }

    // fetches price from the Database

    private int getPrice(String item){
        String sql = "select item_price from item_details where item_code in (select item_code from item where item_name = ?)";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,item);
            resultSet=preparedStatement.executeQuery();
            String output = null;
            while (resultSet.next()){
                output=resultSet.getString(1);
            }
            preparedStatement.close();
            resultSet.close();

            return Integer.parseInt(output);
        }catch (Exception e) {
            System.err.println(e);
        }
        return 0;
    }

    /* POPULATES THE COMBOBOXE WHILE INITIALIZATION */

    private void fillcomboboxforItems(){
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

    /* Get values from Comboboxes and returns them as Strings */

    private String getvaluefromcb1(){
        try{
            return cb1.getValue().toString();
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
            return 0;
        }
    }

    // generates and order ID
    private int getOrderID(){
        String sql = "select order_id from orders order by order_id DESC limit 1";
        try{
            preparedStatement = connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            String output = null;
            while (resultSet.next()){
                output=resultSet.getString(1);
            }
            preparedStatement.close();
            resultSet.close();

            return Integer.parseInt(output);
        }catch (Exception e) {
            System.err.println(e);
        }
        return 0;
    }

    // For displaying POPUPS

    public void showPopup(String msg){
        VBox popup = new VBox();
        popup.setPrefHeight(100);
        popup.setPrefWidth(250);
        JFXButton confirm =new JFXButton("OK");
        confirm.setPrefSize(50,20);
        confirm.setStyle("-fx-background-color:   #e86363; -fx-text-fill: #FFFFFF");
        Label label = new Label(msg);
        popup.setSpacing(30);
        popup.setAlignment(Pos.CENTER);
        popup.getChildren().add(label);
        popup.getChildren().add(confirm);
        confirm.setCancelButton(true);
        Scene newScene = new Scene(popup);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(newScene);
        stage.setResizable(false);
        stage.setTitle("Message");
        stage.show();
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });

    }

    /* UI navigation methods */

    @FXML
    void openLoginPanel(MouseEvent event) throws IOException {
        if (!statelock){
            accessMain.loadScene(Main.loginPanel);
        }else{
            showPopup("Confirm Your order or clear the order first.");
        }
    }

    @FXML
    void openItemPanel(MouseEvent event) throws IOException {
        if (!statelock){
            accessMain.loadScene(Main.itemPanel);
        }else{
            showPopup("Confirm Your order or clear the order first.");
        }
    }

    @FXML
    void openSuppliers(MouseEvent event) throws IOException{
        if (!statelock){
            accessMain.loadScene(Main.suppliersPanel);
        }else{
            showPopup("Confirm Your order or clear the order first.");
        }
    }

    @FXML
    void openExpired(MouseEvent event) throws IOException{
        if (!statelock){
            accessMain.loadScene(Main.expiredPanel);
        }else{
            showPopup("Confirm Your order or clear the order first.");
        }
    }

    @FXML
    void openModify(MouseEvent event) throws IOException {
        if (!statelock){
            accessMain.loadScene(Main.modifyPanel);
        }else{
            showPopup("Confirm Your order or clear the order first.");
        }
    }

    @FXML
    void openSales(MouseEvent event) throws IOException {
        if (!statelock){
            accessMain.loadScene(Main.salesPanel);
        }else{
            showPopup("Confirm Your order or clear the order first.");
        }
    }


}
