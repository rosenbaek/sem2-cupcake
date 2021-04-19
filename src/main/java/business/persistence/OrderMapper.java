package business.persistence;

import business.entities.Cupcake;
import business.entities.Order;
import business.entities.Product;
import business.entities.ShoppingCart;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderMapper {
    private Database database;

    public OrderMapper(Database database) {
        this.database = database;
    }


    public HashMap<Integer,Order> getAllOrders() throws UserException {

        HashMap<Integer,Order> orders = new HashMap<>();
        try (Connection connection = database.connect())
        {
            String sql = "SELECT * FROM orders;";

            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    int orderId = rs.getInt("id");
                    float totalPrice = rs.getFloat("totalPrice");
                    String status = rs.getString("status");
                    Timestamp ts = rs.getTimestamp("timestamp");
                    int userId = rs.getInt("users_id");

                    Order order = new Order(totalPrice,status,userId);
                    order.setId(orderId);
                    order.setTimestamp(ts);

                    orders.put(orderId, order);

                }
                return orders;
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

    public int deleteOrder(int orderId) throws UserException {
        try (Connection connection = database.connect())
        {
            String sql = "DELETE FROM `orders` WHERE (`id` = ?);";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                ps.setInt(1,orderId);
                int rowsAffected = ps.executeUpdate();
                return rowsAffected;
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            throw new UserException(ex.getMessage());
        }
    }

    public int deleteOrderDetails(int orderId) throws UserException {
        try (Connection connection = database.connect())
        {
            String sql = "DELETE FROM `order_details` WHERE (`orders_id` = ?);";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                ps.setInt(1,orderId);
                int rowsAffected = ps.executeUpdate();
                return rowsAffected;
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            throw new UserException(ex.getMessage());
        }
    }


    public void insertOrder(Order order, ShoppingCart shoppingCart) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO orders (totalPrice,status,users_id) VALUES (?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setFloat(1, order.getTotalPrice());
                ps.setString(2, order.getStatus());
                ps.setInt(3, order.getUserId());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                if (id <= 0) {
                    throw new UserException("Error when inserting order");
                }
                order.setId(id);

                    for (Product tmp : shoppingCart.getProductMap().values()) {
                        int quantity = tmp.getQuantity();
                        int orderId = order.getId();

                        if (tmp instanceof Cupcake) {
                            int toppingsId = ((Cupcake) tmp).getTopping().getId();
                            int bottomsId = ((Cupcake) tmp).getBottom().getId();
                            try {
                                insertOrderDetail(toppingsId, bottomsId, quantity, orderId);
                            } catch (Exception ex) {
                                //Delete entry in orders and entries in order_Details for the order
                                deleteOrderDetails(orderId);
                                deleteOrder(orderId);
                                throw new UserException(ex.getMessage());
                            }
                        }
                    }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public boolean insertOrderDetail(int toppingsId, int bottomsId, int quantity, int orderId) throws UserException, SQLException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO `order_details` (toppings_id,bottoms_id,quantity,orders_id) VALUES (?, ?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, toppingsId);
                ps.setInt(2, bottomsId);
                ps.setInt(3, quantity);
                ps.setInt(4, orderId);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    return true;
                }
                throw new SQLException("Error while inserting into order_details");
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }




}
