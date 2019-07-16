package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Order_details {
    private final StringProperty order_id;
    private final StringProperty order_date;
    private final StringProperty order_quantity;
    private final StringProperty price;

    public Order_details(String order_id, String order_date, String order_quantity, String price) {
        this.order_id = new SimpleStringProperty(order_id);
        this.order_date = new SimpleStringProperty(order_date);
        this.order_quantity = new SimpleStringProperty(order_quantity);
        this.price = new SimpleStringProperty(price);
    }

    public String getOrder_id() {
        return order_id.get();
    }

    public StringProperty order_idProperty() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id.set(order_id);
    }

    public String getOrder_date() {
        return order_date.get();
    }

    public StringProperty order_dateProperty() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date.set(order_date);
    }

    public String getOrder_quantity() {
        return order_quantity.get();
    }

    public StringProperty order_quantityProperty() {
        return order_quantity;
    }

    public void setOrder_quantity(String order_quantity) {
        this.order_quantity.set(order_quantity);
    }

    public String getPrice() {
        return price.get();
    }

    public StringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }
}
