package datasource.connectie;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseProperties {
    private Properties properties;
    private Logger logger;

    public DatabaseProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Can't access property file database.properties", e);
        }
    }

    public String connectionString() {
        return properties.getProperty("databaseurl") + "?user=" + properties.getProperty("user") + "&password=" + properties.getProperty("password") + "&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    }
}
