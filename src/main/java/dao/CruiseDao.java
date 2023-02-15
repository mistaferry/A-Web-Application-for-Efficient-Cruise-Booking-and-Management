package dao;

import exceptions.DbException;
import model.Cruise;
import model.Ship;

import java.sql.Date;
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

    int getAmountByUser(long userId) throws DbException;

    int getAmount() throws DbException;

    List<Cruise> getCruisePagination(int cruisePerPage, int pageNum) throws DbException;

    void addToUser(long userId, long cruiseId) throws DbException;

    boolean pairExists(long userId, long cruiseId) throws DbException;

    int getNumberOfRegisteredPeople(long cruiseId) throws DbException;

    void changeRegisterPeopleAmount(long cruiseId) throws DbException;
}
