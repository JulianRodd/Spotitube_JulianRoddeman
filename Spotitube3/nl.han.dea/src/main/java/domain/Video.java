package domain;


public class Video extends Track {

	private String publicatieDatum;
	private String beschrijving;
	private int aantalWeergaven;

	public int getAantalWeergaven() {
		return aantalWeergaven;
	}
	public String getPublicatieDatum() {
		return publicatieDatum;
	}
	public String getBeschrijving() {
		return beschrijving;
	}

	public Video(int id, String titel, String url, int afspeelduur, boolean offlineAvailable, String performer, String publicatieDatum, String beschrijving, int aantalWeergaven) {
		super(id, titel, url, afspeelduur, offlineAvailable, performer);
		this.beschrijving = beschrijving;
		this.publicatieDatum = publicatieDatum;
		this.aantalWeergaven = aantalWeergaven;
	}
}
