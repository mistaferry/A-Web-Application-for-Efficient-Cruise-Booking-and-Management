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
    public Optional<User> getByEmail(String email) throws DAOException {
        User user = null;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(TransactionMysqlQuery.GET_BY_ID);
            int index = 0;
            preparedStatement.setString(++index, email);
            user = new User();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = setUserValues(resultSet);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(user);
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
    public void updatePassword(User user) throws DAOException {

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
    public void add(User user) throws DAOException, SQLException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ShipMysqlQuery.ADD_SHIP);
            int index = 0;
            preparedStatement.setString(++index, user.getLogin());
            preparedStatement.setString(++index, user.getPassword());
            preparedStatement.setString(++index, user.getFirstName());
            preparedStatement.setString(++index, user.getSurname());
            preparedStatement.setInt(++index, user.getRoleId());
            preparedStatement.setBoolean(++index, user.isBlocked());
            preparedStatement.execute();
        }
    }

    @Override
    public Optional<User> getById(long id) throws DAOException, SQLException {
        User user;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UserMysqlQuery.GET_BY_ID);
            int index = 0;
            preparedStatement.setLong(++index, id);
            user = new User();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = setUserValues(resultSet);
                }
            }
        }
        return Optional.of(user);
    }

    @Override
    public List<User> getAll() throws DAOException, SQLException {
        List<User> userList;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UserMysqlQuery.GET_ALL);
            userList = new ArrayList<>();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = setUserValues(resultSet);
                    userList.add(user);
                }
            }
        }
        return userList;
    }

    private User setUserValues(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setSurname(resultSet.getString("surname"));
        user.setRoleId(resultSet.getInt("role_id"));
        user.setBlocked(resultSet.getBoolean("blocked"));
        return user;
    }

    @Override
    public void update(User user) throws DAOException, SQLException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UserMysqlQuery.UPDATE);
            int index = 0;
            preparedStatement.setString(++index, user.getLogin());
            preparedStatement.setString(++index, user.getPassword());
            preparedStatement.setString(++index, user.getFirstName());
            preparedStatement.setString(++index, user.getSurname());
            preparedStatement.setInt(++index, user.getRoleId());
            preparedStatement.setBoolean(++index, user.isBlocked());
            preparedStatement.execute();
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
