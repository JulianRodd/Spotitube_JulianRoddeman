package Datasource.DAOs;
import Datasource.DTOs.EigenaarDTO;
import Datasource.Properties.DatabaseConnection;

import javax.inject.Inject;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EigenaarDAO {
    private Logger logger = Logger.getLogger(getClass().getName());
    private DatabaseConnection databaseConnection;

    @Inject
    public void setDatabaseConnection(DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }
    public EigenaarDTO selectEigenaar(String gebruikersnaam) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseConnection.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Eigenaar WHERE gebruikersnaam = ?");
            statement.setString(1, gebruikersnaam);//1 specifies the first parameter in the query i.e. name
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                EigenaarDTO eigenaarDTO = new EigenaarDTO();
                eigenaarDTO.setUser(rs.getString("gebruikersNaam"));
                eigenaarDTO.setPassword(rs.getString("wachtwoord"));
                statement.close();
                connection.close();
                return eigenaarDTO;
            }
        }catch(SQLException | ClassNotFoundException e){
            logger.log(Level.SEVERE, "Error communicating with database " + databaseConnection.connectionString(), e);
        }
        return null;
    }

    public void updateToken(String gebruikersnaam, String token) {
        try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(databaseConnection.connectionString());
        PreparedStatement statement = connection.prepareStatement("UPDATE eigenaar  SET token = ? WHERE gebruikersNaam = ?");
        statement.setString(1, token);
        statement.setString(2, gebruikersnaam);
        statement.execute();
        statement.close();
        connection.close();
        }catch(SQLException | ClassNotFoundException e){
            logger.log(Level.SEVERE, "Error communicating with database " + databaseConnection.connectionString(), e);
        }
    }

//        public boolean nieuweEigenaar(String gebruikersnaam,String wachtwoordHash,String token) throws SQLException {
//                Connection connection = DriverManager.getConnection(databaseConnection.connectionString());
//                PreparedStatement statement = connection.prepareStatement("INSERT INTO Eigenaar  VALUES (?,?,?)");
//                statement.setString(1, gebruikersnaam);//1 specifies the first parameter in the query i.e. name
//                statement.setString(2, wachtwoordHash);
//                statement.setString(3, token);
//                statement.execute();
//                statement.close();
//                connection.close();
//                return true;
//
//        }


}
