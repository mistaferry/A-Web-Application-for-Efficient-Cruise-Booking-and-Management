package actions.admin;

import com.google.protobuf.ServiceException;
import dto.CruiseDTO;
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

class ViewCruisesTest {
    private final GeneralService generalService = mock(GeneralService.class);
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final ServiceFactory serviceFactory = mock(ServiceFactory.class);
    private final HttpSession session = mock(HttpSession.class);

    @Test
    void execute() throws ServiceException {setRequestValues();
        when(serviceFactory.getGeneralService()).thenReturn(generalService);
        when(generalService.viewCatalog(2, 0)).thenReturn(cruiseDTOListTest());
        when(generalService.getUserAmount()).thenReturn(2);
        assertEquals("/viewCatalogAdmin.jsp", new ViewCruises().execute(request));
        assertEquals("0", request.getParameter("page"));
        assertEquals("2", request.getParameter("cruisePerPage"));
    }

    private void setRequestValues(){
        when(request.getParameter("page")).thenReturn("0");
        when(request.getParameter("cruisePerPage")).thenReturn("2");
        request.setAttribute("pageAmount", 2);
        when(request.getSession()).thenReturn(session);
        request.getSession().setAttribute("allCruises", cruiseDTOListTest());
    }

    protected static List<CruiseDTO> cruiseDTOListTest(){
        List<CruiseDTO> cruiseDTOS = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            cruiseDTOS.add(CruiseDTO.builder()
                    .id(i)
                    .ship(getTestShip())
                    .number_of_register_people(1000)
                    .price(10000)
                    .startDate(Date.valueOf("2020-01-30"))
                    .duration(10)
                    .build());
        }
        return cruiseDTOS;
    }
}