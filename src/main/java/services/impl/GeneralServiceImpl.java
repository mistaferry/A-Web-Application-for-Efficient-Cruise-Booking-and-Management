package services.impl;

import com.google.protobuf.ServiceException;
import dao.CruiseDao;
import dao.UserDao;
import dto.CruiseDTO;
import dto.UserDTO;
import exceptions.DAOException;
import model.entity.Cruise;
import model.entity.User;
import services.GeneralService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static utils.Convertor.convertCruiseToDTO;
import static utils.Convertor.convertUserToDTO;

public class GeneralServiceImpl implements GeneralService {
    private final UserDao userDao;
    private final CruiseDao cruiseDao;

    public GeneralServiceImpl(UserDao userDao, CruiseDao cruiseDao) {
        this.userDao = userDao;
        this.cruiseDao = cruiseDao;
    }

    @Override
    public UserDTO signIn(String login, String password) throws DAOException {
        UserDTO userDTO;
        User user = (userDao.getByEmail(login, password)).get();
        userDTO = convertUserToDTO(user);
        return userDTO;
    }

    @Override
    public void register(String login, String password, String firstName, String surname) throws DAOException {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setSurname(surname);
        try {
            userDao.add(user);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<UserDTO> viewAllUsers() throws ServiceException {
        List<UserDTO> userDTOList = new ArrayList<>();
        try{
            List<User> userList = userDao.getAll();
            userList.forEach(user -> userDTOList.add(convertUserToDTO(user)));
            return userDTOList;
        } catch (DAOException | SQLException e) {
            throw  new ServiceException(e);
        }
    }

    @Override
    public List<CruiseDTO> viewUserCruisesWithPagination(long userId, int cruisePerPage, int pageNum) throws ServiceException {
        List<CruiseDTO> cruiseDTOList = new ArrayList<>();
        try{
            List<Cruise> cruiseList = cruiseDao.getCruisesByUser(userId, cruisePerPage, pageNum);
            cruiseList.forEach(cruise -> cruiseDTOList.add(convertCruiseToDTO(cruise)));
            return cruiseDTOList;
        } catch (DAOException | SQLException e) {
            throw  new ServiceException(e);
        }
    }

    @Override
    public List<CruiseDTO> viewCatalogWithPagination(List<String> filters, int cruisePerPage, int pageNum) throws ServiceException {
        List<CruiseDTO> cruiseDTOList = new ArrayList<>();
        try{
            List<Cruise> cruiseList = cruiseDao.getCruisePaginationWithFilters(filters, cruisePerPage, pageNum);
            cruiseList.forEach(cruise -> cruiseDTOList.add(convertCruiseToDTO(cruise)));
            return cruiseDTOList;
        } catch (DAOException | SQLException e) {
            throw  new ServiceException(e);
        }
    }

    @Override
    public int getCruiseAmount(List<String> filters) throws ServiceException {
        int amount;
        try{
            amount = cruiseDao.getAmountWithFilters(filters);
            return amount;
        } catch (DAOException | SQLException e) {
            throw  new ServiceException(e);
        }
    }
}
