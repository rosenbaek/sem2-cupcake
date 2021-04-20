package web.commands;

import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.TreeMap;

public class ShowOrdersCommand extends CommandUnprotectedPage{
    public ShowOrdersCommand(String pageToShow) {
        super(pageToShow);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        OrderFacade orderFacade = new OrderFacade(database);
        TreeMap<Integer,Order> orders;

        if (user.getRole().equals("customer")){
            //TODO make logic to get all orders by userid
            orders = orderFacade.getAllOrdersByUserId(user.getId());
            request.setAttribute("orders", orders);
            return pageToShow;

        } else if (user.getRole().equals("employee")){
            //TODO make logic to get all orders by any user
            orders = orderFacade.getAllOrders();
            request.setAttribute("orders", orders);
            return "adminallorderspage";
        }

        return "index";
    }
}
