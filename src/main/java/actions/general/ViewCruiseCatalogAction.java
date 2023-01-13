package actions.general;

import actions.Action;
import com.google.protobuf.ServiceException;
import dao.impl.MySqlCruiseDAO;
import dto.CruiseDTO;
import exceptions.DAOException;
import services.GeneralService;
import services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewCruiseCatalogAction implements Action {
    private final GeneralService generalService;

    public ViewCruiseCatalogAction() {
        this.generalService = ServiceFactory.getInstance().getGeneralService();
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException, ServiceException {
        HttpSession session = request.getSession();
        List<String> filters = new ArrayList<>();
        int pageNum = Integer.parseInt(request.getParameter("page"));
        int cruisePerPage = Integer.parseInt(request.getParameter("cruisePerPage"));
        getFiltersFromPage(request, filters);

        System.out.println(request.getQueryString());
        String path = null;
        try {

            List<CruiseDTO> cruiseDTOList = generalService.viewCatalogWithPagination(filters, cruisePerPage, pageNum);
            int pageAmount = (new MySqlCruiseDAO()).getAmountWithFilters(filters);
            System.out.println("executed rows - " + pageAmount);
            pageAmount /= cruisePerPage;
            System.out.println("pageAmount - " + pageAmount);
            request.setAttribute("pageAmount", pageAmount);
            session.setAttribute("allCruises", cruiseDTOList);

            path = "/catalog.jsp";
        } catch (ServiceException e) {
            e.printStackTrace();
            path = "/errorPage.jsp";
        } catch (DAOException | SQLException e) {
            e.printStackTrace();
        }
        return path;
    }

    private void getFiltersFromPage(HttpServletRequest request, List<String> filters) {
        String date = request.getParameter("startDay");
        filters.add(date);
        String duration = request.getParameter("duration");
        filters.add(duration);
    }
}
