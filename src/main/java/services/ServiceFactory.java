package services;

import dao.DAOFactory;
import services.impl.GeneralServiceImpl;
import services.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();
    private GeneralServiceImpl generalService;
    private UserServiceImpl userService;

    public ServiceFactory(){
        this.generalService = new GeneralServiceImpl(
                DAOFactory.getInstance().getUserDao(),
                DAOFactory.getInstance().getCruiseDao(),
                DAOFactory.getInstance().getShipDao(),
                DAOFactory.getInstance().getOrderDao());
        this.userService = new UserServiceImpl(
                DAOFactory.getInstance().getUserDao(),
                DAOFactory.getInstance().getCruiseDao());
    }

    public static ServiceFactory getInstance(){
        return INSTANCE;
    }

    public GeneralService getGeneralService(){
        return generalService;
    }

    public UserServiceImpl getUserService() {
        return userService;
    }
}
