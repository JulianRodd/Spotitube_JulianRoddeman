package Controller.controllers;

import Controller.DTOs.AfspeellijstDTO;
import Controller.DTOs.AfspeellijstenDTO;
import Controller.DTOs.TrackDTO;
import Controller.DTOs.TracksDTO;
import Domain.Afspeellijst;
import Domain.Spotitube;
import Domain.Track;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/")
public class AfspeellijstController {

    @Path("playlists")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response alleAfspeellijsten(@QueryParam("token") int token){
        Spotitube spotitube = new Spotitube();
        AfspeellijstenDTO afspeellijstenDTO = spotitube.mapToDTO(spotitube.openOverzicht());
        return Response.ok().entity(afspeellijstenDTO).build();
    }
    @Path("playlists/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response verwijderAfspeellijst(@PathParam("id") int id,@QueryParam("token") int token){
        Spotitube spotitube = new Spotitube();
        spotitube.verwijderAfspeellijst(id);
        AfspeellijstenDTO afspeellijstenDTO = spotitube.mapToDTO(spotitube.openOverzicht());
        return Response.ok().entity(afspeellijstenDTO).build();
    }
    @Path("playlists")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response voegAfspeellijstToe(AfspeellijstDTO afspeellijstDTO,@QueryParam("token") int token){
        Spotitube spotitube = new Spotitube();
      spotitube.voegNieuweAfspeellijstToe(afspeellijstDTO.mapToDomain());
        AfspeellijstenDTO afspeellijstenDTO = spotitube.mapToDTO(spotitube.openOverzicht());
        return Response.ok().entity(afspeellijstenDTO).build();
    }
    @Path("playlists/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response wijzigAfspeellijstNaam(AfspeellijstDTO afspeellijstDTO, @PathParam("id") int id,@QueryParam("token") int token){
        Spotitube spotitube = new Spotitube();
        spotitube.wijzigAfspeellijst(afspeellijstDTO.mapToDomain());
        AfspeellijstenDTO afspeellijstenDTO = spotitube.mapToDTO(spotitube.openOverzicht());
        return Response.ok().entity(afspeellijstenDTO).build();
    }
    @Path("tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response trackVoorAfspeellijst(@QueryParam("forPlaylist") int id,@QueryParam("token") int token){
        List<TrackDTO> trackDTOs = new ArrayList<TrackDTO>();
        TracksDTO tracksDTO = new TracksDTO();
        Spotitube spotitube = new Spotitube();
        List<Track> tracks;
        if(id>0) {
            Afspeellijst afspeellijst = spotitube.openAfspeellijst(id);
            tracks = afspeellijst.getTracks();
        }else{
            tracks = spotitube.toonTrackOverzicht();
        }
        for (Track track : tracks) {
            trackDTOs.add(track.mapToDTO());
        }
        tracksDTO.setTracks(trackDTOs);
        return Response.ok().entity(tracksDTO).build();
    }

    @Path("playlists/{id}/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response trackVanAfspeellijst(@PathParam("id") int id,@QueryParam("token") int token){
        List<TrackDTO> trackDTOs = new ArrayList<TrackDTO>();
        TracksDTO tracksDTO = new TracksDTO();
        Spotitube spotitube = new Spotitube();
        List<Track> tracks;
       tracks = spotitube.openAfspeellijst(id).getTracks();
        for (Track track : tracks) {
            trackDTOs.add(track.mapToDTO());
        }
        tracksDTO.setTracks(trackDTOs);
        return Response.ok().entity(tracksDTO).build();
    }
    @Path("playlists/{afspeellijstId}/tracks/{trackId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response trackVanAfspeellijst(@PathParam("afspeellijstId") int afspeellijstId,@PathParam("trackId") int trackId,@QueryParam("token") int token){
        Spotitube spotitube = new Spotitube();
        Afspeellijst afspeellijst = spotitube.openAfspeellijst(afspeellijstId);
        for(Track track : afspeellijst.getTracks()){
            if(track.getId() == trackId){
                afspeellijst.verwijderTrack(track);
            }
        }
        AfspeellijstenDTO afspeellijstenDTO = spotitube.mapToDTO(spotitube.openOverzicht());
        return Response.ok().entity(afspeellijstenDTO).build();
    }
    @Path("playlists/{id}/tracks")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response trackAanPlaylistToevoegen(TrackDTO trackDTO, @PathParam("id") int id,@QueryParam("token") int token){
        Spotitube spotitube = new Spotitube();
        Afspeellijst afspeellijst = spotitube.openAfspeellijst(id);
        afspeellijst.voegTrackToe(trackDTO.mapToDomain());
        AfspeellijstenDTO afspeellijstenDTO = spotitube.mapToDTO(spotitube.openOverzicht());
        return Response.ok().entity(afspeellijstenDTO).build();
    }
}
