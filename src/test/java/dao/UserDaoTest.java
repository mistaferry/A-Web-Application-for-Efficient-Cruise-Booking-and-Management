package dao;

import com.zaxxer.hikari.HikariDataSource;
import connection.DataSource;
import dao.impl.MySqlUserDAO;
import exceptions.DbException;
import model.Ship;
import model.User;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

class UserDaoTest {

    @Test
    void getByEmail() throws SQLException, DbException {
        UserDao userDao = new MySqlUserDAO();
        HikariDataSource dataSource = mock(HikariDataSource.class);
        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
            ResultSet rs = mock(ResultSet.class);
            when(pst.executeQuery()).thenReturn(rs);
            setResultSetValues(rs);
            User user = userDao.getByEmail("user@gmail.com", "user").orElse(null);
            assertNotNull(rs);
            assertEquals(getTestUser(), user);
        }
    }

    @Test
    void changePassword() throws SQLException {
        UserDao userDao = new MySqlUserDAO();
        HikariDataSource dataSource = mock(HikariDataSource.class);
        try(PreparedStatement pst = createPreparedStatement(dataSource)) {
            assertDoesNotThrow(() -> userDao.changePassword(7L, "huryn1"));
        }
    }

    @Test
    void getUserPagination() {
    }

    @Test
    void getAmount() {
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

    private User getTestUser() {
        return User.builder()
                .id(7L)
                .login("user@gmail.com")
                .password("user")
                .firstName("User")
                .surname("User")
                .roleId(1)
                .blocked(false)
                .build();
    }
}