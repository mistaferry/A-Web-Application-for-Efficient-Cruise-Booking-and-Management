package actions.general;

import actions.Action;
import dao.impl.MySqlUserDAO;
import dto.UserDTO;
import exceptions.DAOException;
import model.entity.Role;
import model.entity.User;
import services.ServiceFactory;
import services.UserService;
import utils.Convertor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class ChangeUserValuesByAdminAction implements Action {
    private final UserService userService;

    public ChangeUserValuesByAdminAction() {
        this.userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        //старі дані користувача
        UserDTO getChosenUserFromSession = (UserDTO) session.getAttribute("chosenUser");
        //нові дані, введені
        UserDTO user = getUserDTOValues(request, getChosenUserFromSession);
        try {
            userService.updateUser(user);
            getChosenUserFromSession.setBlocked(user.isBlocked());
            getChosenUserFromSession.setRoleId(user.getRoleId());
        } catch (DAOException | SQLException e) {
            session.setAttribute("prevChosenUser", getChosenUserFromSession);
        }
        return "controller?action=view-all-users&page=0&userPerPage=7";
    }

    private UserDTO getUserDTOValues(HttpServletRequest request, UserDTO user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .surname(user.getSurname())
                .login(user.getLogin())
                .blocked(Boolean.parseBoolean(request.getParameter("account_status")))
                .roleId(Integer.parseInt(request.getParameter("role")))
                .password(user.getPassword())
                .build();
    }
}
