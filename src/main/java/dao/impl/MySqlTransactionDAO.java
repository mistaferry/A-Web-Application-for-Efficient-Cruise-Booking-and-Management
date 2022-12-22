package dao.impl;

import connection.DataSource;
import dao.TransactionDao;
import dao.impl.constants.TransactionMysqlQuery;
import exceptions.DAOException;
import model.entity.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlTransactionDAO implements TransactionDao {

    @Override
    public void add(Transaction transaction) throws DAOException, SQLException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(TransactionMysqlQuery.ADD_TRANSACTION);
            int index = 0;
            preparedStatement.setDate(++index, transaction.getTimestamp());
            preparedStatement.setDouble(++index, transaction.getAmount());
            preparedStatement.setBoolean(++index, transaction.isCompleted());
            preparedStatement.setString(++index, transaction.getDescription());
            preparedStatement.setLong(++index, transaction.getCruise());
            preparedStatement.execute();
        }
    }

    @Override
    public Optional<Transaction> getById(long id) throws DAOException, SQLException {
        Transaction transaction;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(TransactionMysqlQuery.GET_BY_ID);
            int index = 0;
            preparedStatement.setLong(++index, id);
            transaction = new Transaction();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    transaction = getTransactionValues(resultSet);
                }
            }
        }
        return Optional.of(transaction);
    }

    @Override
    public List<Transaction> getAll() throws DAOException, SQLException {
        List<Transaction> transactionList;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(TransactionMysqlQuery.GET_ALL);
            transactionList = new ArrayList<>();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Transaction transaction = getTransactionValues(resultSet);
                    transactionList.add(transaction);
                }
            }
        }
        return transactionList;
    }

    private Transaction getTransactionValues(ResultSet resultSet) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setTimestamp(resultSet.getDate("timestamp"));
        transaction.setAmount(resultSet.getDouble("amount"));
        transaction.setCompleted(resultSet.getBoolean("completed"));
        transaction.setDescription(resultSet.getString("description"));
        transaction.setCruise(resultSet.getInt("cruise_id"));
        return transaction;
    }

    @Override
    public void update(Transaction transaction) throws DAOException, SQLException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(TransactionMysqlQuery.UPDATE);
            int index = 0;
            preparedStatement.setDate(++index, transaction.getTimestamp());
            preparedStatement.setDouble(++index, transaction.getAmount());
            preparedStatement.setBoolean(++index, transaction.isCompleted());
            preparedStatement.setString(++index, transaction.getDescription());
            preparedStatement.setLong(++index, transaction.getCruise());
            preparedStatement.execute();
        }
    }

    @Override
    public void delete(long id) throws DAOException, SQLException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(TransactionMysqlQuery.DELETE);
            int index = 0;
            preparedStatement.setLong(++index, id);
            preparedStatement.execute();
        }
    }
}
