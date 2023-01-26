package dao.impl;

import connection.DataSource;
import dao.CruiseDao;
import exceptions.DbException;
import model.entity.Cruise;
import model.entity.Ship;
import dao.constants.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlCruiseDAO implements CruiseDao {


    @Override
    public List<Cruise> getSorted(String query) throws DbException {
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
        }catch (SQLException e){
            throw new DbException("Cannot get Sorted list of cruises");
        }
        return cruiseList;
    }

    @Override
    public List<Cruise> getByFilters(List<String> filters) throws DbException {
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
        }catch (SQLException e){
            throw new DbException("Cannot get cruise list", e);
        }
        return cruiseList;
    }

    @Override
    public Optional<Cruise> getByDate(Date date) throws DbException {
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
        }catch (SQLException e){
            throw new DbException("Cannot get Cruise by Date", e);
        }
        return Optional.of(cruise);
    }

    @Override
    public Optional<Cruise> getByDuration(int duration) throws DbException {
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
        }catch (SQLException e){
            throw new DbException("Cannot get Cruise by Email", e);
        }
        return Optional.of(cruise);
    }

    @Override
    public void setShip(int id, Ship ship) throws DbException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CruiseMysqlQuery.SET_SHIP);
            int index = 0;
            preparedStatement.setLong(++index, ship.getId());
            preparedStatement.setInt(++index, id);
            preparedStatement.execute();
        }catch (SQLException e){
            throw new DbException("Cannot set Ship", e);
        }
    }

    @Override
    public void add(Cruise cruise) throws DbException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CruiseMysqlQuery.ADD_CRUISE);
            int index = 0;
            setValuesToStatement(cruise, preparedStatement, index);
            preparedStatement.execute();
        }catch (SQLException e){
            throw new DbException("Cannot add Cruise", e);
        }
    }

    private void setValuesToStatement(Cruise cruise, PreparedStatement preparedStatement, int index) throws SQLException {
        preparedStatement.setLong(++index, cruise.getShip().getId());
        preparedStatement.setInt(++index, cruise.getDuration());
        preparedStatement.setDate(++index, Date.valueOf(cruise.getStartDate().toLocalDate()));
        preparedStatement.setDouble(++index, cruise.getPrice());
//        preparedStatement.setInt(++index, cruise.getNumberOfPorts());
    }

    @Override
    public Optional<Cruise> getById(long id) throws DbException {
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
        }catch (SQLException e){
            throw new DbException("Cannot get Cruise by Id", e);
        }
        return Optional.of(cruise);
    }

    @Override
    public List<Cruise> getAll() throws DbException {
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
        }catch (SQLException e){
            throw new DbException("Cannot get all Cruises", e);
        }
        return cruiseList;
    }

    private Cruise setCruiseValues(ResultSet resultSet) throws SQLException, DbException {
        Cruise cruise = new Cruise();
        cruise.setId(resultSet.getInt("cruise"));
        Ship ship = new Ship();
        ship.setId(resultSet.getInt("ship"));
        ship.setName(resultSet.getString("ship_name"));
        ship.setCapacity(resultSet.getInt("capacity"));
        ship.setNumberOfPorts(resultSet.getInt("number_of_ports"));
        try {
            ship.setRoute((new MySqlCityDAO()).getAllCitiesByShipId(ship.getId()));
        } catch (DbException e) {
            throw new DbException("Cannot get All Cities by Ship Id", e);
        }
        cruise.setShip(ship);
        cruise.setDuration(resultSet.getInt("duration"));
        cruise.setPrice(resultSet.getDouble("price"));
        cruise.setStartDate(resultSet.getDate("start_day"));
        cruise.setNumber_of_register_people(resultSet.getInt("number_of_register_people"));
        return cruise;
    }

    @Override
    public void update(Cruise cruise) throws DbException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CruiseMysqlQuery.UPDATE);
            int index = 0;
            setValuesToStatement(cruise, preparedStatement, index);
            preparedStatement.execute();
        }catch (SQLException e){
            throw new DbException("Cannot update Cruise", e);
        }
    }

    @Override
    public void delete(long id) throws DbException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CruiseMysqlQuery.DELETE);
            int index = 0;
            preparedStatement.setLong(++index, id);
            preparedStatement.execute();
        }catch (SQLException e){
            throw new DbException("Cannot update Cruise", e);
        }
    }

    @Override
    public List<Cruise> getCruisePaginationWithFilters(List<String> filters, int cruisePerPage, int pageNum) throws DbException {
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
                setPaginationValues(preparedStatement, cruisePerPage, index, pageNum * cruisePerPage);
            }else{
                if(!filters.get(0).equals("0") && !filters.get(0).isEmpty()) {
                    if (filters.get(1).equals("0")) {
                        query += CruiseMysqlQuery.GET_BY_START_DAY_FILTER;
                        query += CruiseMysqlQuery.GET_PAGINATION;
                        preparedStatement = connection.prepareStatement(query);
                        String startDay = filters.get(0);
                        int index = 0;
                        preparedStatement.setString(++index, startDay);
                        setPaginationValues(preparedStatement, cruisePerPage, index, pageNum * cruisePerPage);
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
                        setPaginationValues(preparedStatement, cruisePerPage, index, pageNum * cruisePerPage);
                    }
                }else{
                    query += CruiseMysqlQuery.GET_PAGINATION;
                    int index = 0;
                    preparedStatement = connection.prepareStatement(query);
                    setPaginationValues(preparedStatement, cruisePerPage, index, pageNum * cruisePerPage);
                }
            }
            cruiseList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            try {
                while (resultSet.next()) {
                    Cruise cruise = setCruiseValues(resultSet);
                    cruiseList.add(cruise);
                }
            } catch (SQLException e) {
                throw new DbException("Cannot set Cruise values", e);
            }
        }catch (SQLException e){
            throw new DbException("Cannot get Cruise catalog", e);
        }
        return cruiseList;
    }

    public static void setPaginationValues(PreparedStatement preparedStatement, int cruisePerPage, int index, int i) throws SQLException {
        preparedStatement.setInt(++index, i);
        preparedStatement.setInt(++index, cruisePerPage);
    }

    @Override
    public int getAmountWithFilters(List<String> filters) throws DbException {
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
            } catch (SQLException e) {
                throw new DbException("Cannot get amount", e);
            }
        }catch (SQLException e) {
            throw new DbException("Cannot get amount", e);
        }
        return amount;
    }

    @Override
    public List<Cruise> getCruisesByUser(long userId, int cruisePerPage, int pageNum) throws DbException {
        List<Cruise> cruiseList;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CruiseMysqlQuery.GET_USER_CRUISES +
                    CruiseMysqlQuery.GET_PAGINATION);
            cruiseList = new ArrayList<>();
            int index = 0;
            preparedStatement.setLong(++index, userId);
            setPaginationValues(preparedStatement, cruisePerPage, index, pageNum * cruisePerPage);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Cruise cruise = setCruiseValues(resultSet);
                    cruiseList.add(cruise);
                }
            }catch (SQLException e) {
                throw new DbException("Cannot set Cruise values", e);
            }
        }catch (SQLException e) {
            throw new DbException("Cannot get Cruises by user", e);
        }
        return cruiseList;
    }

    @Override
    public int getAmountByUser(long userId) throws DbException {
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
            } catch (SQLException e) {
                throw new DbException("Cannot get amount by User", e);
            }
        }catch (SQLException e){
            throw new DbException("Cannot get amount by User", e);
        }
        return amount;
    }

    @Override
    public int getAmount() throws DbException {
        int amount = 0;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = null;
            String query = CruiseMysqlQuery.GET_CRUISE_COUNT;
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            try {
                if(resultSet.next()){
                    amount = resultSet.getInt(1);
                }
            } catch (SQLException e) {
                throw new DbException("Cannot get amount", e);
            }
        }catch (SQLException e){
            throw new DbException("Cannot get amount", e);
        }
        return amount;
    }

    @Override
    public List<Cruise> getCruisePagination(int cruisePerPage, int pageNum) throws DbException {
        List<Cruise> cruiseList;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = null;
            String query = CruiseMysqlQuery.GET_ALL;
                query += CruiseMysqlQuery.GET_PAGINATION;
                preparedStatement = connection.prepareStatement(query);
                int index = 0;
                setPaginationValues(preparedStatement, cruisePerPage, index, pageNum * cruisePerPage);

            cruiseList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            try {
                while (resultSet.next()) {
                    Cruise cruise = setCruiseValues(resultSet);
                    cruiseList.add(cruise);
                }
            } catch (SQLException e) {
                throw new DbException("Cannot set Cruise values", e);
            }
        }catch (SQLException e){
            throw new DbException("Cannot get Cruises");
        }
        return cruiseList;
    }

    @Override
    public void addToUser(long userId, long cruiseId) throws DbException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CruiseMysqlQuery.ADD_CRUISE_TO_USER);
            int index = 0;
            preparedStatement.setLong(++index, userId);
            preparedStatement.setLong(++index, cruiseId);
            preparedStatement.execute();
        }catch (SQLException e){
            throw new DbException("Cannot add Cruise to User", e);
        }
    }

    @Override
    public boolean pairExists(long userId, long cruiseId) throws DbException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CruiseMysqlQuery.FIND_CRUISE_BY_USER);
            int index = 0;
            preparedStatement.setLong(++index, userId);
            preparedStatement.setLong(++index, cruiseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean flag = false;
            try {
                Date date = null;
                if(resultSet.next()){
                    date = resultSet.getDate(1);
                }
                if(date!= null) {
                    flag = date.equals(Date.valueOf(LocalDate.now()));
                }
            } catch (SQLException e) {
                throw new DbException("Cannot get amount", e);
            }
            return flag;
        }catch (SQLException e){
            throw new DbException("Problems in your request", e);
        }
    }

    @Override
    public int getNumberOfRegisteredPeople(long cruiseId) throws DbException {
        int numberOfPeople = 0;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = null;
            String query = CruiseMysqlQuery.GET_NUMBER_OF_REGISTERED_PEOPLE;
            preparedStatement = connection.prepareStatement(query);
            int index = 0;
            preparedStatement.setLong(++index,cruiseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                numberOfPeople = resultSet.getInt(1);
            }
            return numberOfPeople;
        }catch (SQLException e){
            throw new DbException("Cannot get numberOfPeople", e);
        }
    }

    @Override
    public void changeRegisterPeopleAmount(long cruiseId) throws DbException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = null;
            String query = CruiseMysqlQuery.CHANGE_REGISTER_PEOPLE_AMOUNT;
            preparedStatement = connection.prepareStatement(query);
            int index = 0;
            preparedStatement.setLong(++index, cruiseId);
            preparedStatement.execute();
        }catch (SQLException e){
            throw new DbException("Cannot change amount of registered people for Cruise", e);
        }
    }
}
