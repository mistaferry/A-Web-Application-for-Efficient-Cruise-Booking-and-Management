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
                while (resultSet.next()) {
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
                while (resultSet.next()) {
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
        cruise.setId(resultSet.getInt("cruise"));
        Ship ship = new Ship();
        ship.setId(resultSet.getInt("ship"));
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
            String query = CruiseMysqlQuery.GET_ALL;
            if((filters.get(0).isEmpty() || filters.get(0).equals("0"))
                    && !filters.get(1).equals("0")){
                query += "WHERE ";
                query += CruiseMysqlQuery.GET_BY_DURATION_FILTER;
                query += CruiseMysqlQuery.GET_PAGINATION;
                preparedStatement = connection.prepareStatement(query);
                int durationStart = 1;
                int durationEnd = Integer.parseInt(filters.get(1));
                int index = 0;
                preparedStatement.setInt(++index, durationStart);
                preparedStatement.setInt(++index, durationEnd);
                setPaginationValues(preparedStatement, dishPerPage, index, pageNum * dishPerPage);
            }else{
                if(!filters.get(0).equals("0") && !filters.get(0).isEmpty()) {
                    if (filters.get(1).equals("0")) {
                        query += CruiseMysqlQuery.GET_BY_START_DAY_FILTER;
                        query += CruiseMysqlQuery.GET_PAGINATION;
                        preparedStatement = connection.prepareStatement(query);
                        String startDay = filters.get(0);
                        int index = 0;
                        preparedStatement.setString(++index, startDay);
                        setPaginationValues(preparedStatement, dishPerPage, index, pageNum * dishPerPage);
                    } else {
                        query += CruiseMysqlQuery.GET_BY_START_DAY_FILTER +
                                " AND (" + CruiseMysqlQuery.GET_BY_DURATION_FILTER + ") ";
                        query += CruiseMysqlQuery.GET_PAGINATION;
                        preparedStatement = connection.prepareStatement(query);
                        String startDay = filters.get(0);
                        int durationStart = 1;
                        int durationEnd = Integer.parseInt(filters.get(1));
                        int index = 0;
                        preparedStatement.setString(++index, startDay);
                        preparedStatement.setInt(++index, durationStart);
                        preparedStatement.setInt(++index, durationEnd);
                        setPaginationValues(preparedStatement, dishPerPage, index, pageNum * dishPerPage);
                    }
                }else{
                    query += CruiseMysqlQuery.GET_PAGINATION;
                    int index = 0;
                    preparedStatement = connection.prepareStatement(query);
                    setPaginationValues(preparedStatement, dishPerPage, index, pageNum * dishPerPage);
                }
            }
            cruiseList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            try {
                while (resultSet.next()) {
                    Cruise cruise = setCruiseValues(resultSet);
                    cruiseList.add(cruise);
                }
            } catch (SQLException | DAOException throwables) {
                throwables.printStackTrace();
            }

        }
        return cruiseList;
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
            String query = CruiseMysqlQuery.GET_CRUISE_COUNT;
            if((filters.get(0).isEmpty() || filters.get(0).equals("0"))
                    && !filters.get(1).equals("0")){
                query += "WHERE ";
                query += CruiseMysqlQuery.GET_BY_DURATION_FILTER;
                preparedStatement = connection.prepareStatement(query);
                int durationStart = 1;
                int durationEnd = Integer.parseInt(filters.get(1));
                int index = 0;
                preparedStatement.setInt(++index, durationStart);
                preparedStatement.setInt(++index, durationEnd);
            }else{
                if(!filters.get(0).equals("0") && !filters.get(0).isEmpty()) {
                    if (filters.get(1).equals("0")) {
                        query += CruiseMysqlQuery.GET_BY_START_DAY_FILTER;
                        preparedStatement = connection.prepareStatement(query);
                        String startDay = filters.get(0);
                        int index = 0;
                        preparedStatement.setString(++index, startDay);
                    } else {
                        query += CruiseMysqlQuery.GET_BY_START_DAY_FILTER +
                                " AND (" + CruiseMysqlQuery.GET_BY_DURATION_FILTER + ") ";
                        preparedStatement = connection.prepareStatement(query);
                        String startDay = filters.get(0);
                        int durationStart = 1;
                        int durationEnd = Integer.parseInt(filters.get(1));
                        int index = 0;
                        preparedStatement.setString(++index, startDay);
                        preparedStatement.setInt(++index, durationStart);
                        preparedStatement.setInt(++index, durationEnd);
                    }
                }else{
                    preparedStatement = connection.prepareStatement(query);
                }
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            try {
                if(resultSet.next()){
                    amount = resultSet.getInt(1);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return amount;
    }

    @Override
    public List<Cruise> getCruisesByUser(long userId, int dishPerPage, int pageNum) throws DAOException, SQLException {
        List<Cruise> cruiseList;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CruiseMysqlQuery.GET_USER_CRUISES +
                    CruiseMysqlQuery.GET_PAGINATION);
            cruiseList = new ArrayList<>();
            int index = 0;
            preparedStatement.setLong(++index, userId);
            setPaginationValues(preparedStatement, dishPerPage, index, pageNum * dishPerPage);
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
    public int getAmountByUser(long userId) throws DAOException, SQLException {
        int amount = 0;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = null;
            String query = CruiseMysqlQuery.GET_USER_CRUISE_COUNT;
            preparedStatement = connection.prepareStatement(query);
            int index = 0;
            preparedStatement.setLong(++index, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            try {
                if(resultSet.next()){
                    amount = resultSet.getInt(1);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return amount;
    }
}
