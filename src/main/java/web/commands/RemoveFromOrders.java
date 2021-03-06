package web.commands;

import business.entities.Order;
import business.entities.ShoppingCart;
import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class RemoveFromOrders extends CommandProtectedPage {
    public RemoveFromOrders(String pageToShow, String role) {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        OrderFacade orderFacade = new OrderFacade(database);
        int deleteId = Integer.parseInt(request.getParameter("delete"));
        System.out.println("im here");
        //Delete in order_details first then orders
        System.out.println(orderFacade.deleteOrderDetails(deleteId));
        orderFacade.deleteOrder(deleteId);

        TreeMap<Integer, Order> orders = orderFacade.getAllOrders();

        request.setAttribute("orders", orders);
        return pageToShow;
    }
}
