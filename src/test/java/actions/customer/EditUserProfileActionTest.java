package actions.customer;

import actions.admin.AdminGetUserCruisesAction;
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

class EditUserProfileActionTest {
    private final GeneralService generalService = mock(GeneralService.class);
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpSession session = mock(HttpSession.class);
    private final ServiceFactory serviceFactory = mock(ServiceFactory.class);

    @Test
    void executeUnSuccessfully() throws ServiceException {
        setRequestValues();
        when(serviceFactory.getGeneralService()).thenReturn(generalService);
        doThrow(ServiceException.class).when(generalService).updateUser(isA(UserDTO.class));
        when(request.getSession().getAttribute("error")).thenReturn("login.uniqueness");
        when(request.getSession().getAttribute("prevUser")).thenReturn(userDTOTest());
        assertEquals("editProfile.jsp", new EditUserProfileAction().execute(request));
    }

    private void setRequestValues(){
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("user")).thenReturn(userDTOTest());
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