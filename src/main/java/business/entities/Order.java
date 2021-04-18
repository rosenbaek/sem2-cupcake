package business.entities;

import java.sql.Timestamp;

public class Order {
    private int id;
    private float totalPrice;
    private String status;
    private Timestamp timestamp;
    private int userId;


    public Order(float totalPrice, String status, int userId) {
        this.totalPrice = totalPrice;
        this.status = status;
        this.userId = userId;
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
}
