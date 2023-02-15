package dao.constants;

public class ShipMysqlQuery {
    public static final String ADD_SHIP = "INSERT INTO ship (name, capacity, number_of_ports) VALUES (?, ?, ?)";

    public static final String GET_BY_ID = "SELECT id, name, capacity, number_of_ports FROM ship WHERE id=?";

    public static final String GET_ALL = "SELECT * FROM ship";

    public static final String UPDATE = "UPDATE ship SET name=?, capacity=?, number_of_ports=? WHERE id=?";

    public static final String DELETE = "DELETE ship WHERE id=?";

    public static final String GET_CAPACITY_BY_SHIP_ID = "SELECT capacity FROM ship WHERE id=?";
}
