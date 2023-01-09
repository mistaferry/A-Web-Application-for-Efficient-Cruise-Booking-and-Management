package dao.impl;

import connection.DataSource;
import dao.CruiseDao;
import exceptions.DAOException;
import model.entity.Cruise;
import model.entity.Ship;
import dao.constants.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlCruiseDAO implements CruiseDao {

    private static int executedRows = 0;

    public static int getExecutedRows() {
        return executedRows;
    }

    @Override
    public List<Cruise> getSorted(String query) throws DAOException, SQLException {
        List<Cruise> cruiseList;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CruiseMysqlQuery.GET_SORTED);
            int index = 0;
            preparedStatement.setString(++index, query);
            cruiseList = new ArrayList<>();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Cruise cruise = setCruiseValues(resultSet);
                    cruiseList.add(cruise);
                }
            }
        }
        return cruiseList;
    }

    @Override
    public List<Cruise> getByFilters(List<String> filters) throws DAOException, SQLException {
        List<Cruise> cruiseList;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = null;
            if(filters.get(0).equals("")){
                if(filters.get(1).equals("All")){
                    preparedStatement = connection.prepareStatement(CruiseMysqlQuery.GET_ALL);
                }else if (!filters.get(1).equals("All")){
                    preparedStatement = connection.prepareStatement(CruiseMysqlQuery.GET_BY_DURATION_FILTER);
                    int index = 0;
                    setPaginationValues(preparedStatement, Integer.parseInt(filters.get(2)), index, Integer.parseInt(filters.get(1)));
                }
            }else if(!filters.get(0).equals("") ){
                if(filters.get(1).equals("All")) {
                    preparedStatement = connection.prepareStatement(CruiseMysqlQuery.GET_BY_START_DAY_FILTER);
                    int index = 0;
                    preparedStatement.setString(++index, filters.get(0));
                }else {
                    preparedStatement = connection.prepareStatement(CruiseMysqlQuery.GET_BY_ALL_FILTERS);
                    int index = 0;
                    preparedStatement.setString(++index, filters.get(0));
                    setPaginationValues(preparedStatement, Integer.parseInt(filters.get(2)), index, Integer.parseInt(filters.get(1)));
                }
            }
            cruiseList = new ArrayList<>();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Cruise cruise = setCruiseValues(resultSet);
                    cruiseList.add(cruise);
                }
            }
        }
        return cruiseList;
    }

    @Override
    public Optional<Cruise> getByDate(Date date) throws DAOException, SQLException {
        Cruise cruise;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CruiseMysqlQuery.GET_BY_DATE);
            int index = 0;
            preparedStatement.setDate(++index, date);
            cruise = new Cruise();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    cruise = setCruiseValues(resultSet);
                }
            }
        }
        return Optional.of(cruise);
    }

    @Override
    public Optional<Cruise> getByDuration(int duration) throws DAOException, SQLException {
        Cruise cruise;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CruiseMysqlQuery.GET_BY_DURATION);
            int index = 0;
            preparedStatement.setLong(++index, duration);
            cruise = new Cruise();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    cruise = setCruiseValues(resultSet);
                }
            }
        }
        return Optional.of(cruise);
    }

    @Override
    public void setShip(int id, Ship ship) throws DAOException, SQLException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CruiseMysqlQuery.SET_SHIP);
            int index = 0;
            preparedStatement.setLong(++index, ship.getId());
            preparedStatement.setInt(++index, id);
            preparedStatement.execute();
        }
    }

    @Override
    public void add(Cruise cruise) throws DAOException, SQLException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CruiseMysqlQuery.ADD_CRUISE);
            int index = 0;
            setValuesToStatement(cruise, preparedStatement, index);
            preparedStatement.execute();
        }
    }

    private void setValuesToStatement(Cruise cruise, PreparedStatement preparedStatement, int index) throws SQLException {
        preparedStatement.setLong(++index, cruise.getShip().getId());
        preparedStatement.setInt(++index, cruise.getDuration());
        preparedStatement.setDate(++index, Date.valueOf(cruise.getStartDate().toLocalDate()));
        preparedStatement.setBoolean(++index, cruise.isPaid());
        preparedStatement.setDouble(++index, cruise.getPrice());
//        preparedStatement.setInt(++index, cruise.getNumberOfPorts());
    }

    @Override
    public Optional<Cruise> getById(long id) throws DAOException, SQLException {
        Cruise cruise;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CruiseMysqlQuery.GET_BY_ID);
            int index = 0;
            preparedStatement.setLong(++index, id);
            cruise = new Cruise();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    cruise = setCruiseValues(resultSet);
                }
            }
        }
        return Optional.of(cruise);
    }

    @Override
    public List<Cruise> getAll() throws DAOException, SQLException {
        List<Cruise> cruiseList;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CruiseMysqlQuery.GET_ALL);
            cruiseList = new ArrayList<>();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Cruise cruise = setCruiseValues(resultSet);
                    cruiseList.add(cruise);
                }
            }
        }
        return cruiseList;
    }

    private Cruise setCruiseValues(ResultSet resultSet) throws SQLException, DAOException {
        Cruise cruise = new Cruise();
        cruise.setId(resultSet.getInt("id"));
        Ship ship = new Ship();
        ship.setId(resultSet.getInt("ship_id"));
        ship.setName(resultSet.getString("ship_name"));
        ship.setCapacity(resultSet.getInt("capacity"));
        ship.setNumberOfPorts(resultSet.getInt("number_of_ports"));
        ship.setRoute((new MySqlCityDAO()).getAllCitiesByShipId(ship.getId()));
        cruise.setShip(ship);
        cruise.setDuration(resultSet.getInt("duration"));
        cruise.setPrice(resultSet.getDouble("price"));
        cruise.setStartDate(resultSet.getDate("start_day"));
        cruise.setPaid(resultSet.getBoolean("paid"));
        return cruise;
    }

    @Override
    public void update(Cruise cruise) throws DAOException, SQLException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CruiseMysqlQuery.UPDATE);
            int index = 0;
            setValuesToStatement(cruise, preparedStatement, index);
            preparedStatement.execute();
        }
    }

    @Override
    public void delete(long id) throws DAOException, SQLException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CruiseMysqlQuery.DELETE);
            int index = 0;
            preparedStatement.setLong(++index, id);
            preparedStatement.execute();
        }
    }

    @Override
    public List<Cruise> getCruisePaginationWithFilters(List<String> filters, int dishPerPage, int pageNum) throws DAOException, SQLException {
        List<Cruise> cruiseList;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = null;
            String query = "";
            if(!filters.get(0).isEmpty() && !filters.get(0).equals("All")){ /*дату встановлено*/
                query += CruiseMysqlQuery.GET_ALL + CruiseMysqlQuery.GET_BY_START_DAY_FILTER;
                preparedStatement = setDurationFilter(filters, connection, preparedStatement, query, dishPerPage, pageNum);
            }else{ /*дату встановлено*/
                query += CruiseMysqlQuery.GET_ALL;
                preparedStatement = setDurationFilter(filters, connection, preparedStatement, query, dishPerPage, pageNum);
            }
            cruiseList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            try {
                while (resultSet.next()) {
                    Cruise cruise = setCruiseValues(resultSet);
                    cruiseList.add(cruise);
                }
                resultSet = preparedStatement.executeQuery("SELECT  FOUND_ROWS()");
                if(resultSet.next()){
                    executedRows = resultSet.getInt(1);
                }
            } catch (SQLException | DAOException throwables) {
                throwables.printStackTrace();
            }

        }
        return cruiseList;
    }

    private PreparedStatement setDurationFilter(List<String> filters, Connection connection, PreparedStatement preparedStatement, String query, int dishPerPage, int pageNum) throws SQLException {
        if(filters.get(1).equals("All")){ /*будь-яка тривалість*/
//            query += CruiseMysqlQuery.GET_PAGINATION;
            preparedStatement = connection.prepareStatement(query);
            int index = 0;
            if(!filters.get(0).isEmpty() && !filters.get(0).equals("All")){
                preparedStatement.setString(++index, filters.get(0));
            }
//            setPaginationValues(preparedStatement, dishPerPage, index, pageNum * dishPerPage);

        }else if (!filters.get(1).equals("All")){ /*задано тривалість*/
            int index = 0;
            if(!filters.get(0).isEmpty() && !filters.get(0).equals("All")){
                query += " AND (" + CruiseMysqlQuery.GET_BY_DURATION_FILTER + ") " ;
//                query += CruiseMysqlQuery.GET_PAGINATION;
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(++index, filters.get(0));
            }else{
                query += " WHERE " + CruiseMysqlQuery.GET_BY_DURATION_FILTER;
//                query += CruiseMysqlQuery.GET_PAGINATION;
                preparedStatement = connection.prepareStatement(query);
            }
            preparedStatement.setInt(++index, Integer.parseInt(filters.get(1)));
            preparedStatement.setInt(++index, Integer.parseInt(filters.get(2)));
//            setPaginationValues(preparedStatement, dishPerPage, index, pageNum * dishPerPage);
        }
        return preparedStatement;
    }

    private void setPaginationValues(PreparedStatement preparedStatement, int dishPerPage, int index, int i) throws SQLException {
        preparedStatement.setInt(++index, i);
        preparedStatement.setInt(++index, dishPerPage);
    }

    @Override
    public int getAmountWithFilters(List<String> filters) throws DAOException, SQLException {
        int amount = 0;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = null;
            String query = "";
            if(!filters.get(0).isEmpty()){ /*дату встановлено*/
                query += CruiseMysqlQuery.GET_CRUISE_COUNT + CruiseMysqlQuery.GET_BY_START_DAY_FILTER;
                preparedStatement = setDurationFilterWithoutPagination(filters, connection, preparedStatement, query);
            }else{ /*дату встановлено*/
                query += CruiseMysqlQuery.GET_CRUISE_COUNT;
                preparedStatement = setDurationFilterWithoutPagination(filters, connection, preparedStatement, query);
            }
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    amount = resultSet.getInt(1);
                }
            }
        }
        return amount;
    }

    PreparedStatement setDurationFilterWithoutPagination(List<String> filters, Connection connection, PreparedStatement preparedStatement, String query) throws SQLException {
        if(filters.get(1).equals("All")){ /*будь-яка тривалість*/
            preparedStatement = connection.prepareStatement(query);
            int index = 0;
            if(!filters.get(0).isEmpty()){
                preparedStatement.setString(++index, filters.get(0));
            }

        }else if (!filters.get(1).equals("All")){ /*задано тривалість*/
            int index = 0;
            if(!filters.get(0).isEmpty()){
                query += " AND (" + CruiseMysqlQuery.GET_BY_DURATION_FILTER + ") " ;
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(++index, filters.get(0));
            }else{
                query += " WHERE " + CruiseMysqlQuery.GET_BY_DURATION_FILTER;
                preparedStatement = connection.prepareStatement(query);
            }
            preparedStatement.setInt(++index, Integer.parseInt(filters.get(1)));
            preparedStatement.setInt(++index, Integer.parseInt(filters.get(2)));
        }
        return preparedStatement;
    }
}
