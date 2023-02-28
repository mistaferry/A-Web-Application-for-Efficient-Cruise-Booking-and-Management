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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetUserInfoActionTest {
    private final GeneralService generalService = mock(GeneralService.class);
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpSession session = mock(HttpSession.class);
    private final ServiceFactory serviceFactory = mock(ServiceFactory.class);

    @Test
    void execute() throws ServiceException {
        setRequestValues();
        when(serviceFactory.getGeneralService()).thenReturn(generalService);
        when(generalService.getChosenUser(1L)).thenReturn(userDTOTest());
        assertEquals("userInfoPage.jsp", new GetUserInfoAction().execute(request));
        assertEquals("1", request.getParameter("user_id"));
    }

    private void setRequestValues(){
        when(request.getParameter("user_id")).thenReturn("1");
        when(request.getSession()).thenReturn(session);
        request.getSession().setAttribute("chosenUser", userDTOTest());
    }

    private UserDTO userDTOTest(){
        return UserDTO.builder()
                    .id(1L)
                    .firstName("name")
                    .surname("surname")
                    .roleId(1)
                    .login("login")
                    .password("password")
                    .blocked(true)
                    .build();
    }
}