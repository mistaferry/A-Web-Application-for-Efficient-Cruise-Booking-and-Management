package services;

import com.google.protobuf.ServiceException;
import dao.CityDao;
import dao.CruiseDao;
import dto.CruiseDTO;
import dto.UserDTO;

import java.util.List;

public interface GeneralService {

    UserDTO signIn(String login, String password) throws ServiceException;

    List<UserDTO> viewAllUsers() throws ServiceException;

    List<CruiseDTO> viewCruiseCatalog() throws ServiceException;

    List<CruiseDTO> viewCatalog(List<String> filters) throws ServiceException;

    List<CruiseDTO> viewCatalogWithPagination(List<String> filters, int cruisePerPage, int pageNum) throws ServiceException;
}
