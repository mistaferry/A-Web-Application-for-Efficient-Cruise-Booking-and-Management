package actions.customer;

import actions.Action;
import com.google.protobuf.ServiceException;
import dao.impl.MySqlCruiseDAO;
import dto.CruiseDTO;

import exceptions.DbException;
import services.GeneralService;
import services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ViewCruiseCatalogAction implements Action {
    private final GeneralService generalService;

    public ViewCruiseCatalogAction() {
        this.generalService = ServiceFactory.getInstance().getGeneralService();
    }

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        List<String> filters = new ArrayList<>();
        int pageNum = Integer.parseInt(request.getParameter("page"));
        int cruisePerPage = Integer.parseInt(request.getParameter("cruisePerPage"));
        getFiltersFromPage(request, filters);

        System.out.println(request.getQueryString());
        String path = null;
        List<CruiseDTO> cruiseDTOList = generalService.viewCatalogWithPagination(filters, cruisePerPage, pageNum);
        int pageAmount = 0;
        pageAmount = generalService.getCruiseAmount(filters);
        pageAmount /= cruisePerPage;
        System.out.println("pageAmount - " + pageAmount);
        request.setAttribute("pageAmount", pageAmount);
        session.setAttribute("allCruises", cruiseDTOList);
        path = "/catalog.jsp";
        return path;
    }

    private void getFiltersFromPage(HttpServletRequest request, List<String> filters) {
        String date = request.getParameter("startDay");
        filters.add(date);
        String duration = request.getParameter("duration");
        filters.add(duration);
    }
}
