package datasource.daos;

import datasource.connectie.DatabaseProperties;
import domain.Afspeellijst;
import exceptions.eigenexcepties.DatabaseFoutException;

import javax.inject.Inject;
import java.sql.*;

public class AfspeellijstDAO {
    private DatabaseProperties databaseProperties;

    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    public void update(Afspeellijst afspeellijst) {
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("UPDATE afspeellijst SET naam = ?, eigenaar = ? WHERE id = ?");
            statement.setString(1, afspeellijst.getNaam());
            statement.setString(2, afspeellijst.getEigenaar());
            statement.setInt(3, afspeellijst.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseFoutException("Er is een update-fout opgetreden in de tabel afspeellijst");
        }
    }

    public ResultSet selectAll() {
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM afspeellijst ");
            return statement.executeQuery();
        } catch (SQLException e) {
            throw new DatabaseFoutException("Er is een selectAll-fout opgetreden in de tabel afspeellijst");
        }
    }

    public ResultSet select(int pk) {
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM afspeellijst WHERE id = ?");
            statement.setInt(1, pk);
            return statement.executeQuery();
        } catch (SQLException e) {
            throw new DatabaseFoutException("Er is een select-fout opgetreden in de tabel afspeellijst");
        }
    }


    public void delete(int pk) {
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("DELETE FROM afspeellijst WHERE id = ?");
            statement.setInt(1, pk);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseFoutException("Er is een delete-fout opgetreden in de tabel afspeellijst");
        }
    }

    public void insert(Afspeellijst afspeellijst) {
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("INSERT INTO afspeellijst VALUES (?,?,?)");
            statement.setInt(1, afspeellijst.getId());
            statement.setString(2, afspeellijst.getNaam());
            statement.setString(3, afspeellijst.getEigenaar());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseFoutException("Er is een insert-fout opgetreden in de tabel afspeellijst");
        }
    }

    public int getMaxId() {
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT MAX(id) as 'a' FROM afspeellijst");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DatabaseFoutException("Er is een select-fout opgetreden in de tabel afspeellijst");
        }
        return 0;
    }
}
