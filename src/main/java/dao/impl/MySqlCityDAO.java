package dao.impl;

import connection.DataSource;
import dao.CityDao;
import dao.constants.*;
import exceptions.DAOException;
import model.entity.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlCityDAO implements CityDao {

    @Override
    public void add(City city) throws DAOException, SQLException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CityMysqlQuery.ADD_CITY);
            int index = 0;
            preparedStatement.setString(++index, city.getName());
            preparedStatement.setString(++index, city.getCountry());
            preparedStatement.execute();
        }
    }

    @Override
    public Optional<City> getById(long id) throws DAOException, SQLException {
        City city = null;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CityMysqlQuery.GET_BY_ID);
            int index = 0;
            preparedStatement.setLong(++index, id);
            city = new City();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    city.setName(resultSet.getString("name"));
                    city.setCountry(resultSet.getString("country"));
                }
            }
        }
        return Optional.of(city);
    }

    @Override
    public List<City> getAll() throws DAOException, SQLException {
        List<City> cityList;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CityMysqlQuery.GET_ALL);
            cityList = new ArrayList<>();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    City city = new City();
                    city.setName(resultSet.getString("name"));
                    city.setCountry(resultSet.getString("country"));
                    cityList.add(city);
                }
            }
        }
        return cityList;
    }

    @Override
    public void update(City city) throws DAOException, SQLException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CityMysqlQuery.UPDATE);
            int index = 0;
            preparedStatement.setString(++index, city.getName());
            preparedStatement.setString(++index, city.getCountry());
            preparedStatement.execute();
        }
    }

    @Override
    public void delete(long id) throws DAOException, SQLException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CityMysqlQuery.DELETE);
            int index = 0;
            preparedStatement.setLong(++index, id);
            preparedStatement.execute();
        }
    }
}
