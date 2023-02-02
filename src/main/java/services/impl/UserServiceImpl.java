package services.impl;

import com.google.protobuf.ServiceException;
import dao.CruiseDao;
import dao.UserDao;
import dto.CruiseDTO;
import dto.UserDTO;

import exceptions.DbException;
import model.entity.Cruise;
import model.entity.User;
import services.UserService;
import utils.Convertor;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final CruiseDao cruiseDao;

    public UserServiceImpl(UserDao userDao, CruiseDao cruiseDao) {
        this.userDao = userDao;
        this.cruiseDao = cruiseDao;
    }

    @Override
    public void updateUser(UserDTO user) throws ServiceException {
        User convertedUser = Convertor.convertDTOToUser(user);
        try {
            userDao.update(convertedUser);
        }  catch (DbException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void changePassword(String login, String oldPassword, String newPassword, String confirmPassword) throws ServiceException {
        try {
            User user = userDao.getByEmail(login, oldPassword).get();
            if(!newPassword.equals(confirmPassword)){
                throw new ServiceException("Passwords aren't equal");
            }
            userDao.changePassword(user.getId(), newPassword);
        }  catch (DbException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public CruiseDTO getChosenCruise(long cruiseId) throws ServiceException {
        Cruise cruise = null;
        try {
            cruise = cruiseDao.getById(cruiseId).get();
        }  catch (DbException e) {
            throw new ServiceException(e);
        }
        return Convertor.convertCruiseToDTO(cruise);
    }
}
