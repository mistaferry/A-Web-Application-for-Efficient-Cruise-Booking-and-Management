package services;

import com.google.protobuf.ServiceException;
import dto.CruiseDTO;
import dto.ShipDTO;
import dto.UserDTO;

import model.entity.User;
import utils.Convertor;

import java.sql.SQLException;
import java.util.List;

public interface GeneralService {

    UserDTO signIn(String login, String password) throws ServiceException;

    void register(String login, String password, String firstName, String surname) throws ServiceException;

    List<UserDTO> viewAllUsers() throws ServiceException;

    List<CruiseDTO> viewUserCruisesWithPagination(long userId, int cruisePerPage, int pageNum) throws ServiceException;

    List<CruiseDTO> viewCatalogWithPagination(List<String> filters, int cruisePerPage, int pageNum) throws ServiceException;

    int getCruiseAmount(List<String> filters) throws ServiceException;

    List<UserDTO> viewAllUsersWithPagination(int cruisePerPage, int pageNum)  throws ServiceException;

    UserDTO getChosenUser(long userId) throws ServiceException;

    void updateCruise(CruiseDTO cruise) throws ServiceException;

    List<CruiseDTO> getCruisesByUser(long userId, int cruisePerPage, int pageNum) throws ServiceException;

    CruiseDTO getChosenCruise(long cruiseId) throws ServiceException;

    List<CruiseDTO> viewCatalog(int cruisePerPage, int pageNum) throws ServiceException;

    List<ShipDTO> getAllShips() throws ServiceException;
}
