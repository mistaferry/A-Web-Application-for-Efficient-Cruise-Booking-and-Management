package services.impl;

import com.google.protobuf.ServiceException;
import dao.CruiseDao;
import dao.ShipDao;
import dao.UserDao;
import dto.CruiseDTO;
import dto.ShipDTO;
import dto.UserDTO;
import exceptions.DbException;
import model.entity.Cruise;
import model.entity.Ship;
import model.entity.User;
import services.GeneralService;
import utils.Convertor;

import java.util.ArrayList;
import java.util.List;

import static utils.Convertor.*;

public class GeneralServiceImpl implements GeneralService {
    private final UserDao userDao;
    private final CruiseDao cruiseDao;
    private final ShipDao shipDao;

    public GeneralServiceImpl(UserDao userDao, CruiseDao cruiseDao, ShipDao shipDao) {
        this.userDao = userDao;
        this.cruiseDao = cruiseDao;
        this.shipDao = shipDao;
    }

    @Override
    public UserDTO signIn(String login, String password) throws ServiceException {
        UserDTO userDTO;
        User user = null;
        try {
            user = (userDao.getByEmail(login, password)).get();
            userDTO = convertUserToDTO(user);
        } catch (DbException e) {
            throw new ServiceException(e);
        }
        return userDTO;
    }

    @Override
    public void register(String login, String password, String firstName, String surname) throws ServiceException {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setSurname(surname);
        try {
            userDao.add(user);
        } catch (DbException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<UserDTO> viewAllUsers() throws ServiceException {
        List<UserDTO> userDTOList = new ArrayList<>();
        try {
            List<User> userList = userDao.getAll();
            userList.forEach(user -> userDTOList.add(convertUserToDTO(user)));
            return userDTOList;
        } catch (DbException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<CruiseDTO> viewUserCruisesWithPagination(long userId, int cruisePerPage, int pageNum) throws ServiceException {
        List<CruiseDTO> cruiseDTOList = new ArrayList<>();
        try {
            List<Cruise> cruiseList = cruiseDao.getCruisesByUser(userId, cruisePerPage, pageNum);
            cruiseList.forEach(cruise -> cruiseDTOList.add(convertCruiseToDTO(cruise)));
            return cruiseDTOList;
        } catch (DbException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<CruiseDTO> viewCatalogWithPagination(List<String> filters, int cruisePerPage, int pageNum) throws ServiceException {
        List<CruiseDTO> cruiseDTOList = new ArrayList<>();
        try {
            List<Cruise> cruiseList = cruiseDao.getCruisePaginationWithFilters(filters, cruisePerPage, pageNum);
            cruiseList.forEach(cruise -> cruiseDTOList.add(convertCruiseToDTO(cruise)));
            return cruiseDTOList;
        } catch (DbException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getCruiseAmount(List<String> filters) throws ServiceException {
        int amount;
        try {
            amount = cruiseDao.getAmountWithFilters(filters);
            return amount;
        } catch (DbException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<UserDTO> viewAllUsersWithPagination(int cruisePerPage, int pageNum) throws ServiceException {
        List<UserDTO> userDTOList = new ArrayList<>();
        try {
            List<User> userList = userDao.getUserPagination(cruisePerPage, pageNum);
            userList.forEach(user -> userDTOList.add(convertUserToDTO(user)));
            return userDTOList;
        } catch (DbException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public UserDTO getChosenUser(long userId) throws ServiceException {
        User user = null;
        try {
            user = userDao.getById(userId).get();
        } catch (DbException e) {
            throw new ServiceException(e);
        }
        return Convertor.convertUserToDTO(user);
    }

    @Override
    public void updateCruise(CruiseDTO cruise) throws ServiceException {
        Cruise convertedCruise = Convertor.convertDTOToCruise(cruise);
        try {
            cruiseDao.update(convertedCruise);
        } catch (DbException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<CruiseDTO> getCruisesByUser(long userId, int cruisePerPage, int pageNum) throws ServiceException {
        List<CruiseDTO> cruiseDTO = new ArrayList<>();
        try {
            List<Cruise> cruiseList = cruiseDao.getCruisesByUser(userId, cruisePerPage, pageNum);
            cruiseList.forEach(cruise -> cruiseDTO.add(convertCruiseToDTO(cruise)));
            return cruiseDTO;
        } catch (DbException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public CruiseDTO getChosenCruise(long cruiseId) throws ServiceException {
        Cruise cruise = null;
        try {
            cruise = cruiseDao.getById(cruiseId).get();
        } catch (DbException e) {
            throw new ServiceException(e);
        }
        return Convertor.convertCruiseToDTO(cruise);
    }

    @Override
    public List<CruiseDTO> viewCatalog(int cruisePerPage, int pageNum) throws ServiceException {
        List<CruiseDTO> cruiseDTOList = new ArrayList<>();
        try {
            List<Cruise> cruiseList = cruiseDao.getCruisePagination(cruisePerPage, pageNum);
            cruiseList.forEach(cruise -> cruiseDTOList.add(convertCruiseToDTO(cruise)));
            return cruiseDTOList;
        } catch (DbException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ShipDTO> getAllShips() throws ServiceException {
        List<ShipDTO> shipDTOList = new ArrayList<>();
        try {
            List<Ship> shipList = shipDao.getAll();
            shipList.forEach(ship -> shipDTOList.add(convertShipToDTO(ship)));
            return shipDTOList;
        } catch (DbException e) {
            throw new ServiceException(e);
        }
    }
}
