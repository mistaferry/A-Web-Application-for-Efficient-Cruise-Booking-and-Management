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
    public UserDTO signIn(String login, String password) throws ServiceException {
        UserDTO userDTO;
        try{
            User user = (userDao.getByEmail(login)).get();
            if(user == null){
                throw new Exception("Login not exist");
            }
            userDTO = convertUserToDTO(user);
            return userDTO;
        } catch (Exception e) {
            throw  new ServiceException(e);
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
    public List<CruiseDTO> viewCruiseCatalog() throws ServiceException {
        List<CruiseDTO> cruiseDTOList = new ArrayList<>();
        try{
            List<Cruise> cruiseList = cruiseDao.getAll();
            cruiseList.forEach(cruise -> cruiseDTOList.add(convertCruiseToDTO(cruise)));
            return cruiseDTOList;
        } catch (DAOException | SQLException e) {
            throw  new ServiceException(e);
        }
    }

    @Override
    public List<CruiseDTO> viewCatalog(List<String> filters) throws ServiceException {
        List<CruiseDTO> cruiseDTOList = new ArrayList<>();
        try{
            List<Cruise> cruiseList = cruiseDao.getByFilters(filters);
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
