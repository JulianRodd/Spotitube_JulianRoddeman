package domain;
import datasource.daos.AfspeellijstTrackDAO;
import datasource.daos.DAO;
import datasource.daos.TrackDAO;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

public class Afspeellijst {

    private int id;
    private String naam;
    private String eigenaar;
    private List<Track> tracks;
    private TrackDAO trackDAO;
    private AfspeellijstTrackDAO afspeellijstTrackDAO;

public void setId(int id){
        this.id = id;
}

    public List<Track> getTracks() {
        return tracks;
    }
    public int berekenAfspeellijstLengte() {
        int lengte = 0;
        for (Track track : openTracksVoorAfspeellijst()) {
            lengte += track.getAfspeelduur();
        }
        return lengte;
    }
    public void setTracks(List<domain.Track> tracks) {
        this.tracks = tracks;
    }
    public int getId() {
        return id;
    }
    public String getNaam() {
        return naam;
    }
    public String getEigenaar() {
        return eigenaar;
    }
    public void voegTracksToe() {
        for(Track track: tracks){
            voegTrackToe(track);
        }
    }
    public void voegTrackToe(Track track) {
        trackDAO.insert(track);
        afspeellijstTrackDAO.insert(this);
    }
    public void updateTracks() {
        List<Track> teVerwijderenTracks = new ArrayList<Track>();
        openTracksVoorAfspeellijst();
        afspeellijstTrackDAO.delete(id);
        for(Track teVerwijderenTrack :  teVerwijderenTracks){
            verwijderTrack(teVerwijderenTrack);
        }
        voegTracksToe();
    }

    public void verwijderTrack(Track track) {
        trackDAO.delete(track.getId());
    }
    public List<Track> openTracksVoorAfspeellijst() {
        List<Track> tracks = new ArrayList<Track>();

        for(Object object: afspeellijstTrackDAO.select(id)){
            tracks.add((Track)object);
        }
        return tracks;
    }
    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setEigenaar(String eigenaar) {
        this.eigenaar = eigenaar;
    }
    @Inject
    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }
    @Inject
    public void setAfspeellijstTrackDAO(AfspeellijstTrackDAO afspeellijstTrackDAO) {
        this.afspeellijstTrackDAO = afspeellijstTrackDAO;
    }

}
