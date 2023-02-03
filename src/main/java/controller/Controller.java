package controller;

import actions.Action;
import actions.ActionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = process(req);
        if(isAttributesPresent(req)){
            req.getRequestDispatcher(path).forward(req, resp);
        }else {
            resp.sendRedirect(process(req));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(process(req));
    }

    private String process(HttpServletRequest req) {
        Action action = ActionFactory.getAction(req.getParameter("action"));
        try {
            return action.execute(req);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "errorPage.jsp";
        }
    }

    private boolean isAttributesPresent (HttpServletRequest request) {
        return request.getAttributeNames().hasMoreElements();
    }
}
