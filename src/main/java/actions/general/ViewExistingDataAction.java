package actions.general;

import actions.Action;
import dto.CruiseDTO;
import dto.ShipDTO;
import exceptions.DAOException;
import services.GeneralService;
import services.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class ViewExistingDataAction implements Action {
    private final GeneralService generalService;

    public ViewExistingDataAction() {
        this.generalService = ServiceFactory.getInstance().getGeneralService();
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        List<ShipDTO> shipDTOList = generalService.getAllShips();
        session.setAttribute("ships", shipDTOList);
        return "addNewCruise.jsp";
    }
}
