package datasource.daos;

import datasource.connectie.DatabaseProperties;
import exceptions.eigenexcepties.DatabaseFoutException;

import javax.inject.Inject;
import java.sql.*;

public class TrackDAO{
    private DatabaseProperties databaseProperties;

    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }
    public ResultSet selectAll() {
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * " +
                    "FROM track LEFT JOIN lied " +
                    "ON track.id = lied.id " +
                    "LEFT JOIN video " +
                    "ON track.id=video.id " +
                    "INNER JOIN afspeellijsttrack " +
                    "ON afspeellijsttrack.trackId = track.id ");
            return statement.executeQuery();
        } catch (SQLException e) { throw new DatabaseFoutException("Er is een selectAll-fout opgetreden in de tabel track");
        }
    }
}
