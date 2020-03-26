package domain.datamappers;

import domain.Afspeellijst;
import domain.Lied;
import domain.Track;
import domain.Video;
import exceptions.eigenexcepties.DatabaseFoutException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDataMapper {
    public List<Track> mapResultSetToListDomain(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                List<Track> tracks = new ArrayList<Track>();
                while (resultSet.next()) {
                    if (resultSet.getString("album") != null) {
                        Track track = new Lied(resultSet.getInt("id"), resultSet.getString("titel"),
                                resultSet.getInt("afspeelduur"),
                                resultSet.getBoolean("offlineAvailable"), resultSet.getString("performer"),
                                resultSet.getString("album"));
                        tracks.add(track);
                    } else {
                        domain.Track track = new Video(resultSet.getInt("id"), resultSet.getString("titel"),
                                resultSet.getInt("afspeelduur"),
                                resultSet.getBoolean("offlineAvailable"), resultSet.getString("performer"),
                                resultSet.getString("publicatieDatum"), resultSet.getString("beschrijving"), resultSet.getInt("weergaven"));
                        tracks.add(track);
                    }
                }
                return tracks;
            } catch(SQLException e){
                throw new DatabaseFoutException("Er is heeft een fout opgetreden bij het omzetten van resultset naar Tracks");
            }
        }
        return null;
    }
}
