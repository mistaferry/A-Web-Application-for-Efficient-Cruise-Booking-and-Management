package dao;


import exceptions.DbException;
import model.entity.Ship;
import model.entity.Staff;
import java.util.List;
import java.util.Optional;

public interface ShipDao extends EntityDao<Ship> {

    int getCapacityByShipId(long id) throws DbException;;
}
