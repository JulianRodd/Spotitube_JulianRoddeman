package datasource.daos;

import datasource.connectie.DatabaseProperties;
import domain.Eigenaar;
import exceptions.eigenexcepties.DatabaseFoutException;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.*;

@Default
public class EigenaarDAO {
    private DatabaseProperties databaseProperties;

    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    public ResultSet select(String pk) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM eigenaar WHERE gebruikersNaam = ?");
            statement.setString(1, pk);
            return statement.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            throw new DatabaseFoutException("Er is een select-fout opgetreden in de tabel eigenaar");
        }
    }

    public void update(Eigenaar eigenaar) {
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("UPDATE eigenaar SET  wachtwoord = ?, token = ? WHERE gebruikersNaam = ?");
            statement.setString(1, eigenaar.getWachtwoord());
            statement.setString(2, eigenaar.getToken());
            statement.setString(3, eigenaar.getGebruikersnaam());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseFoutException("\"Er is een update-fout opgetreden in de tabel eigenaar\"");
        }
    }

    public ResultSet getEigenaarMetToken(String token) {
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM eigenaar WHERE token = ?");
            statement.setString(1, token);
            return statement.executeQuery();
        } catch (SQLException e) {
            throw new DatabaseFoutException("Er is een select-fout opgetreden in de tabel eigenaar");
        }
    }
}
