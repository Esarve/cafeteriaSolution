package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Item_details {

    private final StringProperty item_name;
    private final StringProperty item_type;
    private final StringProperty item_code;
    private final StringProperty item_price;
    private final StringProperty item_quantity;
    private final StringProperty exp_date;

    public Item_details(String item_code, String item_name, String item_type, String item_price, String item_quantity, String exp_date) {
        this.item_code = new SimpleStringProperty(item_code);
        this.item_name = new SimpleStringProperty(item_name);
        this.item_type = new SimpleStringProperty(item_type);
        this.item_price = new SimpleStringProperty(item_price);
        this.item_quantity = new SimpleStringProperty(item_quantity);
        this.exp_date = new SimpleStringProperty(exp_date);
    }

    public String getItem_code() {
        return item_code.get();
    }

    public StringProperty item_codeProperty() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code.set(item_code);
    }

    public String getItem_price() {
        return item_price.get();
    }

    public StringProperty item_priceProperty() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price.set(item_price);
    }

    public String getItem_quantity() {
        return item_quantity.get();
    }

    public StringProperty item_quantityProperty() {
        return item_quantity;
    }

    public void setItem_quantity(String item_quantity) {
        this.item_quantity.set(item_quantity);
    }

    public String getExp_date() {
        return exp_date.get();
    }

    public StringProperty exp_dateProperty() {
        return exp_date;
    }

    public void setExp_date(String exp_date) {
        this.exp_date.set(exp_date);
    }

    public String getItem_name() {
        return item_name.get();
    }

    public StringProperty item_nameProperty() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name.set(item_name);
    }

    public String getItem_type() {
        return item_type.get();
    }

    public StringProperty item_typeProperty() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type.set(item_type);
    }

}
