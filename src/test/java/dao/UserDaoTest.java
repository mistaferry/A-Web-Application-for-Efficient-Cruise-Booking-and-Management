package dao;

import com.zaxxer.hikari.HikariDataSource;
import dao.impl.MySqlUserDAO;
import exceptions.DbException;
import model.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

class UserDaoTest {
    @Test
    void add() throws SQLException{
        UserDao userDao = new MySqlUserDAO();
        HikariDataSource dataSource = mock(HikariDataSource.class);
        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
            assertDoesNotThrow(() -> userDao.add(getTestUser()));
        }
    }

    @Test
    void update() throws SQLException {
        UserDao userDao = new MySqlUserDAO();
        HikariDataSource dataSource = mock(HikariDataSource.class);
        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
            getTestUser().setLogin("login@gmail.com");
            assertDoesNotThrow(() -> userDao.update(getTestUser()));
        }
    }

    @Test
    void checkThereAreSomeUsers() throws SQLException, DbException {
        HikariDataSource dataSource = mock(HikariDataSource.class);
        UserDao userDao = new MySqlUserDAO();
        try (PreparedStatement pst = createPreparedStatement(dataSource)) {
            ResultSet resultSet = mock(ResultSet.class);
            when(pst.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(false);
            List<User> users = userDao.getAll();
            assertNotEquals(0, users.size());
        }
    }

    @Test
    void getById() throws SQLException, DbException {
        UserDao userDao = new MySqlUserDAO();
        HikariDataSource dataSource = mock(HikariDataSource.class);
        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
            ResultSet rs = mock(ResultSet.class);
            when(pst.executeQuery()).thenReturn(rs);
            setResultSetValues(rs);
            User user = userDao.getById(1L).orElse(null);
            assertNotNull(rs);
            assertEquals(getTestUser(), user);
        }
    }

    @Test
    void getByEmail() throws SQLException, DbException {
        UserDao userDao = new MySqlUserDAO();
        HikariDataSource dataSource = mock(HikariDataSource.class);
        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
            ResultSet rs = mock(ResultSet.class);
            when(pst.executeQuery()).thenReturn(rs);
            setResultSetValues(rs);
            User user = userDao.getByEmail("newuser@gmail.com", "newUser").orElse(null);
            assertNotNull(rs);
            assertEquals(getTestUser(), user);
        }
    }

    @Test
    @Order(6)
    void changePassword() throws SQLException {
        UserDao userDao = new MySqlUserDAO();
        HikariDataSource dataSource = mock(HikariDataSource.class);
        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
            assertDoesNotThrow(() -> userDao.changePassword(9L, "huryn1"));
        }
    }

    @Test
    @Order(8)
    void delete() throws SQLException {
        HikariDataSource dataSource = mock(HikariDataSource.class);
        UserDao user = new MySqlUserDAO();
        try (PreparedStatement pst = createPreparedStatement(dataSource)) {
            assertDoesNotThrow(() -> user.delete(9L));
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
        when(rs.getString("login")).thenReturn("login");
        when(rs.getString("password")).thenReturn("password");
        when(rs.getString("first_name")).thenReturn("name");
        when(rs.getString("surname")).thenReturn("surname");
        when(rs.getInt("role_id")).thenReturn(1);
        when(rs.getBoolean("blocked")).thenReturn(false);
    }

    public User getTestUser() {
        return User.builder()
                .id(1L)
                .login("newuser@gmail.com")
                .password("newUser")
                .firstName("UserName")
                .surname("UserSurname")
                .roleId(1)
                .blocked(false)
                .build();
    }
}