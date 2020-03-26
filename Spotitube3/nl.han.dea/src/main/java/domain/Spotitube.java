package domain;

import datasource.daos.*;
import exceptions.eigenexcepties.VerkeerdeTokenException;

import javax.inject.Inject;
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
        Afspeellijst afspeellijst = afspeellijstDAO.select(id);
        afspeellijst.setTracks(this.afspeellijst.openTracksAfspeellijst(afspeellijst.getId(), false));
        return afspeellijst;
    }

    public List<Afspeellijst> openOverzicht() {
        List<Afspeellijst> afspeellijsten = afspeellijstDAO.selectAll();

        for (Afspeellijst afspeellijst : afspeellijsten) {
            List<Track> tracks = this.afspeellijst.openTracksAfspeellijst(afspeellijst.getId(), false);
            afspeellijst.setTracks(tracks);
        }
        return afspeellijsten;
    }

    public List<Track> toonTrackOverzicht() {
        return trackDAO.selectAll();
    }

    public void verwijderAfspeellijst(int id) {
        afspeellijstDAO.delete(id);
    }

    public Eigenaar getEigenaar(String token) throws VerkeerdeTokenException {
        Eigenaar eigenaar = eigenaarDAO.getEigenaarMetToken(token);
        if (eigenaar != null) {
            return eigenaar;
        } else {
            throw new VerkeerdeTokenException();
        }
    }

}
