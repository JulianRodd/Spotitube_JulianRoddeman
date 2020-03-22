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

public class AfspeellijstTrackDAO implements DAO{
    private Logger logger = Logger.getLogger(getClass().getName());
    private DatabaseProperties databaseProperties;
    public AfspeellijstTrackDAO() {
        databaseProperties = new DatabaseProperties();
    }

    @Override
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
            statement.setInt(1,pk);
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
    public void delete(int pk) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement1 = connection.prepareStatement("DELETE FROM afspeellijsttrack WHERE afspeellijstId = ?");
            statement1.setInt(1,pk);
            statement1.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void insert(Object object) {
        try {
            Afspeellijst afspeellijst = (Afspeellijst)object;
            List<Object> tracks = select(afspeellijst.getId());
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement;
            for(Object track: tracks) {
                if(!afspeellijst.getTracks().contains((Track)track)) {
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

    @Override
    public void update(Object object) {

    }
}
