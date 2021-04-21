package web.commands;

import business.entities.User;
import business.exceptions.UserException;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditProfileCommand extends CommandUnprotectedPage {
    public EditProfileCommand(String pageToShow) {
        super(pageToShow);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        UserFacade userFacade = new UserFacade(database);
        User user = (User) request.getSession().getAttribute("user");
        //tjek requestscope
        String newEmail = request.getParameter("newemail");
        String newPassword = request.getParameter("newpassword");

        //opdatere user object
        if (newEmail != null) {
            user.setEmail(newEmail);
        }
        if (newPassword != null) {
            user.setPassword(newPassword);
        }
        //opdatere db
        user = userFacade.updateUser(user);
        
        //opdatere sessionscope
        request.getSession().setAttribute("user",user);
        return pageToShow;
    }
}
