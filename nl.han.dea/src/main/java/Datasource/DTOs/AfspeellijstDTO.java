package Datasource.DTOs;

import java.util.List;

public class AfspeellijstDTO {

    private int id;
    private String naam;
    List<TrackDTO> tracks;

    public AfspeellijstDTO(int id, String naam,  List<TrackDTO> tracks) {
        this.id = id;
        this.naam = naam;
        this.tracks = tracks;
    }

    public String getNaam() {
        return naam;
    }

    public void setnaam(String naam) {
        this.naam = naam;
    }

    public List<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}



