package controller.dtos;

import java.util.List;

public class TracksDTO {
    private List<TrackDTO> tracks;

    public List<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }
}
