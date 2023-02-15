package dao;


import exceptions.DbException;
import model.Ship;

import java.util.List;

public interface ShipDao extends EntityDao<Ship> {

    //test of all
    List<Ship> getShips() throws DbException;

    int getCapacityByShipId(long id) throws DbException;;
}
