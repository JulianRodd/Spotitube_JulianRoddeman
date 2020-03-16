package Domain;

import Datasource.Properties.DatabaseConnection;

import java.util.List;


/**
 * Hello world!
 *
 */
public class Spotitube
{
    List<Eigenaar> eigenaren;
    List<Afspeellijst> afspeellijsten;
    List<Track> tracks;

    public static void main( String[] args ) {
//        System.out.println(Hashing.sha256()
//                .hashString("wachtwoord", StandardCharsets.UTF_8)
//                .toString());


//        AfspeellijstDAO dao;
//        DatabaseConnection db;
//        db = new DatabaseConnection();
//        Eigenaar eigenaar = new Eigenaar();
//        System.out.println(eigenaar.setIngelogd("gebruiker0", "wachtwoord"));
        //        dao = new AfspeellijstDAO(db);
//       for(AfspeellijstDTO afspeellijst :  dao.alleAfspeellijsten()){
//           System.out.println("AFSPEELLIJST:");
//           System.out.println(afspeellijst.getId());
//           System.out.println(afspeellijst.getNaam());
//           for(TrackDTO track : afspeellijst.getTracks()){
//               System.out.println("TRACK ERIN:");
//               System.out.println(track.getTitel());
//               System.out.println(track.getAfspeelduur());
//               System.out.println(track.getPerformer());
//           }
//       }
    }
    public String geefInlogFormulier() {
        return null;
    }
    public Afspeellijst openAfspeellijst(Afspeellijst afspeellijst) {
        return null;
    }
    public Afspeellijst openOverzicht() {
        return null;
    }
    public void logIn(String gebruikersnaam, String wachtwoord) { }
    public Eigenaar valideerGegevens(String gebruikersnaam, String wachtwoord) {
        return null;
    }
    public Track toonTrackOverzicht() {
        return null;
    }
    public void maakAfspeellijst(Track tracks, String naam) { }
    public void speelTrackAf(Track track, Afspeellijst afspeellijst) { }
}
