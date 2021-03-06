package domain;

import datasource.daos.AfspeellijstTrackDAO;
import datasource.daos.TrackDAO;
import domain.datamappers.TrackDataMapper;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class Afspeellijst {
    private int id;
    private String naam;
    private String eigenaar;
    private List<Track> tracks;
    private TrackDAO trackDAO;
    private AfspeellijstTrackDAO afspeellijstTrackDAO;
    private TrackDataMapper trackDataMapper;

    public Afspeellijst() {
        tracks = new ArrayList<>();
    }

    @Inject
    public void setTrackDataMapper(TrackDataMapper trackDataMapper) {
        this.trackDataMapper = trackDataMapper;
    }

    @Inject
    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    @Inject
    public void setAfspeellijstTrackDAO(AfspeellijstTrackDAO afspeellijstTrackDAO) {
        this.afspeellijstTrackDAO = afspeellijstTrackDAO;
    }

    public int berekenAfspeellijstLengte(int id) {
        int lengte = 0;
        for (Track track : openTracksAfspeellijst(id, false)) {
            lengte += track.getAfspeelduur();
        }
        return lengte;
    }

    public void voegTrackToe(Track track, Afspeellijst afspeellijst) {
        List<Track> tracks = afspeellijst.getTracks();
        tracks.add(track);
        afspeellijst.setTracks(tracks);
        afspeellijstTrackDAO.insert(afspeellijst.getId(), track.getId());
    }

    public void verwijderTrack(int afspeellijstId, int trackId) {
        afspeellijstTrackDAO.delete(afspeellijstId, trackId);
    }

    public List<Track> openTracksAfspeellijst(int id, boolean voorAfspeellijst) {
        return trackDataMapper.mapResultSetToListDomain(afspeellijstTrackDAO.select(id, voorAfspeellijst));

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
    public void setNaam(String naam) {
        this.naam = naam;
    }
    public void setEigenaar(String eigenaar) {
        this.eigenaar = eigenaar;
    }
    public void setId(int id) {
        this.id = id;
    }
    public List<Track> getTracks() {
        return tracks;
    }
}
