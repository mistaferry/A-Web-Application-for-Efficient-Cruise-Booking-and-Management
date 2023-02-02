package services;

import com.google.protobuf.ServiceException;
import dto.CruiseDTO;
import dto.UserDTO;

import model.entity.User;

import java.sql.SQLException;

public interface UserService {
    void updateUser(UserDTO user) throws ServiceException;

    void changePassword(String login, String oldPassword, String newPassword, String confirmPassword) throws ServiceException;

    CruiseDTO getChosenCruise(long cruiseId) throws ServiceException;
}
