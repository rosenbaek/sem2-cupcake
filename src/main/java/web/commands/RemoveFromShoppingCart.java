package web.commands;

import business.entities.Bottom;
import business.entities.Cupcake;
import business.entities.ShoppingCart;
import business.entities.Topping;
import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class RemoveFromShoppingCart extends CommandUnprotectedPage {
    public RemoveFromShoppingCart(String pageToShow) {
        super(pageToShow);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingcart");
        String test = (String) request.getParameter("delete");
        System.out.println(test);
        return pageToShow;
    }
}
