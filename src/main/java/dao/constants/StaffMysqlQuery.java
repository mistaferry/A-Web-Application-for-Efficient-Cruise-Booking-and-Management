package dao.constants;

public class StaffMysqlQuery {
    public static final String ADD_SHIP = "INSERT INTO staff (duration, start_day, paid, number_of_ports, start_port, end_port) VALUES (?, ?, ?, ?, ?, ?)";

    public static final String GET_BY_ID = "SELECT staff WHERE id=?";

    public static final String GET_ALL = "SELECT * FROM staff";

    public static final String UPDATE = "UPDATE staff WHERE id=?";

    public static final String DELETE = "DELETE staff WHERE id=?";
}
