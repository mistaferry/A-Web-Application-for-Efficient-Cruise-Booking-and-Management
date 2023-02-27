package dao;

import com.zaxxer.hikari.HikariDataSource;
import dao.impl.MySqlCityDAO;
import dao.impl.MySqlUserDAO;
import exceptions.DbException;
import model.City;
import model.User;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dao.ShipDaoTest.getTestRoute;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;

class CityDaoTest {

    @Test
    @Order(1)
    void add() throws SQLException {
        CityDao cityDao = new MySqlCityDAO();
        HikariDataSource dataSource = mock(HikariDataSource.class);
        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
            assertDoesNotThrow(() -> cityDao.add(getTestCity()));
        }
    }

    @Test
    @Order(2)
    void update() throws SQLException {
        CityDao cityDao = new MySqlCityDAO();
        HikariDataSource dataSource = mock(HikariDataSource.class);
        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
            getTestCity().setName("Kyiv");
            assertDoesNotThrow(() -> cityDao.update(getTestCity()));
        }
    }

    @Test
    @Order(3)
    void checkThereAreSomeUsers() throws SQLException, DbException {
        HikariDataSource dataSource = mock(HikariDataSource.class);
        CityDao cityDao = new MySqlCityDAO();
        try (PreparedStatement pst = createPreparedStatement(dataSource)) {
            ResultSet resultSet = mock(ResultSet.class);
            when(pst.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(false);
            List<City> cities = cityDao.getAll();
            assertNotEquals(0, cities.size());
        }
    }

    @Test
    @Order(4)
    void getById() throws SQLException, DbException {
        CityDao cityDao = new MySqlCityDAO();
        HikariDataSource dataSource = mock(HikariDataSource.class);
        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
            ResultSet rs = mock(ResultSet.class);
            when(pst.executeQuery()).thenReturn(rs);
            setResultSetValues(rs);
            City city = cityDao.getById(1L).orElse(null);
            assertNotNull(rs);
            assertEquals(getTestCity(), city);
        }
    }

    @Test
    void getAllCitiesByShipId() throws SQLException, DbException {
        CityDao cityDao = new MySqlCityDAO();
        HikariDataSource dataSource = mock(HikariDataSource.class);
        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
            ResultSet rs = mock(ResultSet.class);
            when(pst.executeQuery()).thenReturn(rs);
            setResultSetValues(rs);
            List<City> cities = cityDao.getAllCitiesByShipId(1L);
            assertNotNull(rs);
            assertEquals(getTestRoute(), cities);
        }
    }

    private PreparedStatement createPreparedStatement(HikariDataSource dataSource) throws SQLException {
        Connection connection = mock(Connection.class);
        PreparedStatement pst = mock(PreparedStatement.class);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(isA(String.class))).thenReturn(pst);
        doNothing().when(pst).setLong(isA(int.class), isA(long.class));
        doNothing().when(pst).setString(isA(int.class), isA(String.class));
        when(pst.execute()).thenReturn(true);
        return pst;
    }

    private void setResultSetValues(ResultSet rs) throws SQLException {
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getLong("id")).thenReturn(1L);
        when(rs.getString("name")).thenReturn("City");
        when(rs.getString("country")).thenReturn("Country");
    }

    private City getTestCity() {
        return City.builder()
                .id(1L)
                .name("City")
                .country("Country")
                .build();
    }
}