package dao.impl;

import connection.DataSource;
import dao.OrderDao;
import dao.constants.CruiseMysqlQuery;
import dao.constants.OrderMysqlQuery;
import exceptions.DbException;
import model.entity.Cruise;
import model.entity.Order;
import model.entity.Ship;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlOrderDAO implements OrderDao {
    @Override
    public void add(Order order) throws DbException {

    }

    @Override
    public Optional<Order> getById(long id) throws DbException {
        return Optional.empty();
    }

    @Override
    public List<Order> getAll() throws DbException {
        return null;
    }

    @Override
    public void update(Order order) throws DbException {

    }

    @Override
    public void delete(long id) throws DbException {

    }

    @Override
    public int getAmountByUser(long userId) throws DbException {
        int amount = 0;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = null;
            String query = OrderMysqlQuery.GET_ORDERS_AMOUNT;
            preparedStatement = connection.prepareStatement(query);
            int index = 0;
            preparedStatement.setLong(++index, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            try {
                if(resultSet.next()){
                    amount = resultSet.getInt(1);
                }
            } catch (SQLException e) {
                throw new DbException("Cannot get Cruise amount by User", e);
            }
        }catch (SQLException e){
            throw new DbException("Cannot get Cruise amount by User", e);
        }
        return amount;
    }

    @Override
    public List<Order> getOrdersByUser(long loggedUserId, int cruisePerPage, int pageNum) throws DbException {
        List<Order> orderList;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(OrderMysqlQuery.GET_ALL +
                    CruiseMysqlQuery.GET_PAGINATION);
            orderList = new ArrayList<>();
            int index = 0;
            preparedStatement.setLong(++index, loggedUserId);
            setPaginationValues(preparedStatement, cruisePerPage, index, pageNum * cruisePerPage);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = setOrderValues(resultSet);
                    orderList.add(order);
                }
            }catch (SQLException e) {
                throw new DbException("Cannot get Orders values", e);
            }
        }catch (SQLException e) {
            throw new DbException("Cannot get Orders by User", e);
        }
        return orderList;
    }

    @Override
    public void updatePaymentStatus(long userId, long cruiseId, Date date) throws DbException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(OrderMysqlQuery.UPDATE_PAYMENT_STATUS);
            int index = 0;
            preparedStatement.setLong(++index, userId);
            preparedStatement.setLong(++index, cruiseId);
            preparedStatement.setDate(++index, date);
            preparedStatement.execute();
        }catch (SQLException e){
            throw new DbException("Cannot update Order payment status", e);
        }
    }

    @Override
    public List<Order> getOrdersByUserAdmin(long loggedUserId, int cruisePerPage, int pageNum) throws DbException {
        List<Order> orderList;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(OrderMysqlQuery.GET_USER_ORDERS +
                    CruiseMysqlQuery.GET_PAGINATION);
            orderList = new ArrayList<>();
            int index = 0;
            preparedStatement.setLong(++index, loggedUserId);
            setPaginationValues(preparedStatement, cruisePerPage, index, pageNum * cruisePerPage);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = setOrderValues(resultSet);
                    orderList.add(order);
                }
            }catch (SQLException e) {
                throw new DbException("Admin cannot get Orders values", e);
            }
        }catch (SQLException e) {
            throw new DbException("Admin cannot get Orders by User", e);
        }
        return orderList;
    }

    private Order setOrderValues(ResultSet resultSet)  throws SQLException, DbException {
        Order order = new Order();
        Cruise cruise= new Cruise();
        cruise.setId(resultSet.getInt("cruise"));
        Ship ship = new Ship();
        ship.setId(resultSet.getLong("ship"));
        ship.setName(resultSet.getString("ship_name"));
        cruise.setShip(ship);
        cruise.setDuration(resultSet.getInt("duration"));
        cruise.setPrice(resultSet.getDouble("price"));
        cruise.setStartDate(resultSet.getDate("start_day"));
        cruise.setNumber_of_register_people(resultSet.getInt("number_of_register_people"));

        order.setUserId(resultSet.getLong("user"));
        order.setPaid(resultSet.getBoolean("paid"));
        order.setDateOfRegistration(resultSet.getDate("reg_date"));
        order.setCruise(cruise);
        return order;
    }

    public static void setPaginationValues(PreparedStatement preparedStatement, int cruisePerPage, int index, int i) throws SQLException {
        preparedStatement.setInt(++index, i);
        preparedStatement.setInt(++index, cruisePerPage);
    }
}
