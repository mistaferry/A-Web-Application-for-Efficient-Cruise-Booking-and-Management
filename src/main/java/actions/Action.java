package actions;

import com.google.protobuf.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


public interface Action {
    String execute(HttpServletRequest request) throws ServletException, IOException, ServiceException;
}