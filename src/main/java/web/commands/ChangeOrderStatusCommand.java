package web.commands;

import business.entities.Order;
import business.entities.Status;
import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.TreeMap;

public class ChangeOrderStatusCommand extends CommandProtectedPage{

    public ChangeOrderStatusCommand(String pageToShow, String role) {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        OrderFacade orderFacade = new OrderFacade(database);
        int orderId = Integer.parseInt(request.getParameter("idorder"));
        String status = request.getParameter("statusorder");

        //Change status in DB
        int rowsAffected = orderFacade.changeOrderStatus(orderId,Status.valueOf(status));
        if(rowsAffected == 1){
            request.setAttribute("status","Success! Order "+orderId+ " status updated to "+status);
        }else {
            request.setAttribute("changestatuserror","ERROR! Something went wrong!");
        }
        return pageToShow;
    }

}
