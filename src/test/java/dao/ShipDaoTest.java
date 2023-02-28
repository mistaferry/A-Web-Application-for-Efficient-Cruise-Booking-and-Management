package dao;

import com.zaxxer.hikari.HikariDataSource;
import dao.impl.MySqlShipDAO;
import exceptions.DbException;
import model.City;
import model.Ship;
import model.Staff;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;

public class ShipDaoTest {

    @Test
    void add() throws SQLException {
        ShipDao shipDao = new MySqlShipDAO();
        HikariDataSource dataSource = mock(HikariDataSource.class);
        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
            assertDoesNotThrow(() -> shipDao.add(getTestShip()));
        }
    }

    @Test
    void addRouteToShip() throws SQLException {
        HikariDataSource dataSource = mock(HikariDataSource.class);
        ShipDao shipDao = new MySqlShipDAO();
        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
            assertDoesNotThrow(() -> shipDao.addRoute(getTestShip().getId(), getTestRoute()));
        }
    }

    @Test
    void addStaffToShip() throws SQLException {
        HikariDataSource dataSource = mock(HikariDataSource.class);
        ShipDao shipDao = new MySqlShipDAO();
        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
            assertDoesNotThrow(() -> shipDao.addStaff(getTestShip().getId(), getTestStaff()));
        }
    }

    @Test
    void update() throws SQLException {
        ShipDao shipDao = new MySqlShipDAO();
        HikariDataSource dataSource = mock(HikariDataSource.class);
        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
            getTestShip().setName("Name");
            assertDoesNotThrow(() -> shipDao.update(getTestShip()));
        }
    }

    @Test
    void checkThereAreSomeShips() throws SQLException, DbException {
        HikariDataSource dataSource = mock(HikariDataSource.class);
        ShipDao shipDao = new MySqlShipDAO();
        try (PreparedStatement pst = createPreparedStatement(dataSource)) {
            ResultSet resultSet = mock(ResultSet.class);
            when(pst.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(false);
            List<Ship> ships = shipDao.getAll();
            assertNotEquals(0, ships.size());
        }
    }


    private PreparedStatement createPreparedStatement(HikariDataSource dataSource) throws SQLException {
        Connection connection = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(isA(String.class))).thenReturn(pst);
        doNothing().when(pst).setInt(isA(int.class), isA(int.class));
        doNothing().when(pst).setLong(isA(int.class), isA(long.class));
        doNothing().when(pst).setString(isA(int.class), isA(String.class));
        doNothing().when(pst).setBoolean(isA(int.class), isA(Boolean.class));
        when(pst.execute()).thenReturn(true);
        return pst;
    }

    private void setResultSetValues(ResultSet rs) throws SQLException {
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getLong("id")).thenReturn(1L);
        when(rs.getString("name")).thenReturn("Ship");
        when(rs.getInt("capacity")).thenReturn(100);

    }

    public static Ship getTestShip() {
        return Ship.builder()
                .id(1L)
                .capacity(100)
                .name("Ship")
                .numberOfPorts(10)
                .build();
    }

    public static List<City> getTestRoute() {
        List<City> cities = new ArrayList<>();
        City city1 = new City(1, "City1", "Country1");
        City city2 = new City(2, "City2", "Country2");
        City city3 = new City(3, "City3", "Country3");
        City city4 = new City(4, "City4", "Country4");
        cities.add(city1);
        cities.add(city2);
        cities.add(city3);
        cities.add(city4);
        return cities;
    }

    public static List<Staff> getTestStaff() {
        List<Staff> staff = new ArrayList<>();
        Staff staff1 = new Staff(1, "Name1", "Surname1");
        Staff staff2 = new Staff(2, "Name2", "Surname2");
        Staff staff3 = new Staff(3, "Name3", "Surname3");
        Staff staff4 = new Staff(4, "Name4", "Surname4");
        staff.add(staff1);
        staff.add(staff2);
        staff.add(staff3);
        staff.add(staff4);
        return staff;
    }
}
