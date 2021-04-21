package web.commands;

import business.entities.Order;
import business.entities.Status;
import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.TreeMap;

public class EmployeePageCommand extends CommandProtectedPage{
    public EmployeePageCommand(String pageToShow, String role) {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        //Hent ordre fra DB
        OrderFacade orderFacade = new OrderFacade(database);
        TreeMap<Integer, Order> orders;
        orders = orderFacade.getAllOrders();
        request.setAttribute("orderids", orders.keySet());
        return pageToShow;
    }
}
