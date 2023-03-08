package actions.customer;

import actions.Action;
import com.google.protobuf.ServiceException;
import dto.CruiseDTO;
import dto.UserDTO;
import services.GeneralService;
import services.ServiceFactory;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class PlaceOrder implements Action {
    private final GeneralService generalService;

    public PlaceOrder() {
        this.generalService = ServiceFactory.getInstance().getGeneralService();
    }


    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        InputStream inputStream = null;
        Part filePart = null;
        try {
            filePart = request.getPart("copyDocument");
            System.out.println("sdc");
            if (filePart != null) {
                System.out.println(filePart.getInputStream());
                inputStream = filePart.getInputStream();
            }else{
                return "errorPage.jsp";
            }
            long cruiseId = Long.parseLong(request.getParameter("cruise_id"));
            UserDTO user = (UserDTO) request.getSession().getAttribute("user");
            long userId = user.getId();
            generalService.addDocumentsToUser(userId, inputStream);
            generalService.addCruiseToUser(userId, cruiseId);
        } catch (IOException | ServletException e) {
            throw new ServiceException(e);
        }
        return "controller?action=view-user-cruises&page=0&cruisePerPage=5";
    }
}
