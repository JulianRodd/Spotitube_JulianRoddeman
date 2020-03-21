package Controller.DTOs;

import java.util.List;

public class AfspeellijstenDTO {
    private List<AfspeellijstDTO> playlists;
    private int length;

    public void setPlaylists(List<AfspeellijstDTO> playlists) {
        this.playlists = playlists;
    }
    public List<AfspeellijstDTO> getPlaylists() {
        return playlists;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
