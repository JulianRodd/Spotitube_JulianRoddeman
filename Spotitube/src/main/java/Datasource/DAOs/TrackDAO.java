package Datasource.DAOs;

import Datasource.util.DatabaseProperties;

import java.util.logging.Logger;

public class TrackDAO {
    private Logger logger = Logger.getLogger(getClass().getName());

    private DatabaseProperties databaseProperties;

    public TrackDAO(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }
}
