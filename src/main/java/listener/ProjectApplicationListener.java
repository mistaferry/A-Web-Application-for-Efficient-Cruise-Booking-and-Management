package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import connection.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class ProjectApplicationListener implements ServletContextListener {
    private final Logger LOGGER = LoggerFactory.getLogger(ProjectApplicationListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("Webapp 'Cruise Company' was started.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            DataSource.getConnection().close();
        } catch (SQLException exc) {
            LOGGER.error("ConnectionPool closing error: " + exc.getMessage(), exc);
        }
        LOGGER.info("Webapp 'Cruise Company' was closed");
    }
}