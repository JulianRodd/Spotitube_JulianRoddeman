package domain;


public class Video extends Track {

	private String publicatieDatum;
	private String beschrijving;

	public String getPublicatieDatum() {
		return publicatieDatum;
	}

	public void setPublicatieDatum(String publicatieDatum) {
		this.publicatieDatum = publicatieDatum;
	}

	public String getBeschrijving() {
		return beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}
	public Video(int id, String titel, String url, int afspeelduur, boolean offlineAvailable, String performer, String publicatieDatum, String beschrijving) {
		super(id, titel, url, afspeelduur, offlineAvailable, performer);
		this.beschrijving = beschrijving;
		this.publicatieDatum = publicatieDatum;
	}
}
