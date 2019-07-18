package sample;

public class UpdateItems {
    String item;
    int orderQuantity;

    public UpdateItems(String item, int orderQuantity) {
        this.item = item;
        this.orderQuantity = orderQuantity;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }
}
