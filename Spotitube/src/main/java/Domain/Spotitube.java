package Domain;

import Controller.DTOs.AfspeellijstDTO;
import Controller.DTOs.AfspeellijstenDTO;
import Datasource.DAOs.AfspeellijstDao;
import Datasource.DAOs.EigenaarDao;
import Datasource.DAOs.TrackDAO;
import Exceptions.EigenExcepties.VerkeerdeTokenException;

import java.util.ArrayList;
import java.util.List;

public class Spotitube {

    private Eigenaar[] eigenaar;

    private List<Afspeellijst> afspeellijsten;

    private Track[] track;
    private AfspeellijstDao afspeellijstDao;
    private TrackDAO trackDAO;
    private EigenaarDao eigenaarDAO;

    public Spotitube() {
        afspeellijstDao = new AfspeellijstDao();
        trackDAO = new TrackDAO();
    }

    public Afspeellijst openAfspeellijst(int id) {
       Afspeellijst afspeellijst = afspeellijstDao.selectAfspeellijst(id);
       afspeellijst.setTracks(afspeellijst.openTracksVoorAfspeellijst());
       return afspeellijst;
    }

    public List<Afspeellijst> openOverzicht() {
        List<Afspeellijst> afspeellijsten = afspeellijstDao.selectAll();
        for (Afspeellijst afspeellijst : afspeellijsten) {
            List<Track> tracks = afspeellijst.getTracks();
                afspeellijst.setTracks(tracks);
        }
        return afspeellijsten;
    }
    public List<Track> toonTrackOverzicht() {
        return trackDAO.getAlleTracks();
    }
    public void verwijderAfspeellijst(int id) {
        afspeellijstDao.delete(id);
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
