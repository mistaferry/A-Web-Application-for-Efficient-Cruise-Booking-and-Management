package dao.constants;

public class OrderMysqlQuery {

    public static final String GET_ORDERS_AMOUNT = "SELECT COUNT(*) FROM users_has_cruises WHERE user_id=?";

    public static final String GET_USER_ORDERS = "SELECT uhc.user_id as user, cruise.id as cruise,\n" +
            "            cruise.ship_id as ship, cruise.duration, cruise.price, cruise.start_day, cruise.number_of_register_people,\n" +
            "            uhc.paid as paid, uhc.date_of_registration as reg_date, s.name as ship_name, s.capacity as capacity, s.number_of_ports as number_of_ports FROM cruise\n" +
            "            JOIN ship s on s.id = cruise.ship_id JOIN users_has_cruises uhc on cruise.id = uhc.cruise_id\n" +
            "            WHERE uhc.user_id=?\n" +
            "            ORDER BY uhc.date_of_registration DESC\n";
}
