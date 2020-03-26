package domain;

public class Video extends Track {

    private String publicatieDatum;
    private String beschrijving;
    private int aantalWeergaven;

    public Video(int id, String titel, int afspeelduur, boolean offlineBeschikbaar, String performer, String publicatieDatum, String beschrijving, int aantalWeergaven) {
        super(id, titel, afspeelduur, offlineBeschikbaar, performer);
        this.beschrijving = beschrijving;
        this.publicatieDatum = publicatieDatum;
        this.aantalWeergaven = aantalWeergaven;
    }

    public int getAantalWeergaven() {
        return aantalWeergaven;
    }
    public String getPublicatieDatum() {
        return publicatieDatum;
    }
    public String getBeschrijving() {
        return beschrijving;
    }

}
