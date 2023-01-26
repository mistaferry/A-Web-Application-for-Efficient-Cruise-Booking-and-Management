package dao;


import exceptions.DbException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface EntityDao<T> {

    void add(T t) throws DbException;

    Optional<T> getById(long id) throws DbException;

    List<T> getAll() throws DbException;

    void update(T t) throws DbException;

    void delete(long id) throws DbException;
}
