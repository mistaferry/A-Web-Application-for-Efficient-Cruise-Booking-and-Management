package actions.admin;

import actions.Action;
import com.google.protobuf.ServiceException;
import dao.impl.MySqlUserDAO;
import dto.OrderDTO;
import dto.UserDTO;
import exceptions.DbException;
import model.User;
import services.GeneralService;
import services.ServiceFactory;
import utils.Convertor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminGetUserCruisesAction implements Action {
    private final GeneralService generalService;

    public AdminGetUserCruisesAction() {
        this.generalService = ServiceFactory.getInstance().getGeneralService();
    }

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        long userId = Long.parseLong(request.getParameter("user"));
        User user = null;
        try {
            user = (new MySqlUserDAO()).getById(userId).get();
        } catch (DbException e) {
            throw new ServiceException(e);
        }
        UserDTO userDTO = Convertor.convertUserToDTO(user);
        session.setAttribute("chosenUser", userDTO);
        int cruisePerPage = Integer.parseInt(request.getParameter("cruisePerPage"));
        int page = Integer.parseInt(request.getParameter("page"));
        List<OrderDTO> orderDTOList = generalService.getOrdersByUserAdmin(userId, cruisePerPage, page);
        request.setAttribute("userCruises", orderDTOList);
        return "getUserCruises.jsp";

    }
}
