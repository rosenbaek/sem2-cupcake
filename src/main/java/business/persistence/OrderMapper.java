package business.persistence;

import business.entities.Cupcake;
import business.entities.Order;
import business.entities.ShoppingCart;
import business.entities.User;
import business.exceptions.UserException;

import java.sql.*;

public class OrderMapper {
    private Database database;

    public OrderMapper(Database database) {
        this.database = database;
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

                    for (Cupcake tmp : shoppingCart.getCupcakeList()) {
                        int toppingsId = tmp.getTopping().getId();
                        int bottomsId = tmp.getBottom().getId();
                        int quantity = tmp.getQuantity();
                        int orderId = order.getId();
                        try {
                            insertOrderDetail(toppingsId, bottomsId, quantity, orderId);
                        } catch (Exception ex) {
                           //Delete entry in orders and entries in order_Details for the order
                            deleteOrderDetails(orderId);
                            deleteOrder(orderId);
                            throw new UserException(ex.getMessage());
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
