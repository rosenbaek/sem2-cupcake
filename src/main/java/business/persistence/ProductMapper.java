package business.persistence;

import business.entities.Topping;
import business.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductMapper {
    private Database database;

    public ProductMapper(Database database) {
        this.database = database;
    }

    public List<Topping> getAllToppings() throws UserException {
        List<Topping> toppingList = new ArrayList<>();

        try (Connection connection = database.connect())
        {
            String sql = "SELECT * FROM cupcake.toppings;";

            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");

                    toppingList.add(new Topping(id,name,price));
                }
                return toppingList;
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            throw new UserException("Connection to database could not be established");
        }
    }
    public List<Topping> getAllBottoms() throws UserException {
        List<Topping> bottomList = new ArrayList<>();

        try (Connection connection = database.connect())
        {
            String sql = "SELECT * FROM cupcake.bottoms;";

            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");

                    bottomList.add(new Topping(id,name,price));
                }
                return bottomList;
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            throw new UserException("Connection to database could not be established");
        }
    }
}
