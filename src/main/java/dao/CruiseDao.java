package dao;

import exceptions.DAOException;
import model.entity.City;
import model.entity.Cruise;
import model.entity.Ship;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CruiseDao extends EntityDao<Cruise> {

    List<Cruise> getSorted(String query) throws DAOException, SQLException;

    List<Cruise> getByFilters(List<String> filters) throws DAOException, SQLException;

    Optional<Cruise> getByDate(Date date) throws DAOException, SQLException;

    Optional<Cruise> getByDuration(int duration) throws DAOException, SQLException;

    void setShip(int id, Ship ship) throws DAOException, SQLException;

}
