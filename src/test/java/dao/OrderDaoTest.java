//package dao;
//
//import com.zaxxer.hikari.HikariDataSource;
//import dao.impl.MySqlOrderDAO;
//import dao.impl.MySqlUserDAO;
//import exceptions.DbException;
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
//class OrderDaoTest {
//
//    @Test
//    @Order(1)
//    void add() throws SQLException{
//        OrderDao orderDao = new MySqlOrderDAO();
//        HikariDataSource dataSource = mock(HikariDataSource.class);
//        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
//            assertDoesNotThrow(() -> orderDao.add(getTestUser()));
//        }
//    }
//
//    @Test
//    @Order(2)
//    void update() throws SQLException {
//        UserDao userDao = new MySqlUserDAO();
//        HikariDataSource dataSource = mock(HikariDataSource.class);
//        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
//            getTestUser().setLogin("login@gmail.com");
//            assertDoesNotThrow(() -> userDao.update(getTestUser()));
//        }
//    }
//
//    @Test
//    void getAmountByUser() {
//    }
//
//    @Test
//    void getOrdersByUser() {
//    }
//
//    @Test
//    void updatePaymentStatus() {
//    }
//
//    @Test
//    void getOrdersByUserAdmin() {
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
////        Order order = new Order();
////        Cruise cruise= new Cruise();
////        cruise.setId(resultSet.getInt("cruise"));
////        Ship ship = new Ship();
////        ship.setId(resultSet.getLong("ship"));
////        ship.setName(resultSet.getString("ship_name"));
////        cruise.setShip(ship);
////        cruise.setDuration(resultSet.getInt("duration"));
////        cruise.setPrice(resultSet.getDouble("price"));
////        cruise.setStartDate(resultSet.getDate("start_day"));
////        cruise.setNumber_of_register_people(resultSet.getInt("number_of_register_people"));
////
////        order.setUserId(resultSet.getLong("user"));
////        order.setPaid(resultSet.getBoolean("paid"));
////        order.setDateOfRegistration(resultSet.getDate("reg_date"));
////        order.setCruise(cruise);
//        when(rs.next()).thenReturn(true).thenReturn(false);
//        when(rs.getInt("cruise")).thenReturn(1L);
//        when(rs.getLong("ship")).thenReturn("login");
//        when(rs.getString("password")).thenReturn("password");
//        when(rs.getString("first_name")).thenReturn("name");
//        when(rs.getString("surname")).thenReturn("surname");
//        when(rs.getInt("role_id")).thenReturn(1);
//        when(rs.getBoolean("blocked")).thenReturn(false);
//    }
//
//    private model.Order getTestOrder() {
//        return model.Order.builder()
//                .paid(true)
//                .cruise()
//                .dateOfRegistration(Date.valueOf("2022-02-02"))
//                .userId(1)
//                .build();
//    }
//}