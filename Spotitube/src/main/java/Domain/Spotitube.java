package Domain;

import Controller.DTOs.AfspeellijstDTO;
import Controller.DTOs.AfspeellijstenDTO;
import Datasource.DAOs.AfspeellijstDao;
import Datasource.DAOs.TrackDAO;

import java.util.ArrayList;
import java.util.List;

public class Spotitube {

    private Eigenaar[] eigenaar;

    private List<Afspeellijst> afspeellijsten;

    private Track[] track;
    private AfspeellijstDao afspeellijstDao;
    private TrackDAO trackDAO;


    public Spotitube() {
        afspeellijstDao = new AfspeellijstDao();
        trackDAO = new TrackDAO();
    }

    public String geefInlogFormulier() {
        return null;
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

    public void logIn(String gebruikersnaam, String wachtwoord) {

    }

    public Eigenaar valideerGegevens(String gebruikersnaam, String wachtwoord) {
        return null;
    }

    public List<Track> toonTrackOverzicht() {
        return trackDAO.getAlleTracks();
    }

    public void maakAfspeellijst(Track tracks, String naam) {

    }

    public void speelTrackAf(Track track, Afspeellijst afspeellijst) {

    }

    public AfspeellijstenDTO mapToDTO(List<Afspeellijst> afspeellijsten) {
        List<AfspeellijstDTO> afspeellijstDTOs = new ArrayList<AfspeellijstDTO>();
        int lengte = 0;
        for (Afspeellijst afspeellijst : afspeellijsten) {
            afspeellijstDTOs.add(afspeellijst.mapToDTO());
            lengte += afspeellijst.berekenAfspeellijstLengte();
        }
        AfspeellijstenDTO afspeellijstenDTO = new AfspeellijstenDTO();
        afspeellijstenDTO.setPlaylists(afspeellijstDTOs);
        afspeellijstenDTO.setLength(lengte);
        return afspeellijstenDTO;
    }

    public void verwijderAfspeellijst(int id) {
        afspeellijstDao.delete(id);
    }

    public void voegNieuweAfspeellijstToe(Afspeellijst afspeellijst) {
        afspeellijst.setId(afspeellijstDao.getMaxId()+1);
        afspeellijstDao.insert(afspeellijst);
            afspeellijst.voegTracksToe();
    }

    public void wijzigAfspeellijst(Afspeellijst afspeellijst) {
        afspeellijst.updateTracks();
        afspeellijstDao.update(afspeellijst);
    }
}
