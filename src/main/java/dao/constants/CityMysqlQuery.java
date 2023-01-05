package dao.constants;

public final class CityMysqlQuery {
    public static final String ADD_CITY = "INSERT INTO city (name, country, ship_id) VALUES (?,?,?)";

    public static final String GET_BY_ID = "SELECT * FROM city WHERE id=?";

    public static final String GET_ALL = "SELECT * FROM city";

    public static final String UPDATE = "UPDATE city WHERE id=?";

    public static final String DELETE = "DELETE city WHERE id=?";
}
