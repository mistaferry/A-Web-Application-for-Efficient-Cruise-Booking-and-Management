package actions.customer;

import com.google.protobuf.ServiceException;
import dto.CruiseDTO;
import dto.UserDTO;

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
    public String execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        long cruiseId = Long.parseLong(request.getParameter("cruise_id"));
        CruiseDTO chosenCruise = userService.getChosenCruise(cruiseId);
        session.setAttribute("chosenCruise", chosenCruise);
        return "placeOrder.jsp";
    }
}
