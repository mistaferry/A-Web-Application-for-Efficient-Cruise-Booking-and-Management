package dao;


import exceptions.DbException;
import model.City;
import model.Ship;
import model.Staff;

import java.util.List;

public interface ShipDao extends EntityDao<Ship> {

    void addRoute(long shipId, List<City> route) throws DbException;

    void addStaff(long shipId, List<Staff> staff) throws DbException;

    int getCapacityByShipId(long id) throws DbException;;
}
