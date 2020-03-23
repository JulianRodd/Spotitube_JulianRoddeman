package domain;

public abstract class Track {

	private int id;
	private String titel;

	private String url;

	private int afspeelduur;

	private int resterendeTrackTijd;

	private boolean offlineBeschikbaar;

	private String performer;

	public Track(int id, String titel, String url, int afspeelduur, boolean offlineBeschikbaar, String performer) {
		this.id = id;
		this.titel = titel;
		this.url = url;
		this.afspeelduur = afspeelduur;
		this.offlineBeschikbaar = offlineBeschikbaar;
		this.performer = performer;
	}


	public void speelAf() {

	}

	public int getResterendeTijd() {
		return 0;
	}

	public void stopSpelen() {

	}


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

	public boolean isOfflineBeschikbaar() {
		return offlineBeschikbaar;
	}

	public void setOfflineBeschikbaar(boolean offlineBeschikbaar) {
		this.offlineBeschikbaar = offlineBeschikbaar;
	}

	public String getPerformer() {
		return performer;
	}

	public void setPerformer(String performer) {
		this.performer = performer;
	}
}
