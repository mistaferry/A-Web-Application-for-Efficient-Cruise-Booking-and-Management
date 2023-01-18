package dao.impl;

import connection.DataSource;
import dao.UserDao;
import dao.constants.*;
import exceptions.DAOException;
import model.entity.Role;
import model.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlUserDAO implements UserDao {
    @Override
    public Optional<User> getByEmail(String email, String password) throws DAOException {
        User user = null;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UserMysqlQuery.GET_BY_EMAIL);
            int index = 0;
            preparedStatement.setString(++index, email);
            user = new User();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user = setUserValues(resultSet);
                }
            }
            if(user.getPassword() == null || !user.getPassword().equals(password)){
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.of(user);
    }

    @Override
    public List<User> getSorted(String query) throws DAOException {
        return null;
    }

    @Override
    public int getNumberOfRecords(String filter) throws DAOException {
        return 0;
    }

    @Override
    public void changePassword(long id, String newPassword) throws DAOException, SQLException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UserMysqlQuery.CHANGE_PASSWORD);
            int index = 0;
            preparedStatement.setString(++index, newPassword);
            preparedStatement.setLong(++index, id);
            preparedStatement.execute();
        }
    }

    @Override
    public void setUserRole(String userEmail, Role role) throws DAOException {

    }

    @Override
    public void registerForCruise(long userId, long eventId) throws DAOException {

    }

    @Override
    public void cancelRegistration(long userId, long eventId) throws DAOException {

    }

    @Override
    public boolean isRegistered(long userId, long eventId) throws DAOException {
        return false;
    }

    @Override
    public void add(User user) throws DAOException{
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UserMysqlQuery.ADD_USER);
            int index = 0;
            preparedStatement.setString(++index, user.getLogin());
            preparedStatement.setString(++index, user.getPassword());
            preparedStatement.setString(++index, user.getFirstName());
            preparedStatement.setString(++index, user.getSurname());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Optional<User> getById(long id) throws DAOException, SQLException {
        User user = null;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UserMysqlQuery.GET_BY_ID);
            int index = 0;
            preparedStatement.setLong(++index, id);
            user = new User();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user = setUserValues(resultSet);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getAll() throws DAOException, SQLException {
        List<User> userList;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UserMysqlQuery.GET_ALL);
            userList = new ArrayList<>();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = setUserValues(resultSet);
                    userList.add(user);
                }
            }
        }
        return userList;
    }

    private User setUserValues(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setSurname(resultSet.getString("surname"));
        user.setRoleId(resultSet.getInt("role_id"));
        user.setBlocked(resultSet.getBoolean("blocked"));
        return user;
    }

    @Override
    public void update(User user) throws DAOException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UserMysqlQuery.UPDATE);
            int index = 0;
            preparedStatement.setString(++index, user.getLogin());
            preparedStatement.setString(++index, user.getPassword());
            preparedStatement.setString(++index, user.getFirstName());
            preparedStatement.setString(++index, user.getSurname());
            preparedStatement.setInt(++index, user.getRoleId());
            preparedStatement.setBoolean(++index, user.isBlocked());
            preparedStatement.setLong(++index, user.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(long id) throws DAOException, SQLException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UserMysqlQuery.DELETE);
            int index = 0;
            preparedStatement.setLong(++index, id);
            preparedStatement.execute();
        }
    }

}
