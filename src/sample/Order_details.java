package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Order_details {
    private StringProperty order_id;
    private StringProperty date;
    private StringProperty total_items;
    private StringProperty total_price;

    public Order_details(String order_id, String date, String total_items, String total_price) {
        this.order_id = new SimpleStringProperty(order_id);
        this.date = new SimpleStringProperty(date);
        this.total_items = new SimpleStringProperty(total_items);
        this.total_price = new SimpleStringProperty(total_price);
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

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getTotal_items() {
        return total_items.get();
    }

    public StringProperty total_itemsProperty() {
        return total_items;
    }

    public void setTotal_items(String total_items) {
        this.total_items.set(total_items);
    }

    public String getTotal_price() {
        return total_price.get();
    }

    public StringProperty total_priceProperty() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price.set(total_price);
    }
}
