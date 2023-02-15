package dao.impl;

import connection.DataSource;
import dao.UserDao;
import dao.constants.*;
import exceptions.DbException;
import model.Role;
import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static dao.impl.MySqlCruiseDAO.setPaginationValues;

public class MySqlUserDAO implements UserDao {
    @Override
    public Optional<User> getByEmail(String email, String password) throws DbException {
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
            throw new DbException("Cannot get User By email", e);
        }
        return Optional.of(user);
    }

    @Override
    public List<User> getSorted(String query) throws DbException {
        return null;
    }

    @Override
    public int getNumberOfRecords(String filter) throws DbException {
        return 0;
    }

    @Override
    public void changePassword(long id, String newPassword) throws DbException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UserMysqlQuery.CHANGE_PASSWORD);
            int index = 0;
            preparedStatement.setString(++index, newPassword);
            preparedStatement.setLong(++index, id);
            preparedStatement.execute();
        }catch (SQLException e) {
            throw new DbException("Cannot change password", e);
        }
    }

    @Override
    public void setUserRole(String userEmail, Role role) throws DbException {

    }

    @Override
    public void registerForCruise(long userId, long eventId) throws DbException {

    }

    @Override
    public void cancelRegistration(long userId, long eventId) throws DbException {

    }

    @Override
    public boolean isRegistered(long userId, long eventId) throws DbException {
        return false;
    }

    @Override
    public void add(User user) throws DbException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UserMysqlQuery.ADD_USER);
            int index = 0;
            preparedStatement.setString(++index, user.getLogin());
            preparedStatement.setString(++index, user.getPassword());
            preparedStatement.setString(++index, user.getFirstName());
            preparedStatement.setString(++index, user.getSurname());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DbException("Cannot add User", e);
        }
    }

    @Override
    public Optional<User> getById(long id) throws DbException {
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
        } catch (SQLException e) {
            throw new DbException("Cannot get User By id", e);
        }
        return Optional.of(user);
    }

    @Override
    public List<User> getAll() throws DbException {
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
        }catch (SQLException e) {
            throw new DbException("Cannot get all Users", e);
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
    public void update(User user) throws DbException {
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
            throw new DbException("Cannot update User", e);
        }
    }

    @Override
    public void delete(long id) throws DbException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UserMysqlQuery.DELETE);
            int index = 0;
            preparedStatement.setLong(++index, id);
            preparedStatement.execute();
        }catch (SQLException e) {
            throw new DbException("Cannot delete User", e);
        }
    }

    @Override
    public List<User> getUserPagination(int cruisePerPage, int pageNum) throws DbException {
        List<User> userList;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement;
            String query = UserMysqlQuery.GET_ALL;
            query += UserMysqlQuery.GET_PAGINATION;
            preparedStatement = connection.prepareStatement(query);
            int index = 0;
            setPaginationValues(preparedStatement, cruisePerPage, index, pageNum * cruisePerPage);
            userList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            try {
                while (resultSet.next()) {
                    User user = setUserValues(resultSet);
                    userList.add(user);
                }
            } catch (SQLException e) {
                throw new DbException("Cannot set User values", e);
            }
        }catch (SQLException e) {
            throw new DbException("Cannot get Users", e);
        }
        return userList;
    }

    @Override
    public int getAmount() throws DbException {
        int amount = 0;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = null;
            String query = UserMysqlQuery.GET_COUNT;
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            try {
                if(resultSet.next()){
                    amount = resultSet.getInt(1);
                }
            } catch (SQLException e) {
                throw new DbException("Cannot get User amount", e);
            }
        }catch (SQLException e) {
            throw new DbException("Cannot get User amount", e);
        }
        return amount;
    }

    @Override
    public void updateUserValuesByAdmin(User chosenUser, boolean accountStatus, int role) throws DbException {
          
    }
}
