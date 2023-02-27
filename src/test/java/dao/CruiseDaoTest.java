//package dao;
//
//import com.zaxxer.hikari.HikariDataSource;
//import dao.impl.MySqlCruiseDAO;
//import dao.impl.MySqlUserDAO;
//import exceptions.DbException;
//import model.Cruise;
//import model.Ship;
//import model.User;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//
//import java.sql.*;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.isA;
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.doNothing;
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class CruiseDaoTest {
//
//    @Test
//    @Order(1)
//    void add() throws SQLException {
//        CruiseDao cruiseDao = new MySqlCruiseDAO();
//        HikariDataSource dataSource = mock(HikariDataSource.class);
//        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
//            assertDoesNotThrow(() -> cruiseDao.add(getTestUser()));
//        }
//    }
//
//    @Test
//    @Order(2)
//    void update() throws SQLException {
//        CruiseDao cruiseDao = new MySqlCruiseDAO();
//        HikariDataSource dataSource = mock(HikariDataSource.class);
//        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
//            getTestUser().setLogin("login@gmail.com");
//            assertDoesNotThrow(() -> cruiseDao.update(getTestUser()));
//        }
//    }
//
//    @Test
//    @Order(3)
//    void checkThereAreSomeUsers() throws SQLException, DbException {
//        HikariDataSource dataSource = mock(HikariDataSource.class);
//        CruiseDao cruiseDao = new MySqlCruiseDAO();
//        try (PreparedStatement pst = createPreparedStatement(dataSource)) {
//            ResultSet resultSet = mock(ResultSet.class);
//            when(pst.executeQuery()).thenReturn(resultSet);
//            when(resultSet.next()).thenReturn(false);
//            List<Cruise> cruises = cruiseDao.getAll();
//            assertNotEquals(0, cruises.size());
//        }
//    }
//
//    @Test
//    @Order(4)
//    void getById() throws SQLException, DbException {
//        CruiseDao cruiseDao = new MySqlCruiseDAO();
//        HikariDataSource dataSource = mock(HikariDataSource.class);
//        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
//            ResultSet rs = mock(ResultSet.class);
//            when(pst.executeQuery()).thenReturn(rs);
//            setResultSetValues(rs);
//            Cruise cruise = cruiseDao.getById(9L).orElse(null);
//            assertNotNull(rs);
//            assertEquals(getTestUser(), cruise);
//        }
//    }
//
//    @Test
//    void getByFilters() {
//    }
//
//    @Test
//    void getByDate() {
//    }
//
//    @Test
//    void getByDuration() {
//    }
//
//    @Test
//    void getCruisePaginationWithFilters() {
//    }
//
//    @Test
//    void getAmountWithFilters() {
//    }
//
//    @Test
//    void getAmountByUser() {
//    }
//
//    @Test
//    void getCruisePagination() {
//    }
//
//    private PreparedStatement createPreparedStatement(HikariDataSource dataSource) throws SQLException {
//        Connection connection = mock(Connection.class);
//        PreparedStatement pst = mock(PreparedStatement.class);
//        when(dataSource.getConnection()).thenReturn(connection);
//        when(connection.prepareStatement(isA(String.class))).thenReturn(pst);
//        doNothing().when(pst).setInt(isA(int.class), isA(int.class));
//        doNothing().when(pst).setLong(isA(int.class), isA(long.class));
//        doNothing().when(pst).setString(isA(int.class), isA(String.class));
//        doNothing().when(pst).setBoolean(isA(int.class), isA(Boolean.class));
//        when(pst.execute()).thenReturn(true);
//        return pst;
//    }
//
//    private void setResultSetValues(ResultSet rs) throws SQLException {
//        when(rs.next()).thenReturn(true).thenReturn(false);
//        when(rs.getLong("id")).thenReturn(1L);
//        when(rs.getString("login")).thenReturn("login");
//        when(rs.getString("password")).thenReturn("password");
//        when(rs.getString("first_name")).thenReturn("name");
//        when(rs.getString("surname")).thenReturn("surname");
//        when(rs.getInt("role_id")).thenReturn(1);
//        when(rs.getBoolean("blocked")).thenReturn(false);
//    }
//
//    private Cruise getTestCruise() {
//        return Cruise.builder()
//                .id(9L)
//                .duration(10)
//                .price(10000)
//                .number_of_register_people(100)
//                .startDate(Date.valueOf("2020-09-01"))
//                .ship()
//                .build();
//    }
//}