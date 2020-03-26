package datasource.daos;

import datasource.connectie.DatabaseProperties;
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

    public List<Track> select(int pk, boolean voorAfspeellijst) {
        List<Track> tracks = new ArrayList<>();
        try {

            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement;
            if(voorAfspeellijst) {
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
            }else{
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
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("album") != null) {
                    Track track = new Lied(resultSet.getInt("id"), resultSet.getString("titel"),
                            resultSet.getString("url"), resultSet.getInt("afspeelduur"),
                            resultSet.getBoolean("offlineAvailable"), resultSet.getString("performer"),
                            resultSet.getString("album"));
                    tracks.add(track);
                } else {
                    domain.Track track = new Video(resultSet.getInt("id"), resultSet.getString("titel"),
                            resultSet.getString("url"), resultSet.getInt("afspeelduur"),
                            resultSet.getBoolean("offlineAvailable"), resultSet.getString("performer"),
                            resultSet.getString("publicatieDatum"), resultSet.getString("beschrijving"), resultSet.getInt("weergaven"));
                    tracks.add(track);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tracks;
    }

    public void deletePlaylistFromTrack(int afspeellijstId, int trackId) {
        try {

            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement1 = connection.prepareStatement("DELETE FROM afspeellijsttrack WHERE afspeellijstId = ? AND trackId = ?");
            statement1.setInt(1, afspeellijstId);
            statement1.setInt(2, trackId);
            statement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }
}
