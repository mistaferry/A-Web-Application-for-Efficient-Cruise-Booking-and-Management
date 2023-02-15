package dao.constants;

public class StaffMysqlQuery {
    public static final String ADD_STAFF = "INSERT INTO staff (id, first_name, surname) VALUES (?, ?, ?)";

    public static final String GET_BY_ID = "SELECT * FROM staff WHERE id=?";

    public static final String GET_ALL = "SELECT * FROM staff";

    public static final String UPDATE = "UPDATE staff SET first_name=?, surname=? WHERE id=?";

    public static final String DELETE = "DELETE staff WHERE id=?";
}
