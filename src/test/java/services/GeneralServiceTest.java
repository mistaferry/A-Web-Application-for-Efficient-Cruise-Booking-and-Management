package services;

import com.google.protobuf.ServiceException;
import dao.CruiseDao;
import dao.OrderDao;
import dao.ShipDao;
import dao.UserDao;
import dto.CruiseDTO;
import exceptions.DbException;
import model.entity.City;
import model.entity.Cruise;
import model.entity.Ship;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.junit.jupiter.api.Test;
import services.impl.GeneralServiceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GeneralServiceTest {
    private final UserDao userDao = mock(UserDao.class);
    private final CruiseDao cruiseDao = mock(CruiseDao.class);
    private final ShipDao shipDao = mock(ShipDao.class);
    private final OrderDao orderDao = mock(OrderDao.class);
    private final GeneralService generalService = new GeneralServiceImpl(userDao, cruiseDao, shipDao, orderDao);

    @IgnoreForBinding
    @Test
    void testGetChosenCruise() throws ServiceException, DbException{
        when(cruiseDao.getById(1L)).thenReturn(Optional.of(getTestCruise()));
        CruiseDTO cruiseExpected = getTestCruiseDTO();
        CruiseDTO cruiseActual = generalService.getChosenCruise(1L);
        assertEquals(cruiseExpected, cruiseActual);
    }

    private CruiseDTO getTestCruiseDTO() {
        return CruiseDTO.builder()
                .id(1L)
                .duration(3)
                .number_of_register_people(3)
                .ship(getTestShip())
                .startDate(Date.valueOf("2022-4-20"))
                .price(3123)
                .build();
    }

    private Cruise getTestCruise() {
        Cruise cruise = new Cruise();
        cruise.setId(1L);
        cruise.setNumber_of_register_people(3);
        cruise.setShip(getTestShip());
        cruise.setDuration(3);
        cruise.setStartDate(Date.valueOf("2022-4-20"));
        cruise.setPrice(3123.0);
        cruise.setShip(getTestShip());
        return cruise;
    }

    private Ship getTestShip(){
        Ship ship = new Ship();
        ship.setCapacity(200);
        ship.setNumberOfPorts(2);
        ship.setRoute(getTestCityList());
        ship.setStaff(null);
        ship.setName("Karamel");
        return ship;
    }

    private City getTestCity1(){
        return City.builder()
                .country("Ukraine")
                .name("Odesa")
                .build();
    }

    private City getTestCity2(){
        return City.builder()
                .country("Germany")
                .name("Rostok")
                .build();
    }

    private List<City> getTestCityList(){
        List<City> cityList = new ArrayList<>();
        cityList.add(getTestCity1());
        cityList.add(getTestCity2());
        return cityList;
    }

}
