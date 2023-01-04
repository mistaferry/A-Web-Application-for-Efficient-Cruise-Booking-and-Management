package controller;

import actions.Action;
import actions.ActionFactory;
import com.google.protobuf.ServiceException;
import dto.UserDTO;
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
import java.util.List;
import java.util.StringJoiner;

@WebServlet("/controller")
//@WebServlet("/hello")
public class Controller extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    ActionFactory ACTION_FACTORY = ActionFactory.getActionFactory();
    private final GeneralService generalService = ServiceFactory.getInstance().getGeneralService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(process(req, resp));
    }

    public static String getActionToRedirect(String action, String... parameters) {
        String base = "controller" + "?" + "action" + "=" + action;
        StringJoiner stringJoiner = new StringJoiner("&", "&", "");
        for (int i = 0; i < parameters.length; i+=2) {
            stringJoiner.add(parameters[i] + "=" + parameters[i + 1]);
        }
        return base + (parameters.length > 0 ? stringJoiner : "");
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(process(req, resp));
    }

    private String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = ActionFactory.getAction(req.getParameter("action"));
        try{
            return action.execute(req);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            return "errorPage.jsp";
        }

    }
}
