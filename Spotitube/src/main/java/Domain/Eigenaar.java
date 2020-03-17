package Domain;

import Controller.DTOs.LoginDTO;
import Datasource.DAOs.EigenaarDAO;
import Exceptions.EigenExcepties.OnjuistWachtwoordExceptie;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;
import javax.sound.midi.Track;
import javax.ws.rs.NotFoundException;
import java.util.UUID;

public class Eigenaar {

	private String gebruikersnaam;

	private String wachtwoord;
	private String token;
	private boolean ingelogd;

	private Afspeellijst[] eigendom;
	private EigenaarDAO eigenaarDAO;

	public Eigenaar() {
	}
	public Eigenaar(String gebruikersnaam, String wachtwoord, boolean ingelogd) {
		this.gebruikersnaam = gebruikersnaam;
		this.wachtwoord = wachtwoord;
		this.ingelogd = ingelogd;
	}

	public void setIngelogd() throws OnjuistWachtwoordExceptie {
		String wachtwoordHash = eigenaarDAO.selectWachtwoord(this.gebruikersnaam);
		if(wachtwoordHash!=null){
			if((DigestUtils.sha256Hex(this.wachtwoord).equals(wachtwoordHash))){
					String token = UUID.randomUUID().toString();
					eigenaarDAO.updateToken(this.gebruikersnaam, token);
					this.token = token;
					ingelogd = true;
			}else{
				throw new OnjuistWachtwoordExceptie();
			}
		}else{
			throw new NotFoundException();
		}
	}

	public void maakAfspeellijst(Track tracks, String naam) {

	}
	public LoginDTO mapToDTO(){
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setUser(gebruikersnaam);
		loginDTO.setToken(token);
		return loginDTO;
	}

	public void wijzigAfspeellijst(Track tracks, String naam, int iid) {

	}

	@Inject
	public void setEigenaarDAO(EigenaarDAO eigenaarDAO){
		this.eigenaarDAO = eigenaarDAO;
	}

}
