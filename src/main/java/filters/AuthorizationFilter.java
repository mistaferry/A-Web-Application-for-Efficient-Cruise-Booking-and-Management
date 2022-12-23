//package filters;
//
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import model.entity.Role;
//
//import java.io.IOException;
//
//public class AuthorizationFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
////        HttpServletRequest request = (HttpServletRequest) servletRequest;
////        String role = (String) request.getSession().getAttribute("role");
////        if (role != null && isAccessDenied(request, role)) {
////            request.setAttribute(MESSAGE, ACCESS_DENIED);
////            request.getRequestDispatcher(SIGN_IN_PAGE).forward(request, servletResponse);
////        } else {
////            filterChain.doFilter(request, servletResponse);
////        }
//    }
//
////    private boolean isAccessDenied(HttpServletRequest request, String role) {
////        return (getDomain(request.getServletPath(), request.getParameter(ACTION), role).checkAccess());
////    }
//}
