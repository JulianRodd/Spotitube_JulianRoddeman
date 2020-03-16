package Datasource.DAOs;

import Datasource.DTOs.AfspeellijstDTO;
import Datasource.DTOs.LiedDTO;
import Datasource.DTOs.TrackDTO;
import Datasource.DTOs.VideoDTO;
import Datasource.Properties.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AfspeellijstDAO {
    private Logger logger = Logger.getLogger(getClass().getName());

    private DatabaseConnection databaseConnection;

    public AfspeellijstDAO(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public List<AfspeellijstDTO> alleAfspeellijsten() {
        List<AfspeellijstDTO> afspeellijsten = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(databaseConnection.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT id,naam FROM Afspeellijst");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                afspeellijsten.add(new AfspeellijstDTO(resultSet.getInt("id"), resultSet.getString("naam"), getTracksVanAfspeellijst(resultSet.getInt("id"))));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseConnection.connectionString(), e);
        }
        return afspeellijsten;
    }

    public List<TrackDTO> getTracksVanAfspeellijst(int id) {
        List<TrackDTO> tracks = new ArrayList<TrackDTO>();
        try{
            Connection connection = DriverManager.getConnection(databaseConnection.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM AfspeellijstTrack a INNER JOIN Track t ON a.titel = t.titel LEFT JOIN Video v ON t.titel = v.titel LEFT JOIN Lied l ON t.titel = l.titel WHERE id = " + Integer.toString(id)+ "");
             ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
              if(heeftKolom(resultSet, "publicatieDatum")){
                tracks.add(new LiedDTO(resultSet.getString("titel"), resultSet.getString("performer"), resultSet.getString("url"), resultSet.getInt("afspeelduur"), resultSet.getBoolean("offlineAvailable"),resultSet.getString("album")));
               }else if(heeftKolom(resultSet, "album")){
                  tracks.add(new VideoDTO(resultSet.getString("titel"), resultSet.getString("performer"), resultSet.getString("url"), resultSet.getInt("afspeelduur"), resultSet.getBoolean("offlineAvailable"),resultSet.getString("publicatieDatum"),resultSet.getString("beschrijving")));
                }
               }}
                catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseConnection.connectionString(), e);
        }
        return tracks;
    }

    public static boolean heeftKolom(ResultSet rs, String kolomnaam) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int kolommen = rsmd.getColumnCount();
        for (int x = 1; x <= kolommen; x++) {
            if (kolomnaam.equals(rsmd.getColumnName(x))) {
                return true;
            }
        }
        return false;
    }

}

