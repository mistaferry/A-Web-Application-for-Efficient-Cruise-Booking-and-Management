package actions.general;

import actions.Action;
import com.google.protobuf.ServiceException;
import dto.UserDTO;
import exceptions.DAOException;
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
        long loggedUserId = ((UserDTO) session.getAttribute("user")).getId();
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        userService.changePassword(loggedUserId, oldPassword, newPassword, confirmPassword);
        return "profile.jsp";
    }

}
