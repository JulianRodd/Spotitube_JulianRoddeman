package Domain;

import Datasource.DAOs.AfspeellijstDAO;
import Datasource.DAOs.EigenaarDAO;
import Datasource.DTOs.TrackDTO;

import javax.inject.Inject;
import java.util.List;

public class Afspeellijst {
    private int id;
    private String naam;
    private boolean owner;
    List<Track> tracks;
    private AfspeellijstDAO afspeellijstDAO;
    public List<TrackDTO> getTracks(int id) {
        return afspeellijstDAO.getTracksVanAfspeellijst(id);
    }
    public Track speelTrackAf() {
        return null;
    }
    public int resterendeTrackTijd(Track track) {
        return 0;
    }
    public void stopTrack(Track track) { }
    public int getAantalNummersOver() {
        return 0;
    }
    public void setTracks(List<Track> tracks) { }
    @Inject
    public void setAfspeellijstDAO(AfspeellijstDAO afspeellijstDAO) {
        this.afspeellijstDAO = afspeellijstDAO;
    }
}
