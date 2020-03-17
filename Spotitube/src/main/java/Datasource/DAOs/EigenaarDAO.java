package Datasource.DAOs;

import Datasource.util.DatabaseProperties;

import javax.inject.Inject;
import java.sql.*;
import java.util.logging.Logger;

public class EigenaarDAO {
    private Logger logger = Logger.getLogger(getClass().getName());

    private DatabaseProperties databaseProperties;

    public String selectWachtwoord(String gebruikersnaam) {
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT wachtwoord FROM eigenaar WHERE gebruikersNaam = ?");
            statement.setString(1,gebruikersnaam);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getString("wachtwoord");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void updateToken(String gebruikersnaam, String token) {
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("UPDATE eigenaar SET token = ? WHERE gebruikersNaam = ?");
            statement.setString(1,token);
            statement.setString(2,gebruikersnaam);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties){
        this.databaseProperties = databaseProperties;
    }
}
