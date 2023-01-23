package actions.general;

import actions.Action;
import dto.CruiseDTO;
import dto.UserDTO;
import exceptions.DAOException;
import services.GeneralService;
import services.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class ChangePaymentValueByAdminAction implements Action {
    private final GeneralService generalService;

    public ChangePaymentValueByAdminAction() {
        this.generalService = ServiceFactory.getInstance().getGeneralService();
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        //старі дані користувача
        long cruiseId = Long.parseLong(request.getParameter("cruise_id"));
        String path = null;
        long userId = ((UserDTO) session.getAttribute("chosenUser")).getId();
        try {
            CruiseDTO cruiseDTO = generalService.getChosenCruise(cruiseId);
            CruiseDTO cruise = getCruiseDTOValues(request, cruiseDTO);
            //нові дані, введені
            generalService.updateCruise(cruise);
            path = "controller?action=admin-get-user-cruises&user="+userId+"&cruisePerPage=7&page=0";
        } catch (DAOException | SQLException e) {
            path = "controller?action=admin-get-user-cruises&user="+userId+"cruisePerPage=7&page=0";
        }
        return path;
    }

    private CruiseDTO getCruiseDTOValues(HttpServletRequest request, CruiseDTO cruise) {
        return CruiseDTO.builder()
                .id(cruise.getId())
                .ship(cruise.getShip())
                .duration(cruise.getDuration())
                .price(cruise.getPrice())
                .startDate(cruise.getStartDate())
                .paid(Boolean.parseBoolean(request.getParameter("order_status")))
                .build();
    }
}
