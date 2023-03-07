package actions.customer;

import actions.Action;
import com.google.protobuf.ServiceException;
import dto.CruiseDTO;
import dto.UserDTO;

import services.GeneralService;
import services.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class GetOrderForm implements Action {
    private final GeneralService generalService;

    public GetOrderForm() {
        this.generalService = ServiceFactory.getInstance().getGeneralService();
    }

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
//        HttpSession session = request.getSession();
        long cruiseId = Long.parseLong(request.getParameter("cruise_id"));
        CruiseDTO cruise = generalService.getChosenCruise(cruiseId);
        request.setAttribute("orderCruise", cruise);
//        UserDTO user = (UserDTO) session.getAttribute("user");
//        long userId = user.getId();
//        generalService.addCruiseToUser(userId, cruiseId);
//        return "controller?action=view-user-cruises&page=0&cruisePerPage=5";
        return "orderForm.jsp";
    }
}
