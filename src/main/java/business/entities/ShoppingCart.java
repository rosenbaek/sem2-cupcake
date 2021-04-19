package business.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ShoppingCart {
    HashMap<Integer,Product> productMap;
    float totalPrice;
    AtomicInteger id;

    public ShoppingCart() {
        this.id = new AtomicInteger();
       this.productMap = new HashMap<>();
    }

    private void calcTotalPrice() {
        totalPrice = 0;
        for (Product tmp : productMap.values()) {
           totalPrice += tmp.getTotalPrice();
        }
    }

    public int getNumberOfItems(){
        return productMap.size();
    }

    public void addToProductMap(Product cupcake) {
        productMap.put(id.incrementAndGet(),cupcake);
        calcTotalPrice();
    }

    public void removeFromProductMap(int id){
        productMap.remove(id);
        calcTotalPrice();
    }


    public HashMap<Integer, Product> getProductMap() {
        return productMap;
    }

    public float getTotalPrice() {
        return totalPrice;
    }
}
