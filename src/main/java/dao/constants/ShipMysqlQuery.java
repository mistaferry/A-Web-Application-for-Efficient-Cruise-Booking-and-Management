package dao.constants;

public class ShipMysqlQuery {
    public static final String ADD_SHIP = "INSERT INTO ship (name, capacity) VALUES (?, ?)";

    public static final String GET_BY_ID = "SELECT * FROM ship WHERE id=?";

    public static final String GET_ALL = "SELECT * FROM ship";

    public static final String UPDATE = "UPDATE ship WHERE id=?";

    public static final String DELETE = "DELETE ship WHERE id=?";
}
