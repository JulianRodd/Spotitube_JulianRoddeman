package Domain;

import Datasource.DAOs.EigenaarDAO;
import Datasource.DTOs.EigenaarDTO;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class Eigenaar {
    private String gebruikersnaam;
    private String wachtoord;
    private boolean ingelogd;
    private List<Afspeellijst> afspeellijsten;
    private EigenaarDAO eigenaarDAO;

    @Inject
    public void setEigenaarDAO(EigenaarDAO eigenaarDAO) {
        this.eigenaarDAO = eigenaarDAO;
    }
    public String setIngelogd(String gebruikersnaam, String wachtwoord)  {
        EigenaarDTO eigenaarDTO = eigenaarDAO.selectEigenaar(gebruikersnaam);
            String token = UUID.randomUUID().toString();
            String wachtwoordHash = DigestUtils.sha256Hex(wachtwoord);
        if (wachtwoordHash.equals(eigenaarDTO.getPassword())) {
            eigenaarDAO.updateToken(gebruikersnaam, token);
            return token;
        } else {
            throw new NotFoundException();
        }

    }


    public void maakAfspeellijst(Track tracks, String naam) { }
    public void wijzigAfspeellijst(Track tracks, String naam, int iid) {}
}
