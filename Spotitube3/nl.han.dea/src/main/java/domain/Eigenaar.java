package domain;
import datasource.daos.AfspeellijstDAO;
import datasource.daos.EigenaarDAOImpl;
import exceptions.eigenexcepties.OnjuistWachtwoordExceptie;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.UUID;

public class Eigenaar {

    private String gebruikersnaam;
    private String wachtwoord;
    private String token;
    private EigenaarDAOImpl eigenaarDAO;
    private AfspeellijstDAO afspeellijstDao;

    @Inject
    public void setEigenaarDAO(EigenaarDAOImpl eigenaarDAO) {
        this.eigenaarDAO = eigenaarDAO;
    }

    @Inject
    public void setAfspeellijstDao(AfspeellijstDAO afspeellijstDao) {
        this.afspeellijstDao = afspeellijstDao;
    }

    public void setIngelogd(Eigenaar eigenaar) throws OnjuistWachtwoordExceptie {
        Eigenaar geregistreerde = eigenaarDAO.select(eigenaar.getGebruikersnaam());
        if(geregistreerde != null){
        String wachtwoordHash =  geregistreerde.getWachtwoord();
        if ((DigestUtils.sha256Hex(eigenaar.getWachtwoord()).equals(wachtwoordHash))) {
                String nieuweToken = UUID.randomUUID().toString();
                eigenaar.setToken(nieuweToken);
                eigenaar.setWachtwoord(wachtwoordHash);
                eigenaarDAO.update(eigenaar);
            } else {
                throw new OnjuistWachtwoordExceptie();
            }
        } else {
            throw new NotFoundException();
        }
    }


    public void maakAfspeellijst(Afspeellijst afspeellijst) {
        afspeellijst.setId(afspeellijstDao.getMaxId() + 1);
        afspeellijstDao.insert(afspeellijst);
    }

    public void wijzigAfspeellijst(Afspeellijst afspeellijst) {
        afspeellijstDao.update(afspeellijst);
    }
	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	public void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}

	public String getWachtwoord() {
		return wachtwoord;
	}

	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
