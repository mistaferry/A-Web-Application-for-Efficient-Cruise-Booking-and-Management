package services;

import dto.CruiseDTO;
import dto.UserDTO;
import exceptions.DAOException;
import model.entity.User;

import java.sql.SQLException;

public interface UserService {
    void updateUser(UserDTO user) throws DAOException, SQLException;

    void changePassword(long userId, String oldPassword, String newPassword, String confirmPassword) throws DAOException, SQLException;

    CruiseDTO getChosenCruise(long cruiseId) throws DAOException, SQLException;
}
