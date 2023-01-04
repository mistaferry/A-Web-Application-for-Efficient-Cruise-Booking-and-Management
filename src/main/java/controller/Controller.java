package controller;

import actions.Action;
import actions.ActionFactory;
import com.google.protobuf.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.GeneralService;
import services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(process(req, resp));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(process(req, resp));
    }

    private String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = ActionFactory.getAction(req.getParameter("action"));
        try {
            return action.execute(req);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            return "errorPage.jsp";
        }
    }
}
