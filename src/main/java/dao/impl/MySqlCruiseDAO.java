package dao.impl;

import connection.DataSource;
import dao.CruiseDao;
import exceptions.DAOException;
import model.entity.Cruise;
import model.entity.Ship;
import dao.constants.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlCruiseDAO implements CruiseDao {
    @Override
    public List<Cruise> getSorted(String query) throws DAOException, SQLException {
        List<Cruise> cruiseList;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CruiseMysqlQuery.GET_SORTED);
            int index = 0;
            preparedStatement.setString(++index, query);
            cruiseList = new ArrayList<>();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Cruise cruise = setCruiseValues(resultSet);
                    cruiseList.add(cruise);
                }
            }
        }
        return cruiseList;
    }

    @Override
    public Optional<Cruise> getByDate(Date date) throws DAOException, SQLException {
        Cruise cruise;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CruiseMysqlQuery.GET_BY_DATE);
            int index = 0;
            preparedStatement.setDate(++index, date);
            cruise = new Cruise();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    cruise = setCruiseValues(resultSet);
                }
            }
        }
        return Optional.of(cruise);
    }

    @Override
    public Optional<Cruise> getByDuration(int duration) throws DAOException, SQLException {
        Cruise cruise;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CruiseMysqlQuery.GET_BY_DURATION);
            int index = 0;
            preparedStatement.setLong(++index, duration);
            cruise = new Cruise();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    cruise = setCruiseValues(resultSet);
                }
            }
        }
        return Optional.of(cruise);
    }

    @Override
    public void setShip(int id, Ship ship) throws DAOException, SQLException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CruiseMysqlQuery.SET_SHIP);
            int index = 0;
            preparedStatement.setLong(++index, ship.getId());
            preparedStatement.setInt(++index, id);
            preparedStatement.execute();
        }
    }

    @Override
    public void add(Cruise cruise) throws DAOException, SQLException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CruiseMysqlQuery.ADD_CRUISE);
            int index = 0;
            preparedStatement.setInt(++index, cruise.getShipId());
            preparedStatement.setInt(++index, cruise.getDuration());
            preparedStatement.setDate(++index, Date.valueOf(cruise.getStartDate().toLocalDate()));
            preparedStatement.setBoolean(++index, cruise.isPaid());
            preparedStatement.setInt(++index, cruise.getNumberOfPorts());
            preparedStatement.setString(++index, cruise.getStartPort());
            preparedStatement.setString(++index, cruise.getEndPort());
            preparedStatement.execute();
        }
    }

    @Override
    public Optional<Cruise> getById(long id) throws DAOException, SQLException {
        Cruise cruise;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CruiseMysqlQuery.GET_BY_ID);
            int index = 0;
            preparedStatement.setLong(++index, id);
            cruise = new Cruise();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    cruise = setCruiseValues(resultSet);
                }
            }
        }
        return Optional.of(cruise);
    }

    @Override
    public List<Cruise> getAll() throws DAOException, SQLException {
        List<Cruise> cruiseList;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CruiseMysqlQuery.GET_ALL);
            cruiseList = new ArrayList<>();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Cruise cruise = setCruiseValues(resultSet);
                    cruiseList.add(cruise);
                }
            }
        }
        return cruiseList;
    }

    private Cruise setCruiseValues(ResultSet resultSet) throws SQLException {
        Cruise cruise = new Cruise();
        cruise.setShipId(resultSet.getInt("ship_id"));
        cruise.setDuration(resultSet.getInt("duration"));
        cruise.setStartDate(resultSet.getDate("start_day"));
        cruise.setPaid(resultSet.getBoolean("paid"));
        cruise.setStartPort(resultSet.getString("start_port"));
        cruise.setStartPort(resultSet.getString("end_port"));
        return cruise;
    }

    @Override
    public void update(Cruise cruise) throws DAOException, SQLException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CruiseMysqlQuery.UPDATE);
            int index = 0;
            preparedStatement.setInt(++index, cruise.getShipId());
            preparedStatement.setInt(++index, cruise.getDuration());
            preparedStatement.setDate(++index, cruise.getStartDate());
            preparedStatement.setBoolean(++index, cruise.isPaid());
            preparedStatement.setInt(++index, cruise.getNumberOfPorts());
            preparedStatement.setString(++index, cruise.getStartPort());
            preparedStatement.setString(++index, cruise.getEndPort());
            preparedStatement.execute();
        }
    }

    @Override
    public void delete(long id) throws DAOException, SQLException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CruiseMysqlQuery.DELETE);
            int index = 0;
            preparedStatement.setLong(++index, id);
            preparedStatement.execute();
        }
    }
}
