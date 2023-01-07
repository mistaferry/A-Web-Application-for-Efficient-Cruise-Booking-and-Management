package actions.general;

import actions.Action;
import com.google.protobuf.ServiceException;
import dto.CruiseDTO;
import dto.UserDTO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import services.GeneralService;
import services.ServiceFactory;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class SignInAction implements Action {
    private final GeneralService generalService;

    public SignInAction() {
        this.generalService = ServiceFactory.getInstance().getGeneralService();
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException, ServiceException {
        HttpSession session = request.getSession();
        String path = "/index.jsp";
        String errorMessage;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login == null || password == null || login.isEmpty() || password.isEmpty()){
            errorMessage = "Login or password can't be empty";
            request.setAttribute("error", errorMessage);
            return path;
        }
        try{
            UserDTO user = generalService.signIn(login, password);
            if (user == null){
                errorMessage = "There is no such user";
                request.setAttribute("error", errorMessage);
                return path;
            }
            request.setAttribute("user", user);
            request.setAttribute("role", user.getRoleId());
            path = "controller?action=catalog";
        } catch (ServiceException e) {
            return "/pages/errorPage.jsp";
        }
        return path;
    }
}
