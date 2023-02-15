package actions.general;

import actions.Action;
import com.google.protobuf.ServiceException;
import dao.impl.MySqlUserDAO;
import dto.UserDTO;

import exceptions.DbException;
import model.Role;
import model.User;
import services.GeneralService;
import services.ServiceFactory;
import utils.Convertor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegisterAction implements Action {
    private final GeneralService generalService;

    public RegisterAction() {
        this.generalService = ServiceFactory.getInstance().getGeneralService();
    }

    @Override
    public String execute(HttpServletRequest request){
        HttpSession session = request.getSession();
        String path = "/register.jsp";
        String errorMessage;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String surname = request.getParameter("surname");
        try {
            generalService.register(login, password, firstName, surname);
            User user = null;
            try {
                user = (new MySqlUserDAO()).getByEmail(login, password).get();
            } catch (DbException e) {
                throw new ServiceException(e);
            }
            UserDTO userDTO = Convertor.convertUserToDTO(user);
            session.setAttribute("user", userDTO);
            Role loggedUserRole = Role.getRoleByRoleId(user.getRoleId());
            session.setAttribute("role", loggedUserRole);
            path = "profile.jsp";
        } catch (ServiceException e) {
            errorMessage = "login.uniqueness";
            session.setAttribute("error", errorMessage);
            return path;
        }
        return path;
    }
}
