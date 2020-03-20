package Datasource.DAOs;

import Datasource.util.DatabaseProperties;
import Domain.Afspeellijst;
import Domain.Lied;
import Domain.Track;
import Domain.Video;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TrackDAO {
    private Logger logger = Logger.getLogger(getClass().getName());

    private DatabaseProperties databaseProperties;

    public TrackDAO() {
        this.databaseProperties = new DatabaseProperties();
    }
    public void insert(Track track, int afspeellijstId) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM track WHERE id = ?");
            statement.setInt(1,track.getId());
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()) {
                statement = connection.prepareStatement("INSERT INTO Track VALUES (?,?,?,?,?,?)");
                statement.setString(1, track.getTitel());
                statement.setString(2, track.getUrl());
                statement.setInt(3, track.getAfspeelduur());
                statement.setBoolean(4, track.isOfflineAvailable());
                statement.setString(5, track.getPerformer());
                statement.setInt(6, track.getId());
                statement.executeUpdate();
                if (track instanceof Video) {
                    statement = connection.prepareStatement("INSERT INTO Video VALUES (?,?,?)");
                    statement.setString(1, ((Video) track).getPublicatieDatum());
                    statement.setString(2, ((Video) track).getBeschrijving());
                    statement.setInt(3, track.getId());
                } else if (track instanceof Lied) {
                    statement = connection.prepareStatement("INSERT INTO Lied VALUES (?,?)");
                    statement.setString(1, ((Lied) track).getAlbum());
                    statement.setInt(2, track.getId());
                }
                statement.executeUpdate();
            }
                statement = connection.prepareStatement("INSERT INTO afspeellijsttrack VALUES (?,?)");
                statement.setInt(1, afspeellijstId);
                statement.setInt(2, track.getId());
                statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void update(Track track) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("UPDATE track SET titel = ?, url = ?, afspeelduur = ?, offlineAvailable = ?, performer = ? WHERE id = ?");
            statement.setString(1,track.getTitel());
            statement.setString(2,track.getUrl());
            statement.setInt(3,track.getAfspeelduur());
            statement.setBoolean(4,track.isOfflineAvailable());
            statement.setString(5,track.getPerformer());
            statement.setInt(6,track.getId());
            statement.executeUpdate();
            if(track instanceof Video){
                statement = connection.prepareStatement("UPDATE Video SET publicatieDatum = ?, beschrijving = ? WHERE id = ?)");
                statement.setString(1,((Video) track).getPublicatieDatum());
                statement.setString(2,((Video) track).getBeschrijving());
                statement.setInt(3,track.getId());
            }else if(track instanceof Lied){
                statement = connection.prepareStatement("UPDATE Lied SET album = ? WHERE id = ?");
                statement.setString(1,((Lied) track).getAlbum());
                statement.setInt(2,track.getId());
            }
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void detachTracksFromPlaylists(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement1 = connection.prepareStatement("DELETE FROM afspeellijsttrack WHERE afspeellijstId = ?");
            statement1.setInt(1,id);
            statement1.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Track> getTracksVanAfspeellijst(int id) {
        List<Track> tracks = new ArrayList<>();
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
                    "WHERE afspeellijstId = ?");
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if(resultSet.getString("album")!= null) {
                    Domain.Track track = new Lied(resultSet.getInt("id"), resultSet.getString("titel"),
                            resultSet.getString("url"), resultSet.getInt("afspeelduur"),
                            resultSet.getBoolean("offlineAvailable"), resultSet.getString("performer"),
                            resultSet.getString("album"));
                    tracks.add(track);
                }else {
                    Domain.Track track = new Video(resultSet.getInt("id"), resultSet.getString("titel"),
                            resultSet.getString("url"), resultSet.getInt("afspeelduur"),
                            resultSet.getBoolean("offlineAvailable"), resultSet.getString("performer"),
                            resultSet.getString("publicatieDatum"),resultSet.getString("beschrijving") );
                    tracks.add(track);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tracks;
    }
    public void delete(Track track) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement;
            statement = connection.prepareStatement("DELETE FROM afspeellijstTrack WHERE trackId = ?");
            statement.setInt(1, track.getId());
            statement.executeUpdate();
            if(track instanceof Lied){
                statement = connection.prepareStatement("DELETE FROM lied WHERE id = ?");
            } else {
                statement = connection.prepareStatement("DELETE FROM video WHERE id = ?");
            }
            statement.setInt(1, track.getId());
            statement.executeUpdate();
            statement = connection.prepareStatement("DELETE FROM track WHERE id = ?");
            statement.setInt(1, track.getId());
            statement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Track> getTracksVoorAfspeellijst(int id) {
        List<Track> tracks = new ArrayList<>();
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
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if(resultSet.getString("album")!= null) {
                    Domain.Track track = new Lied(resultSet.getInt("id"), resultSet.getString("titel"),
                            resultSet.getString("url"), resultSet.getInt("afspeelduur"),
                            resultSet.getBoolean("offlineAvailable"), resultSet.getString("performer"),
                            resultSet.getString("album"));
                    tracks.add(track);
                }else {
                    Domain.Track track = new Video(resultSet.getInt("id"), resultSet.getString("titel"),
                            resultSet.getString("url"), resultSet.getInt("afspeelduur"),
                            resultSet.getBoolean("offlineAvailable"), resultSet.getString("performer"),
                            resultSet.getString("publicatieDatum"),resultSet.getString("beschrijving") );
                    tracks.add(track);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tracks;
    }

    public List<Track> getAlleTracks() {
        List<Track> tracks = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
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
                if(resultSet.getString("album")!= null) {
                    Domain.Track track = new Lied(resultSet.getInt("id"), resultSet.getString("titel"),
                            resultSet.getString("url"), resultSet.getInt("afspeelduur"),
                            resultSet.getBoolean("offlineAvailable"), resultSet.getString("performer"),
                            resultSet.getString("album"));
                    tracks.add(track);
                }else {
                    Domain.Track track = new Video(resultSet.getInt("id"), resultSet.getString("titel"),
                            resultSet.getString("url"), resultSet.getInt("afspeelduur"),
                            resultSet.getBoolean("offlineAvailable"), resultSet.getString("performer"),
                            resultSet.getString("publicatieDatum"),resultSet.getString("beschrijving") );
                    tracks.add(track);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tracks;
    }
}
