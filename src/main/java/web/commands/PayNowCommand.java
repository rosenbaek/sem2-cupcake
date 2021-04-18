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

public class PayNowCommand extends CommandProtectedPage{
    public PayNowCommand(String pageToShow, String role) {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)  {
        HttpSession session = request.getSession();
        OrderFacade orderFacade = new OrderFacade(database);
        UserFacade userFacade = new UserFacade(database);

        //Check if balance is more than total price or equals
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingcart");
        User user = (User) session.getAttribute("user");

        //Reset shoppingcart and reset link session attribute for next payment
        session.setAttribute("shoppingcart",new ShoppingCart());
        session.removeAttribute("link");

        float orderPrice = shoppingCart.getTotalPrice();
        float userBalance = user.getBalance();

        if (userBalance >= orderPrice){
            //Create order
            Order order = new Order(orderPrice,"betalt", user.getId());

            //write order to order and order_details table
            try {
                orderFacade.insertOrder(order, shoppingCart);

                //Deduct user the credits used
                userFacade.deductUserBalance(user,orderPrice);

                //Update the user in session scope so balance is up to date
                session.setAttribute("user",userFacade.login(user.getEmail(),user.getPassword()));


                //Smid hen på ordre historik side hvor der tilføjes "din ordre er gået igennem" hvis man kommer herfra.
                request.setAttribute("status","Success! The order is added!");
                return "userorderhistory";
            } catch (UserException e) {
                //Something went wrong when inserting to DB!
                request.setAttribute("error", e.getMessage());
                return "paymentpage";
            }
        }
        else{
            //Give error message that you have insufficient credits
            request.setAttribute("error", "Insufficient credits! Please contact shop owner and add credits!");
            return "paymentpage";
        }
    }
}
