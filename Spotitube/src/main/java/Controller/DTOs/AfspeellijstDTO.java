package Controller.DTOs;

import Domain.Afspeellijst;
import Domain.Track;

import java.util.ArrayList;
import java.util.List;

public class AfspeellijstDTO {
    private int id;
    private String name;
   private boolean owner;
   List<TrackDTO> tracks;
   public void setId(int id){
       this.id = id;
   }
    public void setName(String name){
        this.name = name;
    }
    public void setOwner(boolean owner){
        this.owner = owner;
    }
    public void setTracks(List<TrackDTO> tracks){
        this.tracks = tracks;
    }
    public int getId(){return id;}
    public String getName(){return name;}
    public List<TrackDTO>  getTracks(){return tracks;}
    public boolean getOwner(){return owner;}


}
