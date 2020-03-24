package domain;

import datasource.daos.*;
import exceptions.eigenexcepties.VerkeerdeTokenException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class Spotitube {
    private AfspeellijstDAO afspeellijstDAO;
    private TrackDAO trackDAO;
    private EigenaarDAO eigenaarDAO;
    private Afspeellijst afspeellijst;
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

    @Inject
    public void setEigenaarDAO(EigenaarDAO eigenaarDAO) {
        this.eigenaarDAO = eigenaarDAO;
    }

    public Afspeellijst openAfspeellijst(int id) {
        Afspeellijst afspeellijst = (Afspeellijst) afspeellijstDAO.select(id);
        afspeellijst.setTracks(this.afspeellijst.openTracksVoorAfspeellijst(afspeellijst.getId()));
        return afspeellijst;
    }

    public List<Afspeellijst> openOverzicht() {
        List<Afspeellijst> afspeellijsten = new ArrayList<Afspeellijst>();
        for (Afspeellijst afspeellijst : afspeellijstDAO.selectAll()) {
            afspeellijsten.add(afspeellijst);
        }
        for (Afspeellijst afspeellijst : afspeellijsten) {
            List<Track> tracks = this.afspeellijst.openTracksVoorAfspeellijst(afspeellijst.getId());
            afspeellijst.setTracks(tracks);
        }
        return afspeellijsten;
    }

    public List<Track> toonTrackOverzicht() {
        List<Track> tracks = new ArrayList<Track>();
        for (Track track : trackDAO.selectAll()) {
            tracks.add(track);
        }
        return tracks;
    }

    public void verwijderAfspeellijst(int id) {
        afspeellijstDAO.delete(id);
    }

    public Eigenaar getEigenaar(String token) throws VerkeerdeTokenException {
        Eigenaar eigenaar = eigenaarDAO.getEigenaarMetToken(token);
        if (eigenaar != null) {
            return eigenaar;
        } else {
            throw new VerkeerdeTokenException(Response.status(401).build());
        }
    }
}
