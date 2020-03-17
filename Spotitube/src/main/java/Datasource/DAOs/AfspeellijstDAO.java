package Datasource.DAOs;

import Datasource.util.DatabaseProperties;

import java.util.logging.Logger;

public class AfspeellijstDAO {
    private Logger logger = Logger.getLogger(getClass().getName());

    private DatabaseProperties databaseProperties;

    public AfspeellijstDAO(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }
}
