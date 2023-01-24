package dao;

import exceptions.DAOException;
import exceptions.DbException;
import model.entity.City;
import model.entity.Cruise;
import model.entity.Ship;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CruiseDao extends EntityDao<Cruise> {

    List<Cruise> getSorted(String query) throws DbException;

    List<Cruise> getByFilters(List<String> filters) throws DbException;

    Optional<Cruise> getByDate(Date date) throws DbException;

    Optional<Cruise> getByDuration(int duration) throws DbException;

    void setShip(int id, Ship ship) throws DbException;

    List<Cruise> getCruisePaginationWithFilters(List<String> filters, int cruisePerPage, int pageNum) throws DbException;

    int getAmountWithFilters(List<String> filters) throws DbException;

    List<Cruise> getCruisesByUser(long userId, int cruisePerPage, int pageNum) throws DbException;

    int getAmountByUser(long userId) throws DbException;

    int getAmount() throws DbException;

    List<Cruise> getCruisePagination(int cruisePerPage, int pageNum) throws DbException;
}
