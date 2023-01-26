package dao;

import exceptions.DbException;
import model.entity.Order;

import java.util.List;

public interface OrderDao extends EntityDao<Order>{

    int getAmountByUser(long userId) throws DbException;

    List<Order> getOrdersByUser(long loggedUserId, int cruisePerPage, int pageNum) throws DbException;
}
