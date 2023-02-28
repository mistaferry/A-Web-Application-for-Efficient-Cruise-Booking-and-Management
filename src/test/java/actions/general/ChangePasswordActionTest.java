package actions.general;

import com.google.protobuf.ServiceException;
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
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

class ChangePasswordActionTest {
    private final GeneralService generalService = mock(GeneralService.class);
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpSession session = mock(HttpSession.class);
    private final ServiceFactory serviceFactory = mock(ServiceFactory.class);

    @Test
    void execute() throws ServiceException {
        setRequestValues();
        when(serviceFactory.getGeneralService()).thenReturn(generalService);
        assertDoesNotThrow(() -> generalService.changePassword(
                request.getParameter("login"),
                request.getParameter("oldPassword"),
                request.getParameter("newPassword"),
                request.getParameter("confirmPassword")
        ));
        assertNull(request.getSession().getAttribute("error"));
        assertEquals("index.jsp", new ChangePasswordAction().execute(request));
    }

    private void setRequestValues(){
        when(request.getParameter("login")).thenReturn(userDTOTest().getLogin());
        when(request.getParameter("oldPassword")).thenReturn(userDTOTest().getPassword());
        when(request.getParameter("newPassword")).thenReturn("newPassword");
        when(request.getParameter("confirmPassword")).thenReturn("newPassword");
        when(request.getSession()).thenReturn(session);
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
                .login("login")
                .password("password")
                .blocked(true)
                .build();
    }
}