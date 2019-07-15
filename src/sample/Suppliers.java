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

public class Suppliers implements Initializable {

    Main accessMain = new Main();
    Connection connection = DatabaseManager.DBconnect();
    ObservableList<Supplier_details> data = FXCollections.observableArrayList();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    @FXML
    private TableView<Supplier_details> suppliers;

    @FXML
    private TableColumn<?, ?> suppID;

    @FXML
    private TableColumn<?, ?> suppName;

    @FXML
    private TableColumn<?, ?> suppType;

    @FXML
    private TableColumn<?, ?> suppContact;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        suppID.setCellValueFactory(new PropertyValueFactory<>("supp_id"));
        suppName.setCellValueFactory(new PropertyValueFactory<>("supp_name"));
        suppType.setCellValueFactory(new PropertyValueFactory<>("type"));
        suppContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        loadDB();
    }

    private void loadDB(){
        try {
            preparedStatement = connection.prepareStatement("select * from suppliers");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                data.add(new Supplier_details(
                resultSet.getString("supp_id"),
                resultSet.getString("type"),
                resultSet.getString("supp_name"),
                resultSet.getString("contact")
                ));
                suppliers.setItems(data);
            }
        }catch (Exception e){
            System.err.println(e);
            System.err.println("Faild To LOAD suppliers");
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
