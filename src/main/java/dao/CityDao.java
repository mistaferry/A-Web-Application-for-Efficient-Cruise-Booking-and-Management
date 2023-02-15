package dao;


import exceptions.DbException;
import model.City;

import java.util.List;

public interface CityDao extends EntityDao<City>{

    List<City> getAllCitiesByShipId(long shipId) throws DbException;
}
