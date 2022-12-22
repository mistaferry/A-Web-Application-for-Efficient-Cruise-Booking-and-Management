package dao;

import exceptions.DAOException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface EntityDao<T> {

    void add(T t) throws DAOException, SQLException;

    Optional<T> getById(long id) throws DAOException, SQLException;

    List<T> getAll() throws DAOException, SQLException;

    void update(T t) throws DAOException, SQLException;

    void delete(long id) throws DAOException, SQLException;
}
