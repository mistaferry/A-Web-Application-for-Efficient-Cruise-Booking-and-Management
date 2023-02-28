package actions.admin;

import com.google.protobuf.ServiceException;
import dto.CruiseDTO;
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

import static actions.admin.ViewCruisesTest.cruiseDTOListTest;
import static dao.ShipDaoTest.getTestShip;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AdminGetUserCruisesActionTest {
    private final GeneralService generalService = mock(GeneralService.class);
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpSession session = mock(HttpSession.class);
    private final ServiceFactory serviceFactory = mock(ServiceFactory.class);

    @Test
    void execute() throws ServiceException {
        setRequestValues();
        when(serviceFactory.getGeneralService()).thenReturn(generalService);
        when(generalService.getOrdersByUserAdmin(1L, 2, 0)).thenReturn(orderDTOListTest());
        assertEquals("getUserCruises.jsp", new AdminGetUserCruisesAction().execute(request));
        assertEquals("1", request.getParameter("user"));
        assertEquals("2", request.getParameter("cruisePerPage"));
        assertEquals("0", request.getParameter("page"));
    }

    private void setRequestValues(){
        when(request.getParameter("user")).thenReturn("1");
        when(request.getParameter("cruisePerPage")).thenReturn("2");
        when(request.getParameter("page")).thenReturn("0");
        request.setAttribute("userCruises", orderDTOListTest());
        when(request.getSession()).thenReturn(session);
        request.getSession().setAttribute("chosenUser", userDTOTest());
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