package actions.customer;

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

public class EditUserProfileAction implements Action {
    private final UserService userService;

    public EditUserProfileAction() {
        this.userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        UserDTO getUserFromSession = (UserDTO) session.getAttribute("user");
        UserDTO user = getUserDTOValues(request, getUserFromSession);
        try {
            userService.updateUser(user);
            getUserFromSession.setFirstName(user.getFirstName());
            getUserFromSession.setSurname(user.getSurname());
            getUserFromSession.setLogin(user.getLogin());
        } catch (ServiceException e) {
            session.setAttribute("prevUser", getUserFromSession);
            session.setAttribute("error", "login.uniqueness");
            return "editProfile.jsp";
        }
        return "profile.jsp";
    }

    private UserDTO getUserDTOValues(HttpServletRequest request, UserDTO user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(request.getParameter("firstName"))
                .surname(request.getParameter("surname"))
                .login(request.getParameter("login"))
                .blocked(user.isBlocked())
                .roleId(user.getRoleId())
                .password(user.getPassword())
                .build();
    }
}
