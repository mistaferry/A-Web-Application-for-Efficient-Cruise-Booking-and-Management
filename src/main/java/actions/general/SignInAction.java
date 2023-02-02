package actions.general;

import actions.Action;
import controller.Controller;
import dto.UserDTO;

import model.entity.Role;
import org.slf4j.*;
import org.slf4j.event.Level;
import services.GeneralService;
import services.ServiceFactory;
import com.google.protobuf.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import java.io.IOException;

public class SignInAction implements Action {
    private final GeneralService generalService;
    private static final Logger logger = LoggerFactory.getLogger(SignInAction.class);

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
            logger.info(user.getLogin() + " entered the app");
            path = "profile.jsp";
        } catch (ServiceException e) {
            errorMessage = "There is no such user";
            session.setAttribute("error", errorMessage);
            return path;
        }
        return path;
    }
}
