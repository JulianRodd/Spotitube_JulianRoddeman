package Controller.controllers;

import Controller.DTOs.AfspeellijstDTO;
import Controller.DTOs.AfspeellijstenDTO;
import Controller.DTOs.TrackDTO;
import Controller.DTOs.TracksDTO;
import Controller.DataMappers.AfspeellijstDataMapper;
import Controller.DataMappers.SpotitubeDataMapper;
import Controller.DataMappers.TrackDataMapper;
import Domain.Afspeellijst;
import Domain.Eigenaar;
import Domain.Spotitube;
import Domain.Track;
import Exceptions.EigenExcepties.VerkeerdeTokenException;
import org.junit.jupiter.api.BeforeAll;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/")
public class AfspeellijstController {
SpotitubeDataMapper spotitubeDM = new SpotitubeDataMapper();
    AfspeellijstDataMapper afspeellijstDM = new AfspeellijstDataMapper();
    TrackDataMapper trackDM = new TrackDataMapper();

    @Path("playlists")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response alleAfspeellijsten(@DefaultValue("0") @QueryParam("token") String token){

        Spotitube spotitube = new Spotitube();
        try {
            spotitube.getEigenaar(token);
        } catch (VerkeerdeTokenException e) {
            return Response.status(400).entity(e).build();
        }
        AfspeellijstenDTO afspeellijstenDTO = spotitubeDM.mapToDTO(spotitube.openOverzicht());
            return Response.ok().entity(afspeellijstenDTO).build();
    }
    @Path("playlists/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response verwijderAfspeellijst(@PathParam("id") int id,@QueryParam("token") String token){
        Spotitube spotitube = new Spotitube();
        try {
            spotitube.getEigenaar(token);
        } catch (VerkeerdeTokenException e) {
            return Response.status(400).entity(e).build();
        }
        spotitube.verwijderAfspeellijst(id);
        AfspeellijstenDTO afspeellijstenDTO = spotitubeDM.mapToDTO(spotitube.openOverzicht());
        return Response.ok().entity(afspeellijstenDTO).build();
    }
    @Path("playlists")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response voegAfspeellijstToe(AfspeellijstDTO afspeellijstDTO,@QueryParam("token") String token){
        Spotitube spotitube = new Spotitube();
        try {
            Eigenaar eigenaar = spotitube.getEigenaar(token);
            eigenaar.maakAfspeellijst(afspeellijstDM.mapToDomain(afspeellijstDTO));
        } catch (VerkeerdeTokenException e) {
            return Response.status(400).entity(e).build();
        }
        AfspeellijstenDTO afspeellijstenDTO = spotitubeDM.mapToDTO(spotitube.openOverzicht());
        return Response.ok().entity(afspeellijstenDTO).build();

    }
    @Path("playlists/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response wijzigAfspeellijstNaam(AfspeellijstDTO afspeellijstDTO, @PathParam("id") int id,@QueryParam("token") String token){
        Spotitube spotitube = new Spotitube();
        try {
            Eigenaar eigenaar = spotitube.getEigenaar(token);
            eigenaar.wijzigAfspeellijst(afspeellijstDM.mapToDomain(afspeellijstDTO));
        } catch (VerkeerdeTokenException e) {
            return Response.status(400).entity(e).build();
        }
        AfspeellijstenDTO afspeellijstenDTO = spotitubeDM.mapToDTO(spotitube.openOverzicht());
        return Response.ok().entity(afspeellijstenDTO).build();
    }
    @Path("tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response trackVoorAfspeellijst(@QueryParam("forPlaylist") int id,@QueryParam("token") String token){
        List<TrackDTO> trackDTOs = new ArrayList<TrackDTO>();
        TracksDTO tracksDTO = new TracksDTO();
        Spotitube spotitube = new Spotitube();
        try {
            spotitube.getEigenaar(token);
        } catch (VerkeerdeTokenException e) {
            return Response.status(400).entity(e).build();
        }
        List<Track> tracks;
        if(id>0) {
            Afspeellijst afspeellijst = spotitube.openAfspeellijst(id);
            tracks = afspeellijst.getTracks();
        }else{
            tracks = spotitube.toonTrackOverzicht();
        }
        for (Track track : tracks) {
            trackDTOs.add(trackDM.mapToDTO(track));
        }
        tracksDTO.setTracks(trackDTOs);
        return Response.ok().entity(tracksDTO).build();
    }

    @Path("playlists/{id}/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response trackVanAfspeellijst(@PathParam("id") int id,@QueryParam("token") String token){
        List<TrackDTO> trackDTOs = new ArrayList<TrackDTO>();
        TracksDTO tracksDTO = new TracksDTO();
        Spotitube spotitube = new Spotitube();
        try {
            spotitube.getEigenaar(token);
        } catch (VerkeerdeTokenException e) {
            return Response.status(400).entity(e).build();
        }
        List<Track> tracks;
       tracks = spotitube.openAfspeellijst(id).getTracks();
        for (Track track : tracks) {
            trackDTOs.add(trackDM.mapToDTO(track));
        }
        tracksDTO.setTracks(trackDTOs);
        return Response.ok().entity(tracksDTO).build();
    }
    @Path("playlists/{afspeellijstId}/tracks/{trackId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response trackVanAfspeellijst(@PathParam("afspeellijstId") int afspeellijstId,@PathParam("trackId") int trackId,@QueryParam("token") String token){
        Spotitube spotitube = new Spotitube();
        try {
            spotitube.getEigenaar(token);
        } catch (VerkeerdeTokenException e) {
            return Response.status(400).entity(e).build();
        }
        Afspeellijst afspeellijst = spotitube.openAfspeellijst(afspeellijstId);
        for(Track track : afspeellijst.getTracks()){
            if(track.getId() == trackId){
                afspeellijst.verwijderTrack(track);
            }
        }
        AfspeellijstenDTO afspeellijstenDTO = spotitubeDM.mapToDTO(spotitube.openOverzicht());
        return Response.ok().entity(afspeellijstenDTO).build();
    }
    @Path("playlists/{id}/tracks")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response trackAanPlaylistToevoegen(TrackDTO trackDTO, @PathParam("id") int id,@QueryParam("token") String token){
        Spotitube spotitube = new Spotitube();
        try {
            spotitube.getEigenaar(token);
        } catch (VerkeerdeTokenException e) {
            return Response.status(400).entity(e).build();
        }
        Afspeellijst afspeellijst = spotitube.openAfspeellijst(id);
        afspeellijst.voegTrackToe(trackDM.mapToDomain(trackDTO));
        AfspeellijstenDTO afspeellijstenDTO = spotitubeDM.mapToDTO(spotitube.openOverzicht());
        return Response.ok().entity(afspeellijstenDTO).build();
    }
}
