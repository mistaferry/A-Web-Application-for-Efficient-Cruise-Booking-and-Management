package actions.general;

import dto.CruiseDTO;
import dto.UserDTO;
import exceptions.DAOException;
import services.ServiceFactory;
import services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class CreateOrderAction implements actions.Action {
    private final UserService userService;

    public CreateOrderAction() {
        this.userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        try {
            long cruiseId = Long.parseLong(request.getParameter("cruise_id"));
            CruiseDTO chosenCruise = userService.getChosenCruise(cruiseId);
            session.setAttribute("chosenCruise", chosenCruise);
            return "placeOrder.jsp";
        } catch (DAOException | SQLException e) {
            return "errorPage.jsp";
        }
    }
}
