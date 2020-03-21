package Domain;
import Datasource.DAOs.TrackDAO;
import java.util.List;

public class Afspeellijst {

    private int id;
    private String naam;

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setEigenaar(String eigenaar) {
        this.eigenaar = eigenaar;
    }

    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    private String eigenaar;
    private List<Domain.Track> tracks;
    private TrackDAO trackDAO;

    public Afspeellijst(int id, String naam, String eigenaar) {
        this.id = id;
        this.naam = naam;
        this.eigenaar = eigenaar;
        trackDAO = new TrackDAO();
    }
public void setId(int id){
        this.id = id;
}
    public List<Track> getTracks() {
        return trackDAO.getTracksVanAfspeellijst(id);
    }
    public int berekenAfspeellijstLengte() {
        int lengte = 0;
        for (Track track : tracks) {
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
            trackDAO.insert(track, id);
    }
    public void updateTracks() {
        List<Track> teVerwijderenTracks = trackDAO.getTracksVanAfspeellijst(id);
        trackDAO.detachTracksFromPlaylists(id);
        for(Track teVerwijderenTrack :  teVerwijderenTracks){
            verwijderTrack(teVerwijderenTrack);
        }
        voegTracksToe();
    }

    public void verwijderTrack(Track track) {
        trackDAO.delete(track);
    }
    public List<Track> openTracksVoorAfspeellijst() {
        return trackDAO.getTracksVoorAfspeellijst(id);
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
}
