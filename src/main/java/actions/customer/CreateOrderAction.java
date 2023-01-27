package actions.customer;

import com.google.protobuf.ServiceException;
import dto.CruiseDTO;
import dto.UserDTO;

import services.GeneralService;
import services.ServiceFactory;
import services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class CreateOrderAction implements actions.Action {
    private final GeneralService generalService;

    public CreateOrderAction() {
        this.generalService = ServiceFactory.getInstance().getGeneralService();
    }

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        long cruiseId = Long.parseLong(request.getParameter("cruise_id"));
        UserDTO user = (UserDTO) session.getAttribute("user");
        long userId = user.getId();
       generalService.addCruiseToUser(userId, cruiseId);
//        return "controller?action=view-cruises&startDay=0&duration=0&page=0&cruisePerPage=5";
        return "controller?action=view-user-cruises&page=0&cruisePerPage=5";
    }
}
