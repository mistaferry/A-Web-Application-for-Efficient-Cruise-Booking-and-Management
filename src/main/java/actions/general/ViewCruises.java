package actions.general;

import actions.Action;
import com.google.protobuf.ServiceException;
import dto.CruiseDTO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import services.GeneralService;
import services.ServiceFactory;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewCruises implements Action {
    private final GeneralService generalService;

    public ViewCruises() {
        this.generalService = ServiceFactory.getInstance().getGeneralService();
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException, ServiceException {
        HttpSession session = request.getSession();
        String path = null;
        List<String> filters = new ArrayList<>();
        try {
            getFiltersFromPage(request, filters);
            List<CruiseDTO> cruiseDTOList = generalService.viewCatalog(filters);
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
