package actions.general;

import actions.Action;
import com.google.protobuf.ServiceException;
import dao.UserDao;
import dao.impl.MySqlUserDAO;
import dto.UserDTO;
import exceptions.DAOException;
import model.entity.Role;
import model.entity.User;
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
            User user = (new MySqlUserDAO()).getByEmail(login, password).get();
            UserDTO userDTO = Convertor.convertUserToDTO(user);
            session.setAttribute("user", userDTO);
            Role loggedUserRole = Role.getRoleByRoleId(user.getRoleId());
            session.setAttribute("role", loggedUserRole);
            path = "profile.jsp";
        } catch (DAOException | ServiceException e) {
            errorMessage = "login.uniqueness";
            session.setAttribute("error", errorMessage);
            return path;
        }
        return path;
    }
}
