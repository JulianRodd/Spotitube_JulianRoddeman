package Domain;

import Controller.DTOs.LoginDTO;
import Datasource.DAOs.AfspeellijstDAO;
import Datasource.DAOs.EigenaarDAO;
import Datasource.DAOs.EigenaarDAOImpl;
import Exceptions.EigenExcepties.OnjuistWachtwoordExceptie;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Eigenaar {

	private String gebruikersnaam;
	private String wachtwoord;

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

	private String token;
	private List<Afspeellijst> eigendommen;
	@Inject
	private EigenaarDAO eigenaarDAO;
	private AfspeellijstDAO afspeellijstDao;

	public Eigenaar() {
		afspeellijstDao = new AfspeellijstDAO();
		eigendommen = new ArrayList<Afspeellijst>();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setIngelogd() throws OnjuistWachtwoordExceptie {
		System.out.println(eigenaarDAO);
		String wachtwoordHash = ((Eigenaar) eigenaarDAO.select(gebruikersnaam)).getWachtwoord();
		if(wachtwoordHash!=null){
			System.out.println(wachtwoordHash);
			if((DigestUtils.sha256Hex(this.wachtwoord).equals(wachtwoordHash))){
				String nieuweToken = UUID.randomUUID().toString();
				this.token = nieuweToken;
					eigenaarDAO.update(this);
			}else{
				throw new OnjuistWachtwoordExceptie();
			}
		}else{
			throw new NotFoundException();
		}
	}


	public void maakAfspeellijst(Afspeellijst afspeellijst) {
		afspeellijst.setId(afspeellijstDao.getMaxId()+1);
		afspeellijst.setEigenaar(gebruikersnaam);
		afspeellijstDao.insert(afspeellijst);
		afspeellijst.voegTracksToe();
	}
		public void wijzigAfspeellijst(Afspeellijst afspeellijst) {
			afspeellijst.updateTracks();
			afspeellijstDao.update(afspeellijst);
	}

	@Inject
	public void setEigenaarDAO(EigenaarDAO eigenaarDAO) {
		this.eigenaarDAO = eigenaarDAO;
	}
}
