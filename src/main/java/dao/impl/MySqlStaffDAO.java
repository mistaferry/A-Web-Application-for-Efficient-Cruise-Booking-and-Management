package dao.impl;

import connection.DataSource;
import dao.StaffDao;
import dao.constants.*;
import exceptions.DAOException;
import model.entity.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlStaffDAO implements StaffDao {

    @Override
    public void add(Staff staff) throws DAOException, SQLException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ShipMysqlQuery.ADD_SHIP);
            int index = 0;
            preparedStatement.setString(++index, staff.getFirstName());
            preparedStatement.setString(++index, staff.getSurname());
            preparedStatement.execute();
        }
    }

    @Override
    public Optional<Staff> getById(long id) throws DAOException, SQLException {
        Staff staff;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ShipMysqlQuery.GET_BY_ID);
            int index = 0;
            preparedStatement.setLong(++index, id);
            staff = new Staff();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    staff.setFirstName(resultSet.getString("first_name"));
                    staff.setSurname(resultSet.getString("surname"));
                }
            }
        }
        return Optional.of(staff);
    }

    @Override
    public List<Staff> getAll() throws DAOException, SQLException {
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
        }
        return staffList;
    }

    @Override
    public void update(Staff staff) throws DAOException, SQLException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ShipMysqlQuery.UPDATE);
            int index = 0;
            preparedStatement.setString(++index, staff.getFirstName());
            preparedStatement.setString(++index, staff.getSurname());
            preparedStatement.execute();
        }
    }

    @Override
    public void delete(long id) throws DAOException, SQLException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ShipMysqlQuery.DELETE);
            int index = 0;
            preparedStatement.setLong(++index, id);
            preparedStatement.execute();
        }
    }
}
