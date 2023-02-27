package dao;


import exceptions.DbException;
import model.City;
import model.Ship;

import java.util.List;

public interface ShipDao extends EntityDao<Ship> {

    void addRoute(long shipId, List<City> route) throws DbException;
    int getCapacityByShipId(long id) throws DbException;;
}
