package business.services;

import business.entities.*;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.OrderMapper;
import business.persistence.UserMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class OrderFacade
{
    OrderMapper orderMapper;

    public OrderFacade(Database database)
    {
        orderMapper = new OrderMapper(database);
    }

    public int changeOrderStatus(int orderId, Status status) throws UserException {
        return orderMapper.changeOrderStatus(orderId,status);
    }

    public void insertOrder(Order order, ShoppingCart shoppingCart) throws UserException
    {
        orderMapper.insertOrder(order, shoppingCart);
    }

    public TreeMap<Integer,Order> getAllOrders() throws UserException {
        return orderMapper.getAllOrders();
    }

    public int deleteOrder(int orderId) throws UserException {
        return orderMapper.deleteOrder(orderId);
    }
    public int deleteOrderDetails(int orderId) throws UserException {
        return orderMapper.deleteOrderDetails(orderId);
    }
    public TreeMap<Integer,Order> getAllOrdersByUserId(int userId) throws UserException {
        return orderMapper.getAllOrdersByUserId(userId);
    }

}
