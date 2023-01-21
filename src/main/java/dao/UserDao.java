package dao;

import exceptions.DAOException;
import model.entity.Cruise;
import model.entity.Role;
import model.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDao extends EntityDao<User>{
    Optional<User> getByEmail(String email, String password) throws DAOException;

    List<User> getSorted(String query) throws DAOException;

    int getNumberOfRecords(String filter) throws DAOException;

    void changePassword(long id, String newPassword) throws DAOException, SQLException;

    void setUserRole(String userEmail, Role role) throws DAOException;

    void registerForCruise(long userId, long eventId) throws DAOException;

    void cancelRegistration(long userId, long eventId) throws DAOException;

    boolean isRegistered(long userId, long eventId) throws DAOException;

    List<User> getUserPagination(int dishPerPage, int pageNum) throws DAOException, SQLException;

    int getAmount() throws DAOException, SQLException;
}
