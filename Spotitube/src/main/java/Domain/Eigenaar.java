package Domain;

import Controller.DTOs.LoginDTO;
import Datasource.DAOs.AfspeellijstDao;
import Datasource.DAOs.EigenaarDao;
import Exceptions.EigenExcepties.OnjuistWachtwoordExceptie;
import org.apache.commons.codec.digest.DigestUtils;

import javax.sound.midi.Track;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Eigenaar {

	private String gebruikersnaam;
	private String wachtwoord;
	private String token;
	private boolean ingelogd;
	private List<Afspeellijst> eigendommen;
	private EigenaarDao eigenaarDAO;
	private AfspeellijstDao afspeellijstDao;

	public Eigenaar(String gebruikersnaam, String wachtwoord, boolean ingelogd) {
		this.gebruikersnaam = gebruikersnaam;
		this.wachtwoord = wachtwoord;
		this.ingelogd = ingelogd;
		eigenaarDAO = new EigenaarDao();
		afspeellijstDao = new AfspeellijstDao();
		eigendommen = new ArrayList<Afspeellijst>();
	}

	public void setIngelogd() throws OnjuistWachtwoordExceptie {
		String wachtwoordHash = eigenaarDAO.select(this.gebruikersnaam, "wachtwoord");
		if(wachtwoordHash!=null){
			System.out.println(wachtwoordHash);
			if((DigestUtils.sha256Hex(this.wachtwoord).equals(wachtwoordHash))){
				String nieuweToken = UUID.randomUUID().toString();
					eigenaarDAO.update(this.gebruikersnaam,"token", nieuweToken);
					this.token = nieuweToken;
					ingelogd = true;
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
	public LoginDTO mapToDTO(){
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setUser(gebruikersnaam);
		loginDTO.setToken(token);
		return loginDTO;
	}
		public void wijzigAfspeellijst(Afspeellijst afspeellijst) {
			afspeellijst.updateTracks();
			afspeellijstDao.update(afspeellijst);
	}


}
