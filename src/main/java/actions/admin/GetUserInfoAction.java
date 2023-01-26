package actions.admin;

import actions.Action;
import com.google.protobuf.ServiceException;
import dto.CruiseDTO;
import dto.UserDTO;
import services.GeneralService;
import services.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class GetUserInfoAction implements Action {
    private final GeneralService generalService;

    public GetUserInfoAction() {
        this.generalService = ServiceFactory.getInstance().getGeneralService();
    }

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        long userId = Long.parseLong(request.getParameter("user_id"));
        UserDTO chosenUser = generalService.getChosenUser(userId);
        session.setAttribute("chosenUser", chosenUser);
        return "userInfoPage.jsp";
    }
}
