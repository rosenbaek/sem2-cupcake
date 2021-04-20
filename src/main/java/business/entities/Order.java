package business.entities;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Order {
    private int id;
    private float totalPrice;
    private String status;
    private Timestamp timestamp;
    private int userId;
    private ArrayList<Product> products;


    public Order(float totalPrice, String status, int userId) {
        this.totalPrice = totalPrice;
        this.status = status;
        this.userId = userId;
        this.products = new ArrayList<>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public int getUserId() {
        return userId;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }
}
