package domain;


public class Lied extends Track {
    private String album;
    public String getAlbum() {
        return album;
    }
    public Lied(int id, String titel, String url, int afspeelduur, boolean offlineAvailable, String performer, String album) {
        super(id, titel, url, afspeelduur, offlineAvailable, performer);
        this.album = album;
    }

}
