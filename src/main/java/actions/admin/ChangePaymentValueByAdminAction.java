package actions.admin;

import actions.Action;
import com.google.protobuf.ServiceException;
import dto.CruiseDTO;
import dto.UserDTO;
import services.GeneralService;
import services.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.SQLException;

public class ChangePaymentValueByAdminAction implements Action {
    private final GeneralService generalService;

    public ChangePaymentValueByAdminAction() {
        this.generalService = ServiceFactory.getInstance().getGeneralService();
    }

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        //старі дані користувача
        long cruiseId = Long.parseLong(request.getParameter("order_cruise_id"));
        long userId = ((UserDTO)session.getAttribute("chosenUser")).getId();
        Date date = Date.valueOf((request.getParameter("date")));
        String path = null;

        System.out.println("user_id - " + userId);
        System.out.println("cruise_id - " + cruiseId);
        System.out.println("date - " + date);
        //нові дані, введені
        generalService.updateOrderPaymentStatus(userId, cruiseId, date);
        path = "controller?action=admin-get-user-cruises&user="+userId+"&cruisePerPage=7&page=0";
        return path;
    }

    private CruiseDTO getCruiseDTOValues(HttpServletRequest request, CruiseDTO cruise) {
        return CruiseDTO.builder()
                .id(cruise.getId())
                .ship(cruise.getShip())
                .duration(cruise.getDuration())
                .price(cruise.getPrice())
                .startDate(cruise.getStartDate())
                .number_of_register_people(cruise.getNumber_of_register_people())
                .build();
    }
}
