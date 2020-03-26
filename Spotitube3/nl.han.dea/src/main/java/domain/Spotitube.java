package domain;

import datasource.daos.*;
import domain.datamappers.AfspeellijstDataMapper;
import domain.datamappers.TrackDataMapper;

import javax.inject.Inject;
import java.util.List;

public class Spotitube {
    private AfspeellijstDAO afspeellijstDAO;
    private TrackDAO trackDAO;
    private Afspeellijst afspeellijst;
    private AfspeellijstDataMapper afspeellijstDataMapper;
    private TrackDataMapper trackDataMapper;

    @Inject
    public void setTrackDataMapper(TrackDataMapper trackDataMapper) {
        this.trackDataMapper = trackDataMapper;
    }

    @Inject
    public void setAfspeellijstDataMapper(AfspeellijstDataMapper afspeellijstDataMapper) {
        this.afspeellijstDataMapper = afspeellijstDataMapper;
    }

    @Inject
    public void setAfspeellijst(Afspeellijst afspeellijst) {
        this.afspeellijst = afspeellijst;
    }

    @Inject
    public void setAfspeellijstDAO(AfspeellijstDAO afspeellijstDAO) {
        this.afspeellijstDAO = afspeellijstDAO;
    }

    @Inject
    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    public Afspeellijst openAfspeellijst(int id) {
        Afspeellijst afspeellijst = afspeellijstDataMapper.mapResultSetToDomain(afspeellijstDAO.select(id));
        afspeellijst.setTracks(this.afspeellijst.openTracksAfspeellijst(afspeellijst.getId(), false));
        return afspeellijst;
    }

    public List<Afspeellijst> openOverzicht() {
        List<Afspeellijst> afspeellijsten = afspeellijstDataMapper.mapResultSetToListDomain(afspeellijstDAO.selectAll());

        for (Afspeellijst afspeellijst : afspeellijsten) {
            List<Track> tracks = this.afspeellijst.openTracksAfspeellijst(afspeellijst.getId(), false);
            afspeellijst.setTracks(tracks);
        }
        return afspeellijsten;
    }

    public List<Track> toonTrackOverzicht() {
        return trackDataMapper.mapResultSetToListDomain(trackDAO.selectAll());
    }

    public void verwijderAfspeellijst(int id) {
        afspeellijstDAO.delete(id);
    }
}
