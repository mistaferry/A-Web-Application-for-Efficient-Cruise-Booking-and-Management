package dao.impl;

import connection.DataSource;
import dao.CityDao;
import dao.CruiseDao;
import dao.ShipDao;
import exceptions.DAOException;
import model.entity.City;
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
            setValuesToStatement(cruise, preparedStatement, index);
            preparedStatement.execute();
        }
    }

    private void setValuesToStatement(Cruise cruise, PreparedStatement preparedStatement, int index) throws SQLException {
        preparedStatement.setLong(++index, cruise.getShip().getId());
        preparedStatement.setInt(++index, cruise.getDuration());
        preparedStatement.setDate(++index, Date.valueOf(cruise.getStartDate().toLocalDate()));
        preparedStatement.setBoolean(++index, cruise.isPaid());
        preparedStatement.setDouble(++index, cruise.getPrice());
        preparedStatement.setInt(++index, cruise.getNumberOfPorts());
        City startPort = cruise.getStartPort();
        preparedStatement.setLong(++index, startPort.getId());
        City endPort = cruise.getEndPort();
        preparedStatement.setLong(++index, endPort.getId());
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
                while (resultSet.next()) {
                    Cruise cruise = setCruiseValues(resultSet);
                    cruiseList.add(cruise);
                }
            }
        }
        return cruiseList;
    }

    private Cruise setCruiseValues(ResultSet resultSet) throws SQLException, DAOException {
        Cruise cruise = new Cruise();
        cruise.setId(resultSet.getInt("id"));

        int shipId = resultSet.getInt("ship_id");
        ShipDao shipDao = new MySqlShipDAO();
        Ship ship = (shipDao.getById(shipId)).get();
        cruise.setShip(ship);

        cruise.setDuration(resultSet.getInt("duration"));
        cruise.setPrice(resultSet.getDouble("price"));
        cruise.setStartDate(resultSet.getDate("start_day"));
        cruise.setPaid(resultSet.getBoolean("paid"));

        int startPortId = resultSet.getInt("start_port");
        CityDao cityDao = new MySqlCityDAO();
        City startPort = (cityDao.getById(startPortId)).get();
        cruise.setStartPort(startPort);

        int endPortId = resultSet.getInt("end_port");
        City endPort = (cityDao.getById(endPortId)).get();
        cruise.setEndPort(endPort);

        return cruise;
    }

    @Override
    public void update(Cruise cruise) throws DAOException, SQLException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CruiseMysqlQuery.UPDATE);
            int index = 0;
            setValuesToStatement(cruise, preparedStatement, index);
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
