package actions.general;

import actions.Action;
import com.google.protobuf.ServiceException;
import dto.UserDTO;

import model.entity.Role;
import services.ServiceFactory;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class ChangePasswordAction implements Action {
    private final UserService userService;

    public ChangePasswordAction() {
        this.userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        String login = request.getParameter("login");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        session.removeAttribute("error");
        try {
            userService.changePassword(login, oldPassword, newPassword, confirmPassword);
        } catch (ServiceException e) {
            session.setAttribute("error", "Input data is wrong");
        }
        return "index.jsp";
    }

}
