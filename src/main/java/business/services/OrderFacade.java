package business.services;

import business.entities.Order;
import business.entities.ShoppingCart;
import business.entities.User;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.OrderMapper;
import business.persistence.UserMapper;

import java.util.HashMap;

public class OrderFacade
{
    OrderMapper orderMapper;

    public OrderFacade(Database database)
    {
        orderMapper = new OrderMapper(database);
    }

    public void insertOrder(Order order, ShoppingCart shoppingCart) throws UserException
    {
        orderMapper.insertOrder(order, shoppingCart);
    }

    public HashMap<Integer,Order> getAllOrders() throws UserException {
        return orderMapper.getAllOrders();
    }

    public int deleteOrder(int orderId) throws UserException {
        return orderMapper.deleteOrder(orderId);
    }
    public int deleteOrderDetails(int orderId) throws UserException {
        return orderMapper.deleteOrderDetails(orderId);
    }

}
