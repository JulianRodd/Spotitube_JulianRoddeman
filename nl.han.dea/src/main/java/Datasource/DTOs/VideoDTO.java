package Datasource.DTOs;

public class VideoDTO extends TrackDTO {

    private String publicatieDatum;
    private String beschrijving;
    public VideoDTO(String titel, String performer, String url, int afspeelduur, boolean offlineAvailable, String publicatieDatum, String beschrijving) {
        super(titel, performer, url, afspeelduur, offlineAvailable);
        this.beschrijving = beschrijving;
        this.publicatieDatum = publicatieDatum;
    }
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

}
