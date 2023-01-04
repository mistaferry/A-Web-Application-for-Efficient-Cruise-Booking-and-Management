package dao.constants;

public class UserMysqlQuery {
    public static final String ADD_USER = "INSERT INTO user (login, password, first_name, surname, role_id, blocked) VALUES (?, ?, ?, ?, ?, ?)";

    public static final String GET_BY_EMAIL = "SELECT * FROM user WHERE login=?";

//    public static final String GET_ALL = "SELECT id, login, password, first_name, surname, blocked, r.id as role_id FROM user\n" +
//            "JOIN role r ON r.id = user.role_id";

    public static final String GET_ALL = "SELECT * FROM user";

    public static final String UPDATE = "UPDATE user WHERE id=?";

    public static final String UPDATE_PASSWORD = "UPDATE user SET password=? WHERE id=";

    public static final String DELETE = "DELETE user WHERE id=?";

    public static final String GET_BY_ID = "SELECT transaction WHERE id=?";
    public static final String GET_NUMBER_OF_RECORDS = "DELETE user WHERE id=?";

    public static final String SET_USER_ROLE = "";

    public static final String REGISTER_FOR_CRUISE = "DELETE user WHERE id=?";

    public static final String CANCEL_REGISTRATION = "DELETE user WHERE id=?";

    public static final String IS_REGISTERED = "DELETE user WHERE id=?";
}
