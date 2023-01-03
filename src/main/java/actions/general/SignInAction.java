package actions.general;

import actions.Action;
import com.google.protobuf.ServiceException;
import dto.UserDTO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import services.GeneralService;
import services.ServiceFactory;

import java.io.IOException;

public class SignInAction implements Action {
    private final GeneralService generalService;

    public SignInAction() {
        this.generalService = ServiceFactory.getInstance().getGeneralService();
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException, ServiceException {
        String path = null;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        try{
            UserDTO user = generalService.signIn(login, password);
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("role", user.getRoleId());
            switch (user.getRoleId()) {
                case 1 -> path = "/pages/test.jsp";
                case 2 -> path = "/pages/test.jsp";
                case 3 -> path = "/pages/test.jsp";
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return path;
    }
}
