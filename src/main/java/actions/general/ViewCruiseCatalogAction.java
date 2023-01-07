package actions.general;

import actions.Action;
import com.google.protobuf.ServiceException;
import dto.CruiseDTO;
import services.GeneralService;
import services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
        int cruisePerPage = Integer.parseInt(request.getParameter("cruisePerPage"));
        int pageNum = Integer.parseInt(request.getParameter("page"));
        List<String> filters = new ArrayList<>();
        String path = null;
        try{
            getFiltersFromPage(request, filters);
            List<CruiseDTO> cruiseDTOList = generalService.viewCatalogWithPagination(filters, cruisePerPage, pageNum);

            int pageAmount = cruiseDTOList.size() / cruisePerPage;

            session.setAttribute("pageAmount", pageAmount);
            session.setAttribute("allCruises", cruiseDTOList);
            path = "/catalog.jsp";
        } catch (ServiceException e) {
            e.printStackTrace();
            path = "/pages/errorPage.jsp";
        }
        return path;
    }

    private void getFiltersFromPage(HttpServletRequest request, List<String> filters) {
        String date = (request.getParameter("startDay"));
        filters.add(date);
        String duration = String.valueOf(request.getParameter("duration"));
        if(!duration.equals("All")){
            String[] twoDurations = duration.split("-");
            filters.add(twoDurations[0]);
            filters.add(twoDurations[1]);
        }else{
            filters.add(duration);
        }
    }
}
