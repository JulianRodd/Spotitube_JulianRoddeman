package Domain;

import Controller.DTOs.TrackDTO;

public abstract class Track {

	private int id;
	private String titel;

	private String url;

	private int afspeelduur;

	private int resterendeTrackTijd;

	private boolean offlineAvailable;

	private String performer;

	public Track(int id, String titel, String url, int afspeelduur, boolean offlineAvailable, String performer) {
		this.id = id;
		this.titel = titel;
		this.url = url;
		this.afspeelduur = afspeelduur;
		this.offlineAvailable = offlineAvailable;
		this.performer = performer;
	}


	public void speelAf() {

	}

	public int getResterendeTijd() {
		return 0;
	}

	public void stopSpelen() {

	}


	public abstract TrackDTO mapToDTO();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getAfspeelduur() {
		return afspeelduur;
	}

	public void setAfspeelduur(int afspeelduur) {
		this.afspeelduur = afspeelduur;
	}

	public int getResterendeTrackTijd() {
		return resterendeTrackTijd;
	}

	public void setResterendeTrackTijd(int resterendeTrackTijd) {
		this.resterendeTrackTijd = resterendeTrackTijd;
	}

	public boolean isOfflineAvailable() {
		return offlineAvailable;
	}

	public void setOfflineAvailable(boolean offlineAvailable) {
		this.offlineAvailable = offlineAvailable;
	}

	public String getPerformer() {
		return performer;
	}

	public void setPerformer(String performer) {
		this.performer = performer;
	}
}
