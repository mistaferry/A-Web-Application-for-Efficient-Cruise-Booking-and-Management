package actions.admin;

import com.google.protobuf.ServiceException;
import dto.UserDTO;
import org.junit.jupiter.api.Test;
import services.GeneralService;
import services.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ViewAllUsersActionTest {
    private final GeneralService generalService = mock(GeneralService.class);
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final ServiceFactory serviceFactory = mock(ServiceFactory.class);
    private final HttpSession session = mock(HttpSession.class);

    @Test
    void execute() throws ServiceException {
        setRequestValues();
        when(serviceFactory.getGeneralService()).thenReturn(generalService);
        when(generalService.viewAllUsersWithPagination(2, 0)).thenReturn(userDTOListTest());
        when(generalService.getUserAmount()).thenReturn(2);
        assertEquals("/mainAdminPage.jsp", new ViewAllUsersAction().execute(request));
        assertEquals("0", request.getParameter("page"));
        assertEquals("2", request.getParameter("userPerPage"));
    }

    private void setRequestValues(){
        when(request.getParameter("page")).thenReturn("0");
        when(request.getParameter("userPerPage")).thenReturn("2");
        request.setAttribute("pageAmount", 2);
        when(request.getSession()).thenReturn(session);
        request.getSession().setAttribute("allUsers", userDTOListTest());
    }

    private List<UserDTO> userDTOListTest(){
        List<UserDTO> userDTOS = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            userDTOS.add(UserDTO.builder()
                    .id(i)
                    .firstName("name")
                    .surname("surname")
                    .roleId(1)
                    .login("login")
                    .password("password")
                    .blocked(true)
                    .build());
        }
        return userDTOS;
    }
}