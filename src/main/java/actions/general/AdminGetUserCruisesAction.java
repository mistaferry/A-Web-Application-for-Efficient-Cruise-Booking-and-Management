package actions.general;

import actions.Action;
import dao.impl.MySqlUserDAO;
import dto.CruiseDTO;
import dto.UserDTO;
import exceptions.DAOException;
import model.entity.User;
import services.GeneralService;
import services.ServiceFactory;
import utils.Convertor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class AdminGetUserCruisesAction implements Action {
    private final GeneralService generalService;

    public AdminGetUserCruisesAction() {
        this.generalService = ServiceFactory.getInstance().getGeneralService();
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        long userId = Long.parseLong(request.getParameter("user"));
        try {
            User user = (new MySqlUserDAO()).getById(userId).get();
            UserDTO userDTO = Convertor.convertUserToDTO(user);
            session.setAttribute("chosenUser", userDTO);
            int cruisePerPage = Integer.parseInt(request.getParameter("cruisePerPage"));
            int page = Integer.parseInt(request.getParameter("page"));
            List<CruiseDTO> cruiseDTOList = generalService.getCruisesByUser(userId, cruisePerPage, page);
            request.setAttribute("userCruises", cruiseDTOList);
            return "getUserCruises.jsp";
        }catch (DAOException | SQLException e){
            return "errorPage.jsp";
        }
    }
}
