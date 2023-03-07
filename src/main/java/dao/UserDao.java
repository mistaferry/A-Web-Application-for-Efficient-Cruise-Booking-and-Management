package dao;

import exceptions.DbException;
import model.Role;
import model.User;

import java.io.InputStream;
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

    void addDocumentsToUser(long userId, InputStream inputStream) throws DbException;
}
