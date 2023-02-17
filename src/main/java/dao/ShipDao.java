package dao;


import exceptions.DbException;
import model.Ship;

import java.util.List;

public interface ShipDao extends EntityDao<Ship> {

    int getCapacityByShipId(long id) throws DbException;;
}
