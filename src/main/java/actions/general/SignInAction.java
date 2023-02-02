package actions.general;

import actions.Action;
import dto.UserDTO;

import model.entity.Role;
import services.GeneralService;
import services.ServiceFactory;
import com.google.protobuf.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import java.io.IOException;

public class SignInAction implements Action {
    private final GeneralService generalService;

    public SignInAction() {
        this.generalService = ServiceFactory.getInstance().getGeneralService();
    }

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        String path = "/index.jsp";
        String errorMessage;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        session.removeAttribute("error");
        try {
            UserDTO user = generalService.signIn(login, password);
            session.setAttribute("user", user);
            Role loggedUserRole = Role.getRoleByRoleId(user.getRoleId());
            session.setAttribute("role", loggedUserRole);
            System.out.println("Role - " + loggedUserRole.name());
            path = "profile.jsp";
        } catch (ServiceException e) {
            errorMessage = "There is no such user";
            session.setAttribute("error", errorMessage);
            return path;
        }
        return path;
    }
}
