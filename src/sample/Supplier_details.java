package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Supplier_details {
    private StringProperty supp_id;
    private StringProperty type;
    private StringProperty supp_name;
    private StringProperty contact;

    public Supplier_details(String supp_id, String type, String supp_name, String contact) {
        this.supp_id = new SimpleStringProperty(supp_id);
        this.type = new SimpleStringProperty(type);
        this.supp_name = new SimpleStringProperty(supp_name);
        this.contact = new SimpleStringProperty(contact);
    }

    public String getSupp_id() {
        return supp_id.get();
    }

    public StringProperty supp_idProperty() {
        return supp_id;
    }

    public void setSupp_id(String supp_id) {
        this.supp_id.set(supp_id);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getSupp_name() {
        return supp_name.get();
    }

    public StringProperty supp_nameProperty() {
        return supp_name;
    }

    public void setSupp_name(String supp_name) {
        this.supp_name.set(supp_name);
    }

    public String getContact() {
        return contact.get();
    }

    public StringProperty contactProperty() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact.set(contact);
    }
}
