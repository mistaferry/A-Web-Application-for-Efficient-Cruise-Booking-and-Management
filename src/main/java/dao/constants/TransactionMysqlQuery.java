package dao.constants;

public class TransactionMysqlQuery {
    public static final String ADD_TRANSACTION = "INSERT INTO transaction (timestamp, amount, completed, description, cruise_id) VALUES (?, ?, ?, ?, ?)";

    public static final String GET_BY_ID = "SELECT transaction WHERE id=?";

    public static final String GET_ALL = "SELECT * FROM transaction";

    public static final String UPDATE = "UPDATE transaction WHERE id=?";

    public static final String DELETE = "DELETE transaction WHERE id=?";
}
