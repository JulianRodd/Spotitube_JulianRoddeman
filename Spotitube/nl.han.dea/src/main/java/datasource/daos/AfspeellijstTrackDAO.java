package datasource.daos;

import datasource.util.DatabaseProperties;
import domain.Afspeellijst;
import domain.Lied;
import domain.Track;
import domain.Video;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AfspeellijstTrackDAO {
    private Logger logger = Logger.getLogger(getClass().getName());
    private DatabaseProperties databaseProperties;

    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    public List<Object> selectAll() {
        return null;
    }

    public List<Object> select(int pk) {
        List<Object> tracks = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * " +
                    "FROM track LEFT JOIN lied " +
                    "ON track.id = lied.id " +
                    "LEFT JOIN video " +
                    "ON track.id=video.id " +
                    "INNER JOIN afspeellijsttrack " +
                    "ON afspeellijsttrack.trackId = track.id " +
                    "WHERE afspeellijstId != ?");
            statement.setInt(1, pk);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("album") != null) {
                    domain.Track track = new Lied(resultSet.getInt("id"), resultSet.getString("titel"),
                            resultSet.getString("url"), resultSet.getInt("afspeelduur"),
                            resultSet.getBoolean("offlineAvailable"), resultSet.getString("performer"),
                            resultSet.getString("album"));
                    tracks.add(track);
                } else {
                    domain.Track track = new Video(resultSet.getInt("id"), resultSet.getString("titel"),
                            resultSet.getString("url"), resultSet.getInt("afspeelduur"),
                            resultSet.getBoolean("offlineAvailable"), resultSet.getString("performer"),
                            resultSet.getString("publicatieDatum"), resultSet.getString("beschrijving"));
                    tracks.add(track);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tracks;
    }

    public void delete(int pk) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement1 = connection.prepareStatement("DELETE FROM afspeellijsttrack WHERE afspeellijstId = ?");
            statement1.setInt(1, pk);
            statement1.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insert(Object object) {
        try {
            Afspeellijst afspeellijst = (Afspeellijst) object;
            List<Object> tracks = select(afspeellijst.getId());
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement;
            for (Object track : tracks) {
                if (!afspeellijst.getTracks().contains((Track) track)) {
                    statement = connection.prepareStatement("INSERT INTO afspeellijsttrack VALUES (?,?)");
                    statement.setInt(1, afspeellijst.getId());
                    statement.setInt(2, ((Track) track).getId());
                    statement.executeUpdate();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void update(Object object) {

    }
}
