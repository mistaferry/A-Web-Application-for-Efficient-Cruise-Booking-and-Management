package dao;

import dao.impl.MySqlDaoFactory;

public abstract class DAOFactory {
    private static DAOFactory instance;

    public static synchronized DAOFactory getInstance() {
        if(instance == null){
            instance = new MySqlDaoFactory();
        }
        return instance;
    }

    public abstract CityDao getCityDao();

    public abstract CruiseDao getCruiseDao();

    public abstract ShipDao getShipDao();

    public abstract StaffDao getStaffDao();

    public abstract TransactionDao getTransactionDao();

    public abstract UserDao getUserDao();

}
