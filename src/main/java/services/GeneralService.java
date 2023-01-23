package services;

import com.google.protobuf.ServiceException;
import dto.CruiseDTO;
import dto.ShipDTO;
import dto.UserDTO;
import exceptions.DAOException;
import model.entity.User;
import utils.Convertor;

import java.sql.SQLException;
import java.util.List;

public interface GeneralService {

    UserDTO signIn(String login, String password) throws ServiceException, DAOException;

    void register(String login, String password, String firstName, String surname) throws ServiceException, DAOException;

    List<UserDTO> viewAllUsers() throws ServiceException;

    List<CruiseDTO> viewUserCruisesWithPagination(long userId, int cruisePerPage, int pageNum) throws ServiceException;

    List<CruiseDTO> viewCatalogWithPagination(List<String> filters, int cruisePerPage, int pageNum) throws ServiceException;

    int getCruiseAmount(List<String> filters) throws ServiceException;

    List<UserDTO> viewAllUsersWithPagination(int cruisePerPage, int pageNum)  throws ServiceException;

    UserDTO getChosenUser(long userId) throws ServiceException, DAOException, SQLException;

    void updateCruise(CruiseDTO cruise) throws DAOException, SQLException;

    List<CruiseDTO> getCruisesByUser(long userId, int cruisePerPage, int pageNum) throws ServiceException;

    CruiseDTO getChosenCruise(long cruiseId) throws ServiceException, DAOException, SQLException;

    List<CruiseDTO> viewCatalog(int cruisePerPage, int pageNum) throws ServiceException;

    List<ShipDTO> getAllShips() throws ServiceException;
}
