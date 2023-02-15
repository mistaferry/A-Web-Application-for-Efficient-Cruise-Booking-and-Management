package actions.customer;

import actions.Action;
import com.google.protobuf.ServiceException;
import dao.impl.MySqlCruiseDAO;
import dao.impl.MySqlOrderDAO;
import dto.CruiseDTO;
import dto.OrderDTO;
import dto.UserDTO;
import exceptions.DbException;
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
    public String execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        UserDTO getUserFromSession = (UserDTO) session.getAttribute("user");
        int pageNum = Integer.parseInt(request.getParameter("page"));
        int cruisePerPage = Integer.parseInt(request.getParameter("cruisePerPage"));
        long loggedUserId = getUserFromSession.getId();
//        System.out.println(request.getQueryString());
        String path = null;
        int pageAmount = 0;
        try {
            List<OrderDTO> orderDTOList = generalService.viewUserOrdersWithPagination(loggedUserId, cruisePerPage, pageNum);
            //!
            pageAmount = (new MySqlOrderDAO()).getAmountByUser(loggedUserId);
            pageAmount /= cruisePerPage;
            request.setAttribute("pageAmount", pageAmount);
            session.setAttribute("myCruises", orderDTOList);
            path = "/customerCruises.jsp";
        } catch (DbException e) {
            throw new ServiceException(e);
        }

        return path;
    }
}
