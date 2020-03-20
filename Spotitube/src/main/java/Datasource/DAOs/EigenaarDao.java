package Datasource.DAOs;

import Datasource.util.DatabaseProperties;

import javax.inject.Inject;
import java.sql.*;
import java.util.logging.Logger;

public class EigenaarDao {
    private Logger logger = Logger.getLogger(getClass().getName());

    private DatabaseProperties databaseProperties;
    public EigenaarDao(){
        databaseProperties = new DatabaseProperties();
    }
    public String select(String pk, String kolom) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT "+kolom+" FROM eigenaar WHERE gebruikersNaam = ?");
            statement.setString(1,pk);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getString(kolom);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void update(String pk,String kolom, String nieuweWaarde) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("UPDATE eigenaar SET "+kolom+" = ? WHERE gebruikersNaam = ?");
            statement.setString(1,nieuweWaarde);
            statement.setString(2,pk);
            statement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}