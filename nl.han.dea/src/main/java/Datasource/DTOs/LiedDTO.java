package Datasource.DTOs;

public class LiedDTO extends TrackDTO{
    private String album;
    public LiedDTO(String titel, String performer, String url, int afspeelduur, boolean offlineAvailable, String album) {
        super(titel, performer, url, afspeelduur, offlineAvailable);
        this.album =album;
    }
    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }


}
