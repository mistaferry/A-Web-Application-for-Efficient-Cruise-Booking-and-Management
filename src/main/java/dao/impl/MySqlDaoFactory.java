package dao.impl;

import dao.*;

public class MySqlDaoFactory extends DAOFactory {
    private CityDao cityDAO;
    private CruiseDao cruiseDAO;
    private ShipDao shipDAO;
    private StaffDao staffDAO;
    private TransactionDao transactionDAO;
    private UserDao userDAO;
    private OrderDao orderDao;

    @Override
    public CityDao getCityDao() {
        if(cityDAO == null){
            cityDAO = new MySqlCityDAO();
        }
        return cityDAO;
    }

    @Override
    public CruiseDao getCruiseDao() {
        if(cruiseDAO == null){
            cruiseDAO = new MySqlCruiseDAO();
        }
        return cruiseDAO;
    }

    @Override
    public ShipDao getShipDao() {
        if(shipDAO == null){
            shipDAO = new MySqlShipDAO();
        }
        return shipDAO;
    }

    @Override
    public StaffDao getStaffDao() {
        if(staffDAO == null){
            staffDAO = new MySqlStaffDAO();
        }
        return staffDAO;
    }

    @Override
    public TransactionDao getTransactionDao() {
        if(transactionDAO == null){
            transactionDAO = new MySqlTransactionDAO();
        }
        return transactionDAO;
    }

    @Override
    public UserDao getUserDao() {
        if(userDAO == null){
            userDAO = new MySqlUserDAO();
        }
        return userDAO;
    }

    @Override
    public OrderDao getOrderDao() {
        if(orderDao == null){
            orderDao = new MySqlOrderDAO();
        }
        return orderDao;
    }
}
