package dao.impl;

import connection.DataSource;
import dao.StaffDao;
import dao.constants.*;

import exceptions.DbException;
import model.entity.City;
import model.entity.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class MySqlStaffDAO implements StaffDao {

    @Override
    public void add(Staff staff) throws DbException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ShipMysqlQuery.ADD_SHIP);
            int index = 0;
            preparedStatement.setString(++index, staff.getFirstName());
            preparedStatement.setString(++index, staff.getSurname());
            preparedStatement.execute();
        }catch (SQLException e){
            throw new DbException("Cannot add Staff", e);
        }
    }

    @Override
    public Optional<Staff> getById(long id) throws DbException {
        Staff staff;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(StaffMysqlQuery.GET_BY_ID);
            int index = 0;
            preparedStatement.setLong(++index, id);
            staff = new Staff();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    staff.setId(id);
                    staff.setFirstName(resultSet.getString("first_name"));
                    staff.setSurname(resultSet.getString("surname"));
                }
            }
        }catch (SQLException e){
            throw new DbException("Cannot get Staff by id", e);
        }
        return Optional.of(staff);
    }

    @Override
    public List<Staff> getAll() throws DbException {
        List<Staff> staffList;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ShipMysqlQuery.GET_ALL);
            staffList = new ArrayList<>();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Staff staff = new Staff();
                    staff.setFirstName(resultSet.getString("first_name"));
                    staff.setSurname(resultSet.getString("surname"));
                    staffList.add(staff);
                }
            }
        }catch (SQLException e){
            throw new DbException("Cannot get all Staff", e);
        }
        return staffList;
    }

    @Override
    public void update(Staff staff) throws DbException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ShipMysqlQuery.UPDATE);
            int index = 0;
            preparedStatement.setString(++index, staff.getFirstName());
            preparedStatement.setString(++index, staff.getSurname());
            preparedStatement.execute();
        }catch (SQLException e){
            throw new DbException("Cannot update Staff", e);
        }
    }

    @Override
    public void delete(long id) throws DbException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ShipMysqlQuery.DELETE);
            int index = 0;
            preparedStatement.setLong(++index, id);
            preparedStatement.execute();
        }catch (SQLException e){
            throw new DbException("Cannot delete Ship", e);
        }
    }

    @Override
    public List<Staff> getAllStaffByShipId(long shipId) throws DbException {
        List<Staff> staff = null;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CityMysqlQuery.GET_STAFF_BY_SHIP_ID);
            int index = 0;
            preparedStatement.setLong(++index, shipId);
            staff = new ArrayList<>();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Staff person = getStaffFromDB(resultSet);
                    staff.add(person);
                }
            }
        }catch (SQLException e){
            throw new DbException("Cannot get Staff", e);
        }
        return staff;
    }

    private Staff getStaffFromDB(ResultSet resultSet) throws DbException {
        long staff_id = 0;
        try {
            staff_id = resultSet.getLong("staff_id");
        } catch (SQLException e){
            throw new DbException("Cannot get Staff from Db", e);
        }
        return (new MySqlStaffDAO()).getById(staff_id).get();
    }
}
