package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

import model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(SecurityFilter.class);
    private static Map<Role, List<String>> accessMap = new HashMap<>();
    private static List<String> outOfControl = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("Filter initialization starts");

        accessMap.put(Role.ADMIN, asList(filterConfig.getInitParameter("admin")));
        accessMap.put(Role.CUSTOMER, asList(filterConfig.getInitParameter("customer")));
        log.trace("Access map --> " + accessMap);


        outOfControl = asList(filterConfig.getInitParameter("out-of-control"));
        log.trace("Out of control --> " + outOfControl);

        log.debug("Filter initialization finished");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.debug("Filter is started");

        if(accessAllowed(servletRequest)){
            log.debug("Filter is finished");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String error = "You don't have permission for accessing this resource!";
            log.error(error);

            log.trace("Set the request attribute: error --> " + error);

            servletRequest.getRequestDispatcher("errorPage.jsp").forward(servletRequest, servletResponse);
        }
    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String commandName = request.getParameter("action");
        if (commandName == null || commandName.isEmpty()) {
            return false;
        }

        if (outOfControl.contains(commandName)) {
            return true;
        }

        HttpSession session = httpRequest.getSession(false);
        if (session == null) {
            return false;
        }

        Role userRole = (Role) session.getAttribute("role");
        if (userRole == null) {
            return false;
        }

        return accessMap.get(userRole).contains(commandName);
    }

    @Override
    public void destroy() {
        log.debug("Filter destruction starts");

        log.debug("Filter destruction finished");
    }

    private List<String> asList(String param) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(param);
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }
}
