package services;

import com.google.protobuf.ServiceException;
import dto.CruiseDTO;
import dto.UserDTO;
import exceptions.DAOException;

import java.util.List;

public interface GeneralService {

    UserDTO signIn(String login, String password) throws ServiceException, DAOException;

    void register(String login, String password, String firstName, String surname) throws ServiceException, DAOException;

    List<UserDTO> viewAllUsers() throws ServiceException;

    List<CruiseDTO> viewUserCruisesWithPagination(long userId, int cruisePerPage, int pageNum) throws ServiceException;

    List<CruiseDTO> viewCatalogWithPagination(List<String> filters, int cruisePerPage, int pageNum) throws ServiceException;

    int getCruiseAmount(List<String> filters) throws ServiceException;
}
