package business.entities;

public class Bottom {
    int id;
    String name;
    float price;

    public Bottom(int id, String name, float price) {
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
