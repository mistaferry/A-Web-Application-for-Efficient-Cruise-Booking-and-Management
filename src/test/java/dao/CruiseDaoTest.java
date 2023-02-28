package dao;

import com.zaxxer.hikari.HikariDataSource;
import dao.impl.MySqlCruiseDAO;
import dao.impl.MySqlUserDAO;
import exceptions.DbException;
import model.Cruise;
import model.Ship;
import model.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.*;
import java.util.List;

import static dao.ShipDaoTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;

class CruiseDaoTest {

    @Test
    void add() throws SQLException {
        CruiseDao cruiseDao = new MySqlCruiseDAO();
        HikariDataSource dataSource = mock(HikariDataSource.class);
        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
            assertDoesNotThrow(() -> cruiseDao.add(getTestCruise()));
            assertDoesNotThrow(() -> cruiseDao.add(getTestCruise2()));
            assertDoesNotThrow(() -> cruiseDao.add(getTestCruise3()));
        }
    }

    @Test
    void update() throws SQLException {
        CruiseDao cruiseDao = new MySqlCruiseDAO();
        HikariDataSource dataSource = mock(HikariDataSource.class);
        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
            getTestCruise().setPrice(20340);
            assertDoesNotThrow(() -> cruiseDao.update(getTestCruise()));
        }
    }

    @Test
    void checkThereAreSomeUsers() throws SQLException, DbException {
        HikariDataSource dataSource = mock(HikariDataSource.class);
        CruiseDao cruiseDao = new MySqlCruiseDAO();
        try (PreparedStatement pst = createPreparedStatement(dataSource)) {
            ResultSet resultSet = mock(ResultSet.class);
            when(pst.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(false);
            List<Cruise> cruises = cruiseDao.getAll();
            assertNotEquals(0, cruises.size());
        }
    }

    @Test
    void getById() throws SQLException, DbException {
        CruiseDao cruiseDao = new MySqlCruiseDAO();
        HikariDataSource dataSource = mock(HikariDataSource.class);
        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
            ResultSet rs = mock(ResultSet.class);
            when(pst.executeQuery()).thenReturn(rs);
            setResultSetValues(rs);
            Cruise cruise = cruiseDao.getById(1L).orElse(null);
            assertNotNull(rs);
            assertEquals(getTestCruise(), cruise);
        }
    }
//
//    @Test
//    void getByFilters() {
//    }
//
    @Test
    void getByDate() throws SQLException, DbException {
        CruiseDao cruiseDao = new MySqlCruiseDAO();
        HikariDataSource dataSource = mock(HikariDataSource.class);
        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
            ResultSet rs = mock(ResultSet.class);
            when(pst.executeQuery()).thenReturn(rs);
            setResultSetValues(rs);
            Cruise cruise = cruiseDao.getByDate(Date.valueOf("2020-09-01")).orElse(null);
            assertNotNull(rs);
            assertEquals(getTestCruise(), cruise);
        }
    }

    @Test
    void getByDuration() throws SQLException, DbException {
        CruiseDao cruiseDao = new MySqlCruiseDAO();
        HikariDataSource dataSource = mock(HikariDataSource.class);
        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
            ResultSet rs = mock(ResultSet.class);
            when(pst.executeQuery()).thenReturn(rs);
            setResultSetValues(rs);
            Cruise cruise = cruiseDao.getByDuration(10).orElse(null);
            assertNotNull(rs);
            assertEquals(getTestCruise(), cruise);
        }
    }

    @Test
    void getCruisePaginationWithNoFilters() throws SQLException, DbException {
        CruiseDao cruiseDao = new MySqlCruiseDAO();
        HikariDataSource dataSource = mock(HikariDataSource.class);
        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
            List<Cruise> list = cruiseDao.getCruisePaginationWithFilters(List.of("0", "0"), 2, 1);
            assertEquals(List.of(getTestCruise3()), list);
        }
    }

    @Test
    void getCruisePaginationWithDurationFilter() throws SQLException, DbException {
        CruiseDao cruiseDao = new MySqlCruiseDAO();
        HikariDataSource dataSource = mock(HikariDataSource.class);
        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
            List<Cruise> list = cruiseDao.getCruisePaginationWithFilters(List.of("0", "10"), 4, 0);
            assertEquals(List.of(getTestCruise()), list);
        }
    }

    @Test
    void getCruisePaginationWithStartDayFilter() throws SQLException, DbException {
        CruiseDao cruiseDao = new MySqlCruiseDAO();
        HikariDataSource dataSource = mock(HikariDataSource.class);
        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
            List<Cruise> list = cruiseDao.getCruisePaginationWithFilters(List.of("2020-02-22", "0"), 4, 0);
            assertEquals(List.of(getTestCruise2()), list);
        }
    }

    @Test
    void getCruisePagination() throws SQLException, DbException {
        CruiseDao cruiseDao = new MySqlCruiseDAO();
        HikariDataSource dataSource = mock(HikariDataSource.class);
        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
            List<Cruise> list = cruiseDao.getCruisePagination(2, 0);
            assertEquals(List.of(getTestCruise(), getTestCruise2()), list);
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
        when(rs.getLong("ship_id")).thenReturn(1L);
        when(rs.getInt("duration")).thenReturn(10);
        when(rs.getDouble("price")).thenReturn(Double.valueOf(10000));
        when(rs.getDate("start_day")).thenReturn(Date.valueOf("2020-09-01"));
        when(rs.getInt("number_of_register_people")).thenReturn(100);
    }

    public static Cruise getTestCruise() {
        Ship ship = getTestShip();
        ship.setRoute(getTestRoute());
        ship.setStaff(getTestStaff());
        return Cruise.builder()
                .id(1L)
                .duration(10)
                .price(10000)
                .number_of_register_people(100)
                .startDate(Date.valueOf("2020-09-01"))
                .ship(ship)
                .build();
    }

    public static Cruise getTestCruise2() {
        Ship ship = getTestShip();
        ship.setRoute(getTestRoute());
        ship.setStaff(getTestStaff());
        return Cruise.builder()
                .id(2L)
                .duration(20)
                .price(12000)
                .number_of_register_people(102)
                .startDate(Date.valueOf("2020-02-22"))
                .ship(ship)
                .build();
    }

    public static Cruise getTestCruise3() {
        Ship ship = getTestShip();
        ship.setRoute(getTestRoute());
        ship.setStaff(getTestStaff());
        return Cruise.builder()
                .id(3L)
                .duration(13)
                .price(30300)
                .number_of_register_people(130)
                .startDate(Date.valueOf("2020-01-30"))
                .ship(ship)
                .build();
    }
}