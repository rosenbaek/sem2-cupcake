package business.services;

import business.entities.Bottom;
import business.entities.Topping;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.ProductMapper;

import java.util.HashMap;
import java.util.List;

public class ProductFacade {
    private ProductMapper productMapper;

    public ProductFacade(Database database) {
        this.productMapper = new ProductMapper(database);
    }

    public HashMap<Integer,Topping> getAllToppings() throws UserException {
        return productMapper.getAllToppings();
    }
    public HashMap<Integer,Bottom> getAllBottoms() throws UserException {
        return productMapper.getAllBottoms();
    }
}
