package dao;

import dto.UserDTO;
import exceptions.DAOException;
import exceptions.DbException;
import model.entity.Cruise;
import model.entity.Role;
import model.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDao extends EntityDao<User>{
    Optional<User> getByEmail(String email, String password) throws DbException;

    List<User> getSorted(String query) throws DbException;

    int getNumberOfRecords(String filter) throws DbException;

    void changePassword(long id, String newPassword) throws DbException;

    void setUserRole(String userEmail, Role role) throws DbException;

    void registerForCruise(long userId, long eventId) throws DbException;

    void cancelRegistration(long userId, long eventId) throws DbException;

    boolean isRegistered(long userId, long eventId) throws DbException;

    List<User> getUserPagination(int cruisePerPage, int pageNum) throws DbException;

    int getAmount() throws DbException;

    void updateUserValuesByAdmin(User chosenUser, boolean accountStatus, int role) throws DbException;
}
