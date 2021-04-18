package business.services;

import business.entities.Topping;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.ProductMapper;

import java.util.List;

public class ProductFacade {
    private ProductMapper productMapper;

    public ProductFacade(Database database) {
        this.productMapper = new ProductMapper(database);
    }

    public List<Topping> getAllToppings() throws UserException {
        return productMapper.getAllToppings();
    }
    public List<Topping> getAllBottoms() throws UserException {
        return productMapper.getAllBottoms();
    }
}
