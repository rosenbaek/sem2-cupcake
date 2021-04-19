package web.commands;

import business.entities.Order;
import business.entities.ShoppingCart;
import business.entities.User;
import business.exceptions.UserException;
import business.services.OrderFacade;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddCreditToUserCommand extends CommandProtectedPage{
    public AddCreditToUserCommand(String pageToShow, String role) {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)  {
        HttpSession session = request.getSession();
        UserFacade userFacade = new UserFacade(database);

        int userId = Integer.parseInt(request.getParameter("userid"));
        float amount = Float.parseFloat(request.getParameter("amount"));
        try {
            userFacade.addCreditToUserBalance(userId,amount);
        } catch (UserException e) {
            request.setAttribute("error", e.getMessage());
        }

        return pageToShow;
    }
}
