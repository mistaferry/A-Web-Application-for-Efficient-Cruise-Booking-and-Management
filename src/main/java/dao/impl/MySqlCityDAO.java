package dao.impl;

import connection.DataSource;
import dao.CityDao;
import dao.constants.*;

import exceptions.DbException;
import model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlCityDAO implements CityDao {

    @Override
    public void add(City city) throws DbException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CityMysqlQuery.ADD_CITY);
            int index = 0;
            preparedStatement.setLong(++index, city.getId());
            preparedStatement.setString(++index, city.getName());
            preparedStatement.setString(++index, city.getCountry());
            preparedStatement.execute();
        }catch (SQLException e){
            throw new DbException("Cannot add City", e);
        }
    }

    @Override
    public Optional<City> getById(long id) throws DbException {
        City city = null;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CityMysqlQuery.GET_BY_ID);
            int index = 0;
            preparedStatement.setLong(++index, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    city = new City();
                    city.setId(id);
                    city.setName(resultSet.getString("name"));
                    city.setCountry(resultSet.getString("country"));
                }
            }
        }catch (SQLException e){
            throw new DbException("Cannot get City by Id", e);
        }
        return Optional.ofNullable(city);
    }

    @Override
    public List<City> getAll() throws DbException {
        List<City> cityList;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CityMysqlQuery.GET_ALL);
            cityList = new ArrayList<>();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    City city = new City();
                    city.setId(resultSet.getLong("id"));
                    city.setName(resultSet.getString("name"));
                    city.setCountry(resultSet.getString("country"));
                    cityList.add(city);
                }
            }
        }catch (SQLException e){
            throw new DbException("Cannot get All Cities", e);
        }
        return cityList;
    }

    private City getCityFromDB(ResultSet resultSet) throws DbException {
        long city_id = 0;
        try {
            city_id = resultSet.getLong("city_id");
        } catch (SQLException e){
            throw new DbException("Cannot get City from Db", e);
        }
        return (new MySqlCityDAO()).getById(city_id).get();
    }

    @Override
    public void update(City city) throws DbException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CityMysqlQuery.UPDATE);
            int index = 0;
            preparedStatement.setString(++index, city.getName());
            preparedStatement.setString(++index, city.getCountry());
            preparedStatement.setLong(++index, city.getId());
            preparedStatement.execute();
        }catch (SQLException e){
            throw new DbException("Cannot update City", e);
        }
    }

    @Override
    public void delete(long id) throws DbException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CityMysqlQuery.DELETE);
            int index = 0;
            preparedStatement.setLong(++index, id);
            preparedStatement.execute();
        }catch (SQLException e){
            throw new DbException("Cannot delete City", e);
        }
    }

    @Override
    public List<City> getAllCitiesByShipId(long shipId) throws DbException {
        List<City> cityList = null;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CityMysqlQuery.GET_ALL_CITIES_BY_SHIP_ID);
            int index = 0;
            preparedStatement.setLong(++index, shipId);
            cityList = new ArrayList<>();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    City city = getCityFromDB(resultSet);
                    cityList.add(city);
                }
            }
        }catch (SQLException e){
            throw new DbException("Cannot get all Cities", e);
        }
        return cityList;
    }
}
