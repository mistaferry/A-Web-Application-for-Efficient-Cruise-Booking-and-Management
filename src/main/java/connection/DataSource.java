package connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        Properties properties = getProperties();
        config.setJdbcUrl(properties.getProperty("url"));
        config.setUsername(properties.getProperty("user"));
        config.setPassword(properties.getProperty("password"));
        config.setDriverClassName(properties.getProperty("driver"));
        config.addDataSourceProperty( "cachePrepStmts" , properties.getProperty("cachePrepStmts"));
        config.addDataSourceProperty( "prepStmtCacheSize" ,  properties.getProperty("prepStmtCacheSize"));
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , properties.getProperty("prepStmtCacheSqlLimit"));
        ds = new HikariDataSource( config );
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private static Properties getProperties() {
        Properties props = new Properties();
        try {
            props.load(DataSource.class.getResourceAsStream("/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}
