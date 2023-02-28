package actions.customer;

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

import static dao.ShipDaoTest.getTestShip;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ViewUserCruisesActionTest {
    private final GeneralService generalService = mock(GeneralService.class);
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final ServiceFactory serviceFactory = mock(ServiceFactory.class);
    private final HttpSession session = mock(HttpSession.class);

    @Test
    void execute() throws ServiceException {
        setRequestValues();
        when(serviceFactory.getGeneralService()).thenReturn(generalService);
        when(generalService.viewUserOrdersWithPagination(userDTOTest().getId(), 2, 0)).thenReturn(orderDTOListTest());
        assertEquals("/customerCruises.jsp", new ViewUserCruisesAction().execute(request));
        assertEquals("0", request.getParameter("page"));
        assertEquals("2", request.getParameter("cruisePerPage"));
        assertEquals(userDTOTest(), request.getSession().getAttribute("user"));
    }

    private void setRequestValues(){
        when(request.getParameter("page")).thenReturn("0");
        when(request.getParameter("cruisePerPage")).thenReturn("2");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(userDTOTest());
        request.setAttribute("pageAmount", 1);
        request.getSession().setAttribute("myCruises", cruiseDTOListTest());
    }

    protected static List<CruiseDTO> cruiseDTOListTest(){
        List<CruiseDTO> cruiseDTOS = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            cruiseDTOS.add(CruiseDTO.builder()
                    .id(i)
                    .ship(getTestShip())
                    .number_of_register_people(1000)
                    .price(10000)
                    .startDate(null)
                    .duration(0)
                    .build());
        }
        return cruiseDTOS;
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
}