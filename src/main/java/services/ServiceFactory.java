package services;

import dao.DAOFactory;
import services.impl.GeneralServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();
    private GeneralServiceImpl generalService;

    public ServiceFactory(){
        this.generalService = new GeneralServiceImpl(DAOFactory.getInstance().getUserDao());
    }

    public static ServiceFactory getInstance(){
        return INSTANCE;
    }

    public GeneralService getGeneralService(){
        return generalService;
    }
}
