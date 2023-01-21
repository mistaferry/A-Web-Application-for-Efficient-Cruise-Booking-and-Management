package actions.general;

import actions.Action;
import com.google.protobuf.ServiceException;
import dao.impl.MySqlCruiseDAO;
import dto.CruiseDTO;
import dto.UserDTO;
import services.GeneralService;
import services.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ViewUserCruisesAction implements Action {
    private final GeneralService generalService;

    public ViewUserCruisesAction() {
        this.generalService = ServiceFactory.getInstance().getGeneralService();
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        UserDTO getUserFromSession = (UserDTO) session.getAttribute("user");
        int pageNum = Integer.parseInt(request.getParameter("page"));
        int cruisePerPage = Integer.parseInt(request.getParameter("cruisePerPage"));
        long loggedUserId = getUserFromSession.getId();
        System.out.println(request.getQueryString());
        String path = null;
        try {
            List<CruiseDTO> cruiseDTOList = generalService.viewUserCruisesWithPagination(loggedUserId, cruisePerPage, pageNum);
            int pageAmount = (new MySqlCruiseDAO()).getAmountByUser(loggedUserId);
            pageAmount /= cruisePerPage;
            request.setAttribute("pageAmount", pageAmount);
            session.setAttribute("myCruises", cruiseDTOList);
            path = "/customerCruises.jsp";
        } catch (ServiceException e) {
            e.printStackTrace();
            path = "/errorPage.jsp";
        }
        return path;
    }
}
