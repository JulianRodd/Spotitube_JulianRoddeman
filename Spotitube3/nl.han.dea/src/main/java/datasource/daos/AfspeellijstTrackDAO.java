package datasource.daos;

import datasource.connectie.DatabaseProperties;
import exceptions.eigenexcepties.DatabaseFoutException;

import javax.inject.Inject;
import java.sql.*;

public class AfspeellijstTrackDAO {
    private DatabaseProperties databaseProperties;

    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    public ResultSet select(int pk, boolean voorAfspeellijst) {
        try {

            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement;
            if (voorAfspeellijst) {
                statement = connection.prepareStatement("SELECT *, track.id AS id " +
                        "FROM Track " +
                        "LEFT JOIN video v " +
                        "                   ON track.id = v.id " +
                        "LEFT JOIN lied l " +
                        "                   ON track.id = l.id " +
                        "WHERE track.id NOT IN( " +
                        "SELECT trackId " +
                        "FROM afspeellijsttrack " +
                        "         INNER JOIN Track " +
                        "                    ON afspeellijsttrack.trackId = track.Id " +
                        "WHERE afspeellijstId = ?) ");
            } else {
                statement = connection.prepareStatement("SELECT * " +
                        "FROM track LEFT JOIN lied " +
                        "ON track.id = lied.id " +
                        "LEFT JOIN video " +
                        "ON track.id=video.id " +
                        "INNER JOIN afspeellijsttrack " +
                        "ON afspeellijsttrack.trackId = track.id " +
                        "WHERE afspeellijstId = ?");
            }
            statement.setInt(1, pk);
            return statement.executeQuery();
        } catch (SQLException e) {
            throw new DatabaseFoutException("Er is een select-fout opgetreden in de tabel afspeellijstTrack");
        }
    }

    public void delete(int afspeellijstId, int trackId) {
        try {

            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement1 = connection.prepareStatement("DELETE FROM afspeellijsttrack WHERE afspeellijstId = ? AND trackId = ?");
            statement1.setInt(1, afspeellijstId);
            statement1.setInt(2, trackId);
            statement1.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseFoutException("Er is een delete-fout opgetreden in de tabel afspeellijstTrack");
        }
    }

    public void insert(int afspeellijstId, int trackId) {
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement;
            statement = connection.prepareStatement("INSERT INTO afspeellijsttrack VALUES (?,?)");
            statement.setInt(1, afspeellijstId);
            statement.setInt(2, trackId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseFoutException("Er is een insert-fout opgetreden in de tabel afspeellijstTrack");
        }
    }
}
