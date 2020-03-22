package Domain;
import Datasource.DAOs.AfspeellijstTrackDAO;
import Datasource.DAOs.TrackDAO;

import java.util.ArrayList;
import java.util.List;

public class Afspeellijst {

    private int id;
    private String naam;
    private String eigenaar;
    private List<Domain.Track> tracks;
    private TrackDAO trackDAO;
    private AfspeellijstTrackDAO afspeellijstTrackDAO;

    public Afspeellijst(int id, String naam, String eigenaar) {
        this.id = id;
        this.naam = naam;
        this.eigenaar = eigenaar;
        trackDAO = new TrackDAO();
        afspeellijstTrackDAO = new AfspeellijstTrackDAO();
    }
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
    public void setTracks(List<Domain.Track> tracks) {
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

        for(Object object: afspeellijstTrackDAO.select(id)){
            teVerwijderenTracks.add((Track)object);
        }
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

    //    public Track speelTrackAf() {
//        return null;
//    }
//
//    public int resterendeTrackTijd(Track track) {
//        return 0;
//    }
//
//    public void stopTrack(Track track) {
//
//    }
 //   public int getAantalNummersOver() {
//        return 0;
//    }
    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setEigenaar(String eigenaar) {
        this.eigenaar = eigenaar;
    }

    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }
}
