package datasource.daos;

import datasource.connectie.DatabaseProperties;
import domain.Lied;
import domain.Track;
import domain.Video;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TrackDAO{
    private Logger logger = Logger.getLogger(getClass().getName());
    private DatabaseProperties databaseProperties;

    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    public void insert(Track track) {
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM track WHERE id = ?");
            statement.setInt(1, track.getId());
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                statement = connection.prepareStatement("INSERT INTO Track VALUES (?,?,?,?,?,?)");
                statement.setString(1, track.getTitel());
                statement.setString(2, track.getUrl());
                statement.setInt(3, track.getAfspeelduur());
                statement.setBoolean(4, track.isOfflineBeschikbaar());
                statement.setString(5, track.getPerformer());
                statement.setInt(6, track.getId());
                statement.executeUpdate();
                if (track instanceof Video) {
                    statement = connection.prepareStatement("INSERT INTO Video VALUES (?,?,?,?)");
                    statement.setString(1, ((Video) track).getPublicatieDatum());
                    statement.setString(2, ((Video) track).getBeschrijving());
                    statement.setInt(3, ((Video) track).getAantalWeergaven());
                    statement.setInt(4, track.getId());
                } else if (track instanceof Lied) {
                    statement = connection.prepareStatement("INSERT INTO Lied VALUES (?,?)");
                    statement.setString(1, ((Lied) track).getAlbum());
                    statement.setInt(2, track.getId());
                }
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Track track) {
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("UPDATE track SET titel = ?, url = ?, afspeelduur = ?, offlineAvailable = ?, performer = ? WHERE id = ?");
            statement.setString(1, track.getTitel());
            statement.setString(2, track.getUrl());
            statement.setInt(3, track.getAfspeelduur());
            statement.setBoolean(4, track.isOfflineBeschikbaar());
            statement.setString(5, track.getPerformer());
            statement.setInt(6, track.getId());
            statement.executeUpdate();
            if (track instanceof Video) {
                statement = connection.prepareStatement("UPDATE Video SET publicatieDatum = ?, beschrijving = ?, weergaven = ? WHERE id = ?)");
                statement.setString(1, ((Video) track).getPublicatieDatum());
                statement.setString(2, ((Video) track).getBeschrijving());
                statement.setInt(3, ((Video) track).getAantalWeergaven());
                statement.setInt(4, track.getId());
            } else if (track instanceof Lied) {
                statement = connection.prepareStatement("UPDATE Lied SET album = ? WHERE id = ?");
                statement.setString(1, ((Lied) track).getAlbum());
                statement.setInt(2, track.getId());
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int pk) {
        try {

            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement;
            statement = connection.prepareStatement("DELETE FROM afspeellijstTrack WHERE trackId = ?");
            statement.setInt(1, pk);
            statement.executeUpdate();
            statement = connection.prepareStatement("DELETE FROM lied WHERE id = ?");
            statement.setInt(1, pk);
            statement.executeUpdate();
            statement = connection.prepareStatement("DELETE FROM video WHERE id = ?");
            statement.setInt(1, pk);
            statement.executeUpdate();
            statement = connection.prepareStatement("DELETE FROM track WHERE id = ?");
            statement.setInt(1, pk);
            statement.executeUpdate();}
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Track> selectAll() {
        List<Track> tracks = new ArrayList<Track>();
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * " +
                    "FROM track LEFT JOIN lied " +
                    "ON track.id = lied.id " +
                    "LEFT JOIN video " +
                    "ON track.id=video.id " +
                    "INNER JOIN afspeellijsttrack " +
                    "ON afspeellijsttrack.trackId = track.id ");
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
                            resultSet.getString("publicatieDatum"), resultSet.getString("beschrijving"), resultSet.getInt("weergaven"));
                    tracks.add(track);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tracks;
    }
}
