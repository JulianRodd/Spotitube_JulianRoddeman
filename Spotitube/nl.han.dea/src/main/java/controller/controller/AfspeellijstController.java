package controller.controller;

import controller.datamapper.AfspeellijstDataMapper;
import controller.datamapper.EigenaarDataMapper;
import controller.datamapper.SpotitubeDataMapper;
import controller.datamapper.TrackDataMapper;
import controller.dtos.AfspeellijstDTO;
import controller.dtos.AfspeellijstenDTO;
import controller.dtos.TrackDTO;
import controller.dtos.TracksDTO;
import domain.Afspeellijst;
import domain.Eigenaar;
import domain.Spotitube;
import domain.Track;
import exceptions.eigenexcepties.VerkeerdeTokenException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/")
public class AfspeellijstController {
    private SpotitubeDataMapper spotitubeDM;
    private AfspeellijstDataMapper afspeellijstDM;
    private TrackDataMapper trackDM;
    private Spotitube spotitube;
    private Eigenaar eigenaar;
@Inject
    public void setEigenaar(Eigenaar eigenaar) {
        this.eigenaar = eigenaar;
    }
    @Inject
    public void setAfspeellijst(Afspeellijst afspeellijst) {
        this.afspeellijst = afspeellijst;
    }

    private Afspeellijst afspeellijst;

    @Inject
    public void setSpotitube(Spotitube spotitube) {
        this.spotitube = spotitube;
    }

    @Inject
    public void setSpotitubeDM(SpotitubeDataMapper spotitubeDM) {
        this.spotitubeDM = spotitubeDM;
    }

    @Inject
    public void setAfspeellijstDM(AfspeellijstDataMapper afspeellijstDM) {
        this.afspeellijstDM = afspeellijstDM;
    }

    @Inject
    public void setTrackDM(TrackDataMapper trackDM) {
        this.trackDM = trackDM;
    }

    @Path("playlists")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response alleAfspeellijsten(@DefaultValue("0") @QueryParam("token") String token) {
  spotitube.getEigenaar(token);
        return Response.ok().entity(spotitubeDM.mapToDTO(spotitube.openOverzicht())).build();
    }

    @Path("playlists/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response verwijderAfspeellijst(@PathParam("id") int id, @QueryParam("token") String token) { spotitube.getEigenaar(token);
        spotitube.verwijderAfspeellijst(id);
        return Response.ok().entity(spotitubeDM.mapToDTO(spotitube.openOverzicht())).build();
    }

    @Path("playlists")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response voegAfspeellijstToe(AfspeellijstDTO afspeellijstDTO, @QueryParam("token") String token) {
        eigenaar = spotitube.getEigenaar(token);
        eigenaar.maakAfspeellijst(afspeellijstDM.mapToDomain(afspeellijstDTO));
        return Response.ok().entity(spotitubeDM.mapToDTO(spotitube.openOverzicht())).build();

    }

    @Path("playlists/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response wijzigAfspeellijstNaam(AfspeellijstDTO afspeellijstDTO, @PathParam("id") int id, @QueryParam("token") String token) {
        eigenaar = spotitube.getEigenaar(token);
        eigenaar.wijzigAfspeellijst(afspeellijstDM.mapToDomain(afspeellijstDTO));
        return Response.ok().entity(spotitubeDM.mapToDTO(spotitube.openOverzicht())).build();
    }

    @Path("tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response trackVoorAfspeellijst(@QueryParam("forPlaylist") int id, @QueryParam("token") String token) {
        spotitube.getEigenaar(token);
        List<TrackDTO> trackDTOs = new ArrayList<TrackDTO>();
        TracksDTO tracksDTO = new TracksDTO();
        tracksDTO.setTracks(trackDTOs);
        return Response.ok().entity(tracksDTO).build();
    }

    @Path("playlists/{id}/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response trackVanAfspeellijst(@PathParam("id") int id, @QueryParam("token") String token) {
        List<TrackDTO> trackDTOs = new ArrayList<TrackDTO>();
        TracksDTO tracksDTO = new TracksDTO();
      spotitube.getEigenaar(token);
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
    public Response trackVanAfspeellijst(@PathParam("afspeellijstId") int afspeellijstId, @PathParam("trackId") int trackId, @QueryParam("token") String token) {
       spotitube.getEigenaar(token);
        afspeellijst = spotitube.openAfspeellijst(afspeellijstId);
        for (Track track : afspeellijst.getTracks()) {
            if (track.getId() == trackId) {
                afspeellijst.verwijderTrack(track);
            }
        }
        return Response.ok().entity(spotitubeDM.mapToDTO(spotitube.openOverzicht())).build();
    }

    @Path("playlists/{id}/tracks")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response trackAanPlaylistToevoegen(TrackDTO trackDTO, @PathParam("id") int id, @QueryParam("token") String token) {
        spotitube.getEigenaar(token);
        afspeellijst = spotitube.openAfspeellijst(id);
        afspeellijst.voegTrackToe(trackDM.mapToDomain(trackDTO));
        return Response.ok().entity(spotitubeDM.mapToDTO(spotitube.openOverzicht())).build();
    }
}
