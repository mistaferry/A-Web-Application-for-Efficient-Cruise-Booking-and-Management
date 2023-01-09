package filters;

import model.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Redirect access to / or /catalog to /catalog page with parameters
 */
public abstract class CatalogFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String duration = req.getParameter("duration");
//        String startDay = req.getParameter("startDay");
//        User user = (User) req.getSession().getAttribute("user");
//        if (user != null && user.getRoleId() == 2) {
//            res.sendRedirect(req.getContextPath() + "/users");
//            return;
//        }
        if (duration == null) {
            res.sendRedirect(req.getContextPath() + "/controller?action=view-cruises&duration=All&startDay=All&page=0&cruisePerPage=5");
        } else {
            chain.doFilter(req, res);
        }
    }
}
