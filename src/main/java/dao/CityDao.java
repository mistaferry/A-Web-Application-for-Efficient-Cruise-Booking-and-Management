package dao;

import exceptions.DAOException;
import exceptions.DbException;
import model.entity.City;

import java.sql.SQLException;
import java.util.List;

public interface CityDao extends EntityDao<City>{

    List<City> getAllCitiesByShipId(long shipId) throws DbException;
}
