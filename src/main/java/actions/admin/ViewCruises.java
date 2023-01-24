package actions.admin;

import actions.Action;
import com.google.protobuf.ServiceException;
import dao.impl.MySqlCruiseDAO;
import dto.CruiseDTO;
import exceptions.DAOException;
import exceptions.DbException;
import services.GeneralService;
import services.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewCruises implements Action {
    private final GeneralService generalService;

    public ViewCruises() {
        this.generalService = ServiceFactory.getInstance().getGeneralService();
    }

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        int pageNum = Integer.parseInt(request.getParameter("page"));
        int cruisePerPage = Integer.parseInt(request.getParameter("cruisePerPage"));
        String path = null;
        try {
            List<CruiseDTO> cruiseDTOList = generalService.viewCatalog(cruisePerPage, pageNum);
            int pageAmount = 0;
            pageAmount = (new MySqlCruiseDAO()).getAmount();
            pageAmount /= cruisePerPage;
            request.setAttribute("pageAmount", pageAmount);
            session.setAttribute("allCruises", cruiseDTOList);
            path = "/viewCatalogAdmin.jsp";
        } catch (DbException e) {
            throw new ServiceException(e);
        }
        return path;
    }
}
