package dao.constants;

public class CruiseMysqlQuery {
    public static final String ADD_CRUISE = "INSERT INTO cruise (ship_id, duration, start_day, paid, start_port, end_port) VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static final String GET_BY_ID = "SELECT cruise WHERE id=?";

    public static final String GET_ALL = "SELECT id, ship_id, duration, price, start_day, paid, start_port, end_port FROM cruise";

    public static final String UPDATE = "UPDATE cruise WHERE id=?";

    public static final String DELETE = "DELETE cruise WHERE id=?";

    public static final String GET_BY_DURATION = "SELECT cruise WHERE duration=?";

    public static final String GET_BY_DATE = "DELETE cruise WHERE start_day=?";

    public static final String SET_SHIP = "SELECT * FROM cruise SET ship_id=? WHERE id=?";

    public static final String GET_SORTED = "SELECT * FROM cruise ORDER BY ?";

}
