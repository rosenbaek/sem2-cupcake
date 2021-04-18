package web.commands;

import business.entities.Bottom;
import business.entities.Cupcake;
import business.entities.Topping;
import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddToShoppingCart extends CommandUnprotectedPage {
    public AddToShoppingCart(String pageToShow) {
        super(pageToShow);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        Cupcake cupcake;
        List<Cupcake> cupcakeList = new ArrayList<>();
        HashMap<Integer,Topping> toppingMap = (HashMap<Integer, Topping>) request.getServletContext().getAttribute("toppingMap");
        HashMap<Integer,Bottom> bottomMap = (HashMap<Integer, Bottom>) request.getServletContext().getAttribute("bottomMap");
        int toppingId = Integer.parseInt(request.getParameter("toppingid"));
        int bottomId = Integer.parseInt(request.getParameter("bottomid"));
        Topping topping = toppingMap.get(toppingId);
        Bottom bottom = bottomMap.get(bottomId);

        if (session.getAttribute("shoppingcart") != null){
            cupcakeList = (List<Cupcake>) session.getAttribute("shoppingcart");
        }

        cupcake = new Cupcake(
                topping,
                bottom,
                Integer.parseInt(request.getParameter("quantity")));

        cupcakeList.add(cupcake);
        session.setAttribute("shoppingcart", cupcakeList);
        System.out.println("size: " + cupcakeList.size());
        System.out.println("name: " + cupcakeList.get(0).getBottom());
        return pageToShow;
    }
}
