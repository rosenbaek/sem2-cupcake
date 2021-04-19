package business.entities;

import java.util.concurrent.atomic.AtomicInteger;

public class Cupcake extends Product{
    private Topping topping;
    private Bottom bottom;

    public Cupcake(String name, int quantity, float totalPrice, Bottom bottom, Topping topping) {
        super(name, quantity, totalPrice);
        this.bottom = bottom;
        this.topping = topping;
    }

    @Override
    public String toString() {
        return "Cupcake{" +
                "topping=" + topping +
                ", bottom=" + bottom +
                '}';
    }

    public Topping getTopping() {
        return topping;
    }

    public Bottom getBottom() {
        return bottom;
    }

}

