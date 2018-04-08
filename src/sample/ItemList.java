package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ItemList implements Initializable {

    Main accessMain = new Main();
    Connection connection = DatabaseManager.DBconnect();
    ObservableList<Item_details> data = FXCollections.observableArrayList();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    /* Table and column variables */

    @FXML
    private TableView<Item_details> itemlist;

    @FXML
    private TableColumn<?, ?> code;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<?, ?> type;

    @FXML
    private TableColumn<?, ?> price;

    @FXML
    private TableColumn<?, ?> quantity;

    @FXML
    private TableColumn<?, ?> exp;

    @FXML
    private TableColumn<?, ?> supp;


    /* Table and column variables */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        code.setCellValueFactory(new PropertyValueFactory<>("item_code"));
        name.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        type.setCellValueFactory(new PropertyValueFactory<>("item_type"));
        price.setCellValueFactory(new PropertyValueFactory<>("item_price"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("item_quantity"));
        exp.setCellValueFactory(new PropertyValueFactory<>("exp_date"));
        loadDB();

    }

    public void loadDB(){
        System.out.println("loaddb run");
        try{
            preparedStatement = connection.prepareStatement("SELECT  item.item_code, item.item_name, item.item_type, item_details.item_price, item_details.item_quantity, item_details.exp_date FROM `item_details`, `item` WHERE item.item_code=item_details.item_code ORDER BY item_code");
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                data.add(new Item_details(
                        resultSet.getString("item_code"),
                        resultSet.getString("item_name"),
                        resultSet.getString("item_type"),
                        resultSet.getString("item_price"),
                        resultSet.getString("item_quantity"),
                        resultSet.getString("exp_date")
                ));
                itemlist.setItems(data);
            }
            preparedStatement.close();
            resultSet.close();

        }catch (Exception e){
            System.err.println(e);
        }
    }

    /* UI NAVIGATION METHODS */

    @FXML
    void openDashboard(MouseEvent event) throws IOException {
        accessMain.loadScene(Main.mainPanel);
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

    @FXML
    void openLoginPanel(MouseEvent event) throws IOException {
        accessMain.loadScene(Main.loginPanel);
    }
}
