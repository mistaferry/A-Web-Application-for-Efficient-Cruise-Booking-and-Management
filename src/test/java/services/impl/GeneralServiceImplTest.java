package services.impl;

import com.google.protobuf.ServiceException;
import dao.CruiseDao;
import dao.OrderDao;
import dao.ShipDao;
import dao.UserDao;
import dto.OrderDTO;
import dto.ShipDTO;
import dto.UserDTO;
import exceptions.DbException;
import model.*;
import org.junit.jupiter.api.Test;
import services.GeneralService;
import utils.Convertor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

class GeneralServiceImplTest {
    private final UserDao userDao = mock(UserDao.class);
    private final CruiseDao cruiseDao = mock(CruiseDao.class);
    private final ShipDao shipDao = mock(ShipDao.class);
    private final OrderDao orderDao = mock(OrderDao.class);
    private final GeneralService generalService = new GeneralServiceImpl(userDao, cruiseDao, shipDao, orderDao);

    @Test
    void viewAllUsers() throws DbException, ServiceException {
        when(userDao.getAll()).thenReturn(List.of(getTestUser()));
        List<UserDTO> users = generalService.viewAllUsers();
        assertIterableEquals(List.of(getTestUserDTO()), users);
    }

    @Test
    void getChosenUser() throws DbException, ServiceException {
        when(userDao.getById(1L)).thenReturn(Optional.of(getTestUser()));
        assertEquals(getTestUserDTO(), generalService.getChosenUser(1L));
    }

    @Test
    void updateCruise() throws DbException {
        doNothing().when(cruiseDao).update(isA(Cruise.class));
        Cruise cruise = getTestCruise();
        cruise.setPrice(1230.9);
        cruise.setDuration(4);
        when(cruiseDao.getById(1L)).thenReturn(Optional.of(cruise));
        assertDoesNotThrow(() -> generalService.updateCruise(Convertor.convertCruiseToDTO(cruise)));
    }

    @Test
    void getAllShips() throws DbException, ServiceException {
        when(shipDao.getAll()).thenReturn(List.of(getTestShip(), getTestShip(), getTestShip()));
        assertIterableEquals(List.of(getTestShipDTO(), getTestShipDTO(), getTestShipDTO()), generalService.getAllShips());
    }

    @Test
    void viewUserOrdersWithPagination() throws DbException, ServiceException {
        when(orderDao.getOrdersByUser(1L, 2, 1)).thenReturn(List.of(getTestOrder(), getTestOrder()));
        List<OrderDTO> orders = generalService.viewUserOrdersWithPagination(1L, 2, 1);
        assertIterableEquals(List.of(getTestOrderDTO(), getTestOrderDTO()), orders);
    }

    @Test
    void viewAllUsersWithPagination() throws DbException, ServiceException {
        when(userDao.getUserPagination(3, 0)).thenReturn(List.of(getTestUser(), getTestUser(), getTestUser()));
        List<UserDTO> users = generalService.viewAllUsersWithPagination(3, 0);
        assertIterableEquals(List.of(getTestUserDTO(), getTestUserDTO(), getTestUserDTO()), users);
    }

    @Test
    void getUserAmount() throws DbException, ServiceException {
        when(userDao.getAmount()).thenReturn(List.of(getTestUser(), getTestUser(), getTestUser()).size());
        assertEquals(3, generalService.getUserAmount());
    }

    @Test
    void getCruiseAmount() throws DbException, ServiceException {
        when(cruiseDao.getAmountWithFilters(List.of("2022-4-20","4"))).thenReturn(List.of(getTestCruise(), getTestCruise()).size());
        assertEquals(2, generalService.getAmountWithFilters(List.of("2022-4-20","4")));
    }

    @Test
    void signIn() throws DbException, ServiceException {
        User user = getTestUser();
        when(userDao.getByEmail("test@gmail.com", "test")).thenReturn(Optional.of(user));
        assertEquals(getTestUserDTO(), generalService.signIn("test@gmail.com", "test"));
    }

    @Test
    void register() throws DbException {
        User user = new User();
        user.setLogin("test@gmail.com");
        user.setPassword("test");
        user.setFirstName("First Name");
        user.setSurname("Surname");
        doNothing().when(userDao).add(isA(User.class));
        when(userDao.getById(1L)).thenReturn(Optional.of(user));
        assertDoesNotThrow(() -> generalService.register(user.getLogin(), user.getPassword(), user.getFirstName(), user.getSurname()));
    }

    private OrderDTO getTestOrderDTO() {
        return OrderDTO.builder()
                .userId(1L)
                .cruise(getTestCruise())
                .paid(true)
                .dateOfRegistration(Date.valueOf("2022-4-20"))
                .build();
    }

    private Order getTestOrder() {
        return Order.builder()
                .userId(1L)
                .cruise(getTestCruise())
                .paid(true)
                .dateOfRegistration(Date.valueOf("2022-4-20"))
                .build();
    }

    private Cruise getTestCruise() {
        Cruise cruise = new Cruise();
        cruise.setId(1L);
        cruise.setNumber_of_register_people(3);
        cruise.setShip(new Ship(1, "Ship1", 12,
                List.of(new Staff(1, "First Name", "Surname")),
                List.of(new City(1, "City", "Country")), 200));
        cruise.setDuration(3);
        cruise.setStartDate(Date.valueOf("2022-4-20"));
        cruise.setPrice(3123.0);
        return cruise;
    }

    private UserDTO getTestUserDTO() {
        return UserDTO.builder()
                .id(1L)
                .login("test@gmail.com")
                .password("test")
                .firstName("First Name")
                .surname("Surname")
                .roleId(1)
                .build();
    }

    private User getTestUser() {
        User user = new User();
        user.setId(1L);
        user.setLogin("test@gmail.com");
        user.setPassword("test");
        user.setFirstName("First Name");
        user.setSurname("Surname");
        user.setRoleId(1);
        return user;
    }

    private List<City> getTestRouteList() {
        List<City> list = new ArrayList<>();
        list.add(new City(1, "City1", "Country"));
        list.add(new City(2, "City2", "Country"));
        list.add(new City(3, "City3", "Country"));
        list.add(new City(4, "City4", "Country"));
        return list;
    }

    private List<Staff> getTestStaffList() {
        List<Staff> list = new ArrayList<>();
        list.add(new Staff(1, "First Name1", "Surname1"));
        list.add(new Staff(2, "First Name2", "Surname2"));
        list.add(new Staff(3, "First Name3", "Surname3"));
        list.add(new Staff(4, "First Name4", "Surname4"));
        return list;
    }

    private ShipDTO getTestShipDTO() {
        return ShipDTO.builder()
                .id(1L)
                .name("Ship1")
                .numberOfPorts(6)
                .capacity(200)
                .route(getTestRouteList())
                .staff(getTestStaffList())
                .build();
    }

    private Ship getTestShip() {
        return Ship.builder()
                .id(1L)
                .name("Ship1")
                .numberOfPorts(6)
                .capacity(200)
                .route(getTestRouteList())
                .staff(getTestStaffList())
                .build();
    }
}