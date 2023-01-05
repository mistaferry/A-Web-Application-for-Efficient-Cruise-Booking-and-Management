package actions.general;

import actions.Action;
import com.google.protobuf.ServiceException;
import dao.UserDao;
import dto.CruiseDTO;
import dto.UserDTO;
import model.entity.User;
import services.GeneralService;
import services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utils.Convertor.convertUserToDTO;

public class ViewCatalogAction implements Action {
    private final GeneralService generalService;

    public ViewCatalogAction() {
        this.generalService = ServiceFactory.getInstance().getGeneralService();
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException, ServiceException {
        HttpSession session = request.getSession();
        String path = null;
        try{
            List<CruiseDTO> cruiseDTOList = generalService.viewCruiseCatalog();
            session.setAttribute("allCruises", cruiseDTOList);
            path = "/test1.jsp";
        } catch (ServiceException e) {
            e.printStackTrace();
            path = "/pages/errorPage.jsp";
        }
        return path;
    }
}
