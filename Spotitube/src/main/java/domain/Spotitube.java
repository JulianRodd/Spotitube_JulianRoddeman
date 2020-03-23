package domain;

import datasource.daos.AfspeellijstDAO;
import datasource.daos.EigenaarDAO;
import datasource.daos.TrackDAO;
import exceptions.eigenexcepties.VerkeerdeTokenException;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class Spotitube {

    private Eigenaar[] eigenaar;

    private List<Afspeellijst> afspeellijsten;

    private Track[] track;
    private AfspeellijstDAO afspeellijstDAO;
    private TrackDAO trackDAO;
    private EigenaarDAO eigenaarDAO;

    @Inject
    public void setEigenaarDAO(EigenaarDAO eigenaarDAO) {
        this.eigenaarDAO = eigenaarDAO;
    }

    public Spotitube() {
        afspeellijstDAO = new AfspeellijstDAO();
        trackDAO = new TrackDAO();
    }

    public Afspeellijst openAfspeellijst(int id) {
       Afspeellijst afspeellijst = (Afspeellijst)afspeellijstDAO.select(id);
       afspeellijst.setTracks(afspeellijst.openTracksVoorAfspeellijst());
       return afspeellijst;
    }

    public List<Afspeellijst> openOverzicht() {
        List<Afspeellijst> afspeellijsten = new ArrayList<Afspeellijst>();
                for(Object object:afspeellijstDAO.selectAll()){
                    afspeellijsten.add((Afspeellijst)object);
                }
        for (Afspeellijst afspeellijst : afspeellijsten) {
            List<Track> tracks = afspeellijst.getTracks();
                afspeellijst.setTracks(tracks);
        }
        return afspeellijsten;
    }
    public List<Track> toonTrackOverzicht() {
        List<Track> tracks = new ArrayList<Track>();
        for(Object object :trackDAO.selectAll()){
            tracks.add((Track)object);
        }
        return tracks;
    }
    public void verwijderAfspeellijst(int id) {
        afspeellijstDAO.delete(id);
    }

    public Eigenaar getEigenaar(String token) throws VerkeerdeTokenException {
        Eigenaar eigenaar = eigenaarDAO.getEigenaarMetToken(token);
        if(eigenaar!=null){
            return eigenaar;
        }else{
            throw new VerkeerdeTokenException();
        }
    }
//    public void maakAfspeellijst(Track tracks, String naam) {
//
//    }
//    public void logIn(String gebruikersnaam, String wachtwoord) {
//
//    }
//    public String geefInlogFormulier() {
//        return null;
//    }
//
//    public void speelTrackAf(Track track, Afspeellijst afspeellijst) {
//    }
}
