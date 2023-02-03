package actions.customer;

import actions.Action;
import com.google.protobuf.ServiceException;
import dto.CruiseDTO;
import services.GeneralService;
import services.ServiceFactory;
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

        List<CruiseDTO> cruiseDTOList = generalService.viewCatalogWithPagination(filters, cruisePerPage, pageNum);
        int pageAmount = generalService.getCruiseAmount(filters);
        pageAmount /= cruisePerPage;
        request.setAttribute("pageAmount", pageAmount);
        session.setAttribute("allCruises", cruiseDTOList);
        return "/catalog.jsp";
    }

    private void getFiltersFromPage(HttpServletRequest request, List<String> filters) {
        String date = request.getParameter("startDay");
        filters.add(date);
        String duration = request.getParameter("duration");
        filters.add(duration);
    }
}
