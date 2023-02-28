package actions.general;

import dto.OrderDTO;
import dto.UserDTO;
import model.Cruise;
import model.Ship;
import org.junit.jupiter.api.Test;
import services.GeneralService;
import services.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RegisterActionTest {
    private final GeneralService generalService = mock(GeneralService.class);
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpSession session = mock(HttpSession.class);
    private final ServiceFactory serviceFactory = mock(ServiceFactory.class);

    @Test
    void execute() {
        setRequestValues();
        when(serviceFactory.getGeneralService()).thenReturn(generalService);
        assertDoesNotThrow(() -> generalService.register(
                request.getParameter("login"),
                request.getParameter("password"),
                request.getParameter("firstName"),
                request.getParameter("surname")
        ));
        assertEquals("profile.jsp", new RegisterAction().execute(request));
    }

    private void setRequestValues(){
        when(request.getParameter("login")).thenReturn(userDTOTest().getLogin());
        when(request.getParameter("password")).thenReturn(userDTOTest().getPassword());
        when(request.getParameter("firstName")).thenReturn(userDTOTest().getFirstName());
        when(request.getParameter("surname")).thenReturn(userDTOTest().getSurname());
        when(request.getSession()).thenReturn(session);
        request.getSession().setAttribute("user", userDTOTest());
        request.getSession().setAttribute("role", userDTOTest().getRoleId());
    }

    private List<OrderDTO> orderDTOListTest(){
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            orderDTOS.add(OrderDTO.builder()
                    .userId(1L)
                    .dateOfRegistration(Date.valueOf("2022-01-02"))
                    .paid(true)
                    .cruise(new Cruise(1L, new Ship(), 10, 12000, Date.valueOf("2022-09-22"), 1000))
                    .build());
        }
        return orderDTOS;
    }

    private UserDTO userDTOTest(){
        return UserDTO.builder()
                .id(1L)
                .firstName("name")
                .surname("surname")
                .roleId(1)
                .login("log")
                .password("password")
                .blocked(true)
                .build();
    }
}