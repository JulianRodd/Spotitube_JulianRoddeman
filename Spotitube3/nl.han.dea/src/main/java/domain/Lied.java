package domain;

public class Lied extends Track {
    private String album;

    public String getAlbum() {
        return album;
    }

    public Lied(int id, String titel, int afspeelduur, boolean offlineBeschikbaar, String performer, String album) {
        super(id, titel, afspeelduur, offlineBeschikbaar, performer);
        this.album = album;
    }
}
