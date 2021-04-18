package business.entities;

public class Topping {
    int id;
    String name;
    float price;

    public Topping(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
