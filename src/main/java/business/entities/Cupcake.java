package business.entities;

import java.util.concurrent.atomic.AtomicInteger;

public class Cupcake {
    private float totalCupcakePrice;
    private int quantity;
    private Topping topping;
    private Bottom bottom;

    public Cupcake(Topping topping, Bottom bottom, int quantity) {
        this.topping = topping;
        this.bottom = bottom;
        this.quantity = quantity;
        calculatePrice();
    }

    private void calculatePrice() {
        totalCupcakePrice = (topping.price + bottom.price) * quantity;
    }

    public void setTotalCupcakePrice(float totalCupcakePrice) {
        this.totalCupcakePrice = totalCupcakePrice;
    }

    public float getTotalCupcakePrice() {
        return totalCupcakePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public Topping getTopping() {
        return topping;
    }

    public Bottom getBottom() {
        return bottom;
    }

    @Override
    public String toString() {
        return "Cupcake{" +
                "totalCupcakePrice=" + totalCupcakePrice +
                ", quantity=" + quantity +
                ", topping=" + topping +
                ", bottom=" + bottom +
                '}';
    }
}

