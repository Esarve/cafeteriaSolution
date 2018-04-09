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

public class Sales implements Initializable {

    Main accessMain = new Main();
    Connection connection = DatabaseManager.DBconnect();
    ObservableList<Order_details> data = FXCollections.observableArrayList();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @FXML
    private TableView<Order_details> itemlist;

    @FXML
    private TableColumn<?, ?> code;

    @FXML
    private TableColumn<?, ?> date;

    @FXML
    private TableColumn<?, ?> orders;

    @FXML
    private TableColumn<?, ?> price;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        code.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        orders.setCellValueFactory(new PropertyValueFactory<>("total_item"));
        price.setCellValueFactory(new PropertyValueFactory<>("total_price"));
        initDB();
    }

    private void initDB(){
        try{
            preparedStatement = connection.prepareStatement("select * from orders");
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                data.add(new Order_details(
                        resultSet.getString("order_id"),
                        resultSet.getString("date"),
                        resultSet.getString("total_items"),
                        resultSet.getString("total_price")
                ));
                itemlist.setItems(data);
            }
            preparedStatement.close();
            resultSet.close();

        }catch (Exception e){
            System.err.println(e);
        }
    }

    @FXML
    void openDashboard(MouseEvent event) throws IOException {
        accessMain.loadScene(Main.mainPanel);
    }

    @FXML
    void openExpired(MouseEvent event)throws IOException {
        accessMain.loadScene(Main.expiredPanel);
    }

    @FXML
    void openItemList(MouseEvent event)throws IOException {
        accessMain.loadScene(Main.itemPanel);
    }

    @FXML
    void openLoginPanel(MouseEvent event)throws IOException {
        accessMain.loadScene(Main.loginPanel);
    }

    @FXML
    void openSuppliers(MouseEvent event) throws IOException{
        accessMain.loadScene(Main.suppliersPanel);
    }

    @FXML
    void openModify(MouseEvent event) throws IOException {
        accessMain.loadScene(Main.modifyPanel);
    }

}
