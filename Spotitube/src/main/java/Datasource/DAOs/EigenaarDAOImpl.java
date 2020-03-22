package Datasource.DAOs;

import Datasource.util.DatabaseProperties;
import Domain.Eigenaar;

import javax.enterprise.inject.Default;
import java.sql.*;
import java.util.logging.Logger;

@Default
public class EigenaarDAOImpl implements EigenaarDAO {
    private Logger logger = Logger.getLogger(getClass().getName());

    private DatabaseProperties databaseProperties;
    public EigenaarDAOImpl(){
        databaseProperties = new DatabaseProperties();
    }
    @Override
    public Eigenaar select(String pk) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM eigenaar WHERE gebruikersNaam = ?");
            statement.setString(1,pk);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Eigenaar eigenaar = new Eigenaar();
                eigenaar.setGebruikersnaam(resultSet.getString("gebruikersnaam"));
                eigenaar.setWachtwoord(resultSet.getString("wachtwoord"));
                return eigenaar;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void update(Eigenaar eigenaar) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("UPDATE eigenaar SET  wachtwoord = ?, token = ? WHERE gebruikersNaam = ?");
            statement.setString(1,eigenaar.getWachtwoord());
            statement.setString(2,eigenaar.getToken());
            statement.setString(3,eigenaar.getGebruikersnaam());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Eigenaar getEigenaarMetToken(String token) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM eigenaar WHERE token = ?");
            statement.setString(1,token);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Eigenaar eigenaar = new Eigenaar();
                eigenaar.setGebruikersnaam(resultSet.getString("gebruikersnaam"));
                eigenaar.setWachtwoord(resultSet.getString("wachtwoord"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
