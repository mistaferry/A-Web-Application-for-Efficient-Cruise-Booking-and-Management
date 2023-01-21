package actions.general;

import actions.Action;
import com.google.protobuf.ServiceException;
import dao.impl.MySqlCruiseDAO;
import dao.impl.MySqlUserDAO;
import dto.CruiseDTO;
import dto.UserDTO;
import exceptions.DAOException;
import services.GeneralService;
import services.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewAllUsersAction implements Action {
    private final GeneralService generalService;

    public ViewAllUsersAction() {
        this.generalService = ServiceFactory.getInstance().getGeneralService();
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        int pageNum = Integer.parseInt(request.getParameter("page"));
        int cruisePerPage = Integer.parseInt(request.getParameter("userPerPage"));
        System.out.println(request.getQueryString());
        String path = null;
        try {
            List<UserDTO> userDTOList = generalService.viewAllUsersWithPagination(cruisePerPage, pageNum);
            int pageAmount = (new MySqlUserDAO()).getAmount();
            pageAmount /= cruisePerPage;
            request.setAttribute("pageAmount", pageAmount);
            session.setAttribute("allUsers", userDTOList);
            path = "/mainAdminPage.jsp";
        } catch (ServiceException e) {
            e.printStackTrace();
            path = "/errorPage.jsp";
        }
        return path;
    }
}
