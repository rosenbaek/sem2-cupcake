package business.persistence;

import business.entities.*;
import business.exceptions.UserException;
import web.FrontController;

import java.sql.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class OrderMapper {
    private Database database;

    public OrderMapper(Database database) {
        this.database = database;
    }


    public int changeOrderStatus(int orderId, Status status) throws UserException {
        try (Connection connection = database.connect())
        {
            String sql = "UPDATE orders SET status = ? WHERE (`id` = ?);";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                ps.setString(1,status.toString());
                ps.setInt(2,orderId);
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



    public TreeMap<Integer,Order> getAllOrdersByUserId(int userId) throws UserException {
        TreeMap<Integer,Order> orders = new TreeMap<>();
        try (Connection connection = database.connect())
        {
            String sql = "SELECT * FROM orders INNER JOIN order_details ON orders.id = order_details.orders_id WHERE orders.users_id = ?;";

            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    Order order = null;
                    Cupcake cupcake = null;
                    int orderId = rs.getInt("id");
                    float totalPrice = rs.getFloat("totalPrice");
                    String status = rs.getString("status");
                    Timestamp ts = rs.getTimestamp("timestamp");
                    userId = rs.getInt("users_id");
                    int quantity = rs.getInt("quantity");

                    order = new Order(totalPrice,Status.valueOf(status),userId);
                    order.setId(orderId);
                    order.setTimestamp(ts);



                    //Topping and bottom is needed to construct cupcake
                    Topping topping = FrontController.toppingMap.get(rs.getInt("toppings_id"));
                    Bottom bottom = FrontController.bottomMap.get(rs.getInt("bottoms_id"));
                    String name = (topping.getName() + "/" + bottom.getName());
                    //Calculate cupcake price
                    float totalCupcakePrice = (bottom.getPrice() + topping.getPrice()) * quantity;
                    //Construct Cupcake
                    cupcake = new Cupcake(name,quantity,totalCupcakePrice,bottom,topping);

                    //Checks if order exist in the map. If yes: adds product to order arraylist else add order to map
                    if (orders.containsKey(orderId)){
                        orders.get(orderId).addProduct(cupcake);
                    } else {
                        order.addProduct(cupcake);
                        orders.put(orderId, order);
                    }
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


    public TreeMap<Integer,Order> getAllOrders() throws UserException {

        TreeMap<Integer,Order> orders = new TreeMap<>();
        try (Connection connection = database.connect())
        {
            String sql = "SELECT * FROM orders INNER JOIN order_details ON orders.id = order_details.orders_id";

            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    Order order = null;
                    Cupcake cupcake = null;
                    int orderId = rs.getInt("id");
                    float totalPrice = rs.getFloat("totalPrice");
                    String status = rs.getString("status");
                    Timestamp ts = rs.getTimestamp("timestamp");
                    int userId = rs.getInt("users_id");
                    int quantity = rs.getInt("quantity");

                    order = new Order(totalPrice,Status.valueOf(status),userId);
                    order.setId(orderId);
                    order.setTimestamp(ts);



                    //Topping and bottom is needed to construct cupcake
                    Topping topping = FrontController.toppingMap.get(rs.getInt("toppings_id"));
                    Bottom bottom = FrontController.bottomMap.get(rs.getInt("bottoms_id"));
                    String name = (topping.getName() + "/" + bottom.getName());
                    //Calculate cupcake price
                    float totalCupcakePrice = (bottom.getPrice() + topping.getPrice()) * quantity;
                    //Construct Cupcake
                    cupcake = new Cupcake(name,quantity,totalCupcakePrice,bottom,topping);

                    //Checks if order exist in the map. If yes: adds product to order arraylist else add order to map
                    if (orders.containsKey(orderId)){
                        orders.get(orderId).addProduct(cupcake);
                    } else {
                        order.addProduct(cupcake);
                        orders.put(orderId, order);
                    }
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
                ps.setString(2, order.getStatus().toString());
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
