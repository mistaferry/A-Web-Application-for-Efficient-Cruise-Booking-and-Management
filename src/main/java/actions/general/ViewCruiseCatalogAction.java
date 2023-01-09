package actions.general;

import actions.Action;
import com.google.protobuf.ServiceException;
import dao.impl.MySqlCruiseDAO;
import dto.CruiseDTO;
import services.GeneralService;
import services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewCruiseCatalogAction implements Action {
    private final GeneralService generalService;

    public ViewCruiseCatalogAction() {
        this.generalService = ServiceFactory.getInstance().getGeneralService();
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException, ServiceException {
        List<String> filters = new ArrayList<>();
//        int pageNum = Integer.parseInt(request.getParameter("page"));
//        int cruisePerPage = Integer.parseInt(request.getParameter("cruisePerPage"));
        getFiltersFromPage(request, filters);
        HttpSession session = request.getSession();

        System.out.println(request.getQueryString());
        String path = null;
        try{

            List<CruiseDTO> cruiseDTOList = generalService.viewCatalogWithPagination(filters, 0, 0);
//            int pageAmount = MySqlCruiseDAO.getExecutedRows();
//            pageAmount /= cruisePerPage;

//            request.setAttribute("pageAmount", pageAmount);
            request.setAttribute("allCruises", cruiseDTOList);

            path = "/catalog.jsp";
        } catch (ServiceException e) {
            e.printStackTrace();
            path = "/errorPage.jsp";
        }
        return path;
    }

    private void getFiltersFromPage(HttpServletRequest request, List<String> filters) {
        String date = request.getParameter("startDay");
        System.out.println("startDay - " + date);
        filters.add(date);
        String duration = request.getParameter("duration");
        System.out.println("duration - " + duration);
        if(!duration.equals("All")){
            String[] twoDurations = duration.split("-");
            filters.add(twoDurations[0]);
            filters.add(twoDurations[1]);
        }else{
            filters.add(duration);
        }
    }
}
