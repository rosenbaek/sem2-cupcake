package business.entities;

public abstract class Product {
    private String name;
    private int quantity;
    private float totalPrice;

    public Product(String name, int quantity, float totalPrice) {
        this.name = name;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }
}
