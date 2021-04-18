package business.persistence;

import business.entities.Bottom;
import business.entities.Topping;
import business.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductMapper {
    private Database database;

    public ProductMapper(Database database) {
        this.database = database;
    }

    public HashMap<Integer,Topping> getAllToppings() throws UserException {
        HashMap<Integer,Topping> toppingMap = new HashMap();

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
                    toppingMap.put(id,new Topping(id,name,price));
                }
                return toppingMap;
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
    public HashMap<Integer,Bottom> getAllBottoms() throws UserException {
        HashMap<Integer,Bottom>  bottomMap = new HashMap<>();

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
                    bottomMap.put(id,new Bottom(id,name,price));

                }
                return bottomMap;
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
