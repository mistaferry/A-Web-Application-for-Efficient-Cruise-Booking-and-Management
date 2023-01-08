package services.impl;

import dao.UserDao;
import dto.UserDTO;
import exceptions.DAOException;
import model.entity.User;
import services.UserService;
import utils.Convertor;

import java.io.IOException;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;


    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void updateUser(UserDTO user) throws DAOException, SQLException {
        User convertedUser = Convertor.convertDTOToUser(user);
        userDao.update(convertedUser);
    }

    @Override
    public void changePassword(long userId, String oldPassword, String newPassword, String confirmPassword) throws DAOException, SQLException {
        User user = userDao.getById(userId).get();
        if(!user.getPassword().equals(oldPassword)){
            System.out.println("ex");
        }
        if(!newPassword.equals(confirmPassword)){
            System.out.println("ex");
        }
        userDao.changePassword(user.getId(), newPassword);
    }
}
