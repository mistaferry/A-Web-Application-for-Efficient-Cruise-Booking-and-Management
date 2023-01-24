//package filters;
//
//import com.epam.serdyukov.ispmanager.controller.Path;
//import com.epam.serdyukov.ispmanager.model.entity.Role;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.StringTokenizer;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import org.apache.log4j.Logger;
//
//
///**
// * Main security filter.
// *
// * @author Aleksey Serdyukov
// */
//public class SecurityFilter implements Filter {
//    private static final Logger log = Logger.getLogger(SecurityFilter.class);
//
//    // commands access
//    private static Map<Role, List<String>> accessMap = new HashMap<>();
//    private static List<String> commons = new ArrayList<>();
//    private static List<String> outOfControl = new ArrayList<>();
//
//    @Override
//    public void init(FilterConfig config) {
//        log.debug("Filter initialization starts");
//
//        // roles
//        accessMap.put(Role.ADMIN, asList(config.getInitParameter("admin")));
//        accessMap.put(Role.CLIENT, asList(config.getInitParameter("client")));
//        log.trace("Access map --> " + accessMap);
//
//        // commons
//        commons = asList(config.getInitParameter("common"));
//        log.trace("Common commands --> " + commons);
//
//        // out of control
//        outOfControl = asList(config.getInitParameter("out-of-control"));
//        log.trace("Out of control commands --> " + outOfControl);
//
//        log.debug("Filter initialization finished");
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response,
//                         FilterChain chain) throws IOException, ServletException {
//        log.debug("Filter starts");
//        if (accessAllowed(request)) {
//            log.debug("Filter finished");
//            chain.doFilter(request, response);
//        } else {
//            String errorMessages = "You do not have permission to access the requested resource";
//            request.setAttribute("errorMessage", errorMessages);
//
//            log.trace("Set the request attribute: errorMessage --> " + errorMessages);
//
//            request.getRequestDispatcher(Path.PAGE_ERROR_PAGE).forward(request, response);
//        }
//    }
//
//    private boolean accessAllowed(ServletRequest request) {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//
//        String commandName = request.getParameter("action");
//        if (commandName == null || commandName.isEmpty()) {
//            return false;
//        }
//
//        if (outOfControl.contains(commandName)) {
//            return true;
//        }
//
//        HttpSession session = httpRequest.getSession(false);
//        if (session == null) {
//            return false;
//        }
//
//        Role userRole = (Role) session.getAttribute("userRole");
//        if (userRole == null) {
//            return false;
//        }
//
//        return accessMap.get(userRole).contains(commandName) || commons.contains(commandName);
//    }
//
//    @Override
//    public void destroy() {
//        log.debug("Filter destruction starts");
//        // do nothing
//        log.debug("Filter destruction finished");
//    }
//
//    private List<String> asList(String param) {
//        List<String> list = new ArrayList<>();
//        StringTokenizer st = new StringTokenizer(param);
//        while (st.hasMoreTokens()) {
//            list.add(st.nextToken());
//        }
//        return list;
//    }
//}
//
