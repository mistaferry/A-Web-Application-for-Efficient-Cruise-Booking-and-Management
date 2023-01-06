package dao.impl;

import connection.DataSource;
import dao.CityDao;
import dao.ShipDao;
import dao.constants.*;
import exceptions.DAOException;
import model.entity.City;
import model.entity.Ship;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlShipDAO implements ShipDao {
    @Override
    public void add(Ship ship) throws DAOException, SQLException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ShipMysqlQuery.ADD_SHIP);
            int index = 0;
            preparedStatement.setString(++index, ship.getName());
            preparedStatement.setInt(++index, ship.getCapacity());
            preparedStatement.execute();
        }
    }

    @Override
    public Optional<Ship> getById(long id) throws DAOException, SQLException {
        Ship ship;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ShipMysqlQuery.GET_BY_ID);
            int index = 0;
            preparedStatement.setLong(++index, id);
            ship = new Ship();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    List<City> city = (new MySqlCityDAO()).getAllCitiesByShipId(id);
                    ship.setId(resultSet.getLong("id"));
                    ship.setName(resultSet.getString("name"));
                    ship.setCapacity(resultSet.getInt("capacity"));
                    ship.setRoute(city);
                }
            }
        }
        return Optional.of(ship);
    }

    @Override
    public List<Ship> getAll() throws DAOException, SQLException {
        List<Ship> shipList;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ShipMysqlQuery.GET_ALL);
            shipList = new ArrayList<>();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Ship ship = new Ship();
                    ship.setName(resultSet.getString("name"));
                    ship.setCapacity(resultSet.getInt("capacity"));
                    shipList.add(ship);
                }
            }
        }
        return shipList;
    }

    @Override
    public void update(Ship ship) throws DAOException, SQLException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ShipMysqlQuery.UPDATE);
            int index = 0;
            preparedStatement.setString(++index, ship.getName());
            preparedStatement.setInt(++index, ship.getCapacity());
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
