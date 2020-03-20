package Domain;

import Controller.DTOs.AfspeellijstDTO;
import Controller.DTOs.TrackDTO;
import Datasource.DAOs.TrackDAO;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class Afspeellijst {

    private int id;
    private String naam;
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

    public Track speelTrackAf() {
        return null;
    }

    public int resterendeTrackTijd(Track track) {
        return 0;
    }

    public void stopTrack(Track track) {

    }

    public int berekenAfspeellijstLengte() {
        int lengte = 0;
        for (Track track : tracks) {
            lengte += track.getAfspeelduur();
        }
        return lengte;
    }

    public int getAantalNummersOver() {
        return 0;
    }

    public void setTracks(List<Domain.Track> tracks) {
        this.tracks = tracks;
    }

    public int getId() {
        return id;
    }

    public AfspeellijstDTO mapToDTO() {
        AfspeellijstDTO afspeellijstDTO = new AfspeellijstDTO();
        afspeellijstDTO.setId(id);
        if (eigenaar != null) {
            afspeellijstDTO.setOwner(true);
        } else {
            afspeellijstDTO.setOwner(false);
        }
        afspeellijstDTO.setName(naam);
        List<TrackDTO> trackDTOs = new ArrayList<TrackDTO>();
        for (Track track : tracks) {
            trackDTOs.add(track.mapToDTO());
        }
        afspeellijstDTO.setTracks(trackDTOs);

        return afspeellijstDTO;
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
}
