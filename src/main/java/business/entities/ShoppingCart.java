package business.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ShoppingCart {
    List<Cupcake> cupcakeList;
    float totalPrice;

    public ShoppingCart() {
       this.cupcakeList = new ArrayList<>();
    }

    private void calcTotalPrice() {
        totalPrice = 0;
        for (Cupcake tmp : cupcakeList) {
            totalPrice += tmp.getTotalCupcakePrice();
        }
    }

    public int getNumberOfItems(){
        return cupcakeList.size();
    }

    public void addToCupcakelist(Cupcake cupcake) {
        cupcakeList.add(cupcake);
        calcTotalPrice();
    }
    public void removeFromCupcakeList(Cupcake cupcake) {
        cupcakeList.remove(cupcake);
        calcTotalPrice();
    }

    public List<Cupcake> getCupcakeList() {
        return cupcakeList;
    }

    public float getTotalPrice() {
        return totalPrice;
    }
}
