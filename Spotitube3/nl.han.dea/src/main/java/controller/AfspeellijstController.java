package controller;
import controller.datamapper.AfspeellijstDataMapper;
import controller.datamapper.SpotitubeDataMapper;
import controller.datamapper.TrackDataMapper;
import controller.dtos.AfspeellijstDTO;
import controller.dtos.TrackDTO;
import controller.dtos.TracksDTO;
import domain.Afspeellijst;
import domain.Eigenaar;
import domain.Spotitube;
import domain.Track;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/")
public class AfspeellijstController {
    private SpotitubeDataMapper spotitubeDataMapper;
    private AfspeellijstDataMapper afspeellijstDataMapper;
    private TrackDataMapper trackDataMapper;
    private Spotitube spotitube;
    private Eigenaar eigenaar;
    private Afspeellijst afspeellijst;
    @Inject
    public void setEigenaar(Eigenaar eigenaar) {
        this.eigenaar = eigenaar;
    }
    @Inject
    public void setAfspeellijst(Afspeellijst afspeellijst) {
        this.afspeellijst = afspeellijst;
    }
    @Inject
    public void setSpotitube(Spotitube spotitube) {
        this.spotitube = spotitube;
    }
    @Inject
    public void setSpotitubeDataMapper(SpotitubeDataMapper spotitubeDataMapper) {
        this.spotitubeDataMapper = spotitubeDataMapper;
    }
    @Inject
    public void setAfspeellijstDataMapper(AfspeellijstDataMapper afspeellijstDataMapper) {
        this.afspeellijstDataMapper = afspeellijstDataMapper;
    }
    @Inject
    public void setTrackDataMapper(TrackDataMapper trackDataMapper) {
        this.trackDataMapper = trackDataMapper;
    }

    @Path("playlists")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response alleAfspeellijsten(@DefaultValue("0") @QueryParam("token") String token) {
        spotitube.getEigenaar(token);
        return Response.ok().entity(spotitubeDataMapper.mapToDTO(spotitube.openOverzicht())).build();
    }

    @Path("playlists/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response verwijderAfspeellijst(@PathParam("id") int id, @QueryParam("token") String token) {
        spotitube.getEigenaar(token);
        spotitube.verwijderAfspeellijst(id);
        return Response.ok().entity(spotitubeDataMapper.mapToDTO(spotitube.openOverzicht())).build();
    }

    @Path("playlists")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response voegAfspeellijstToe(AfspeellijstDTO afspeellijstDTO, @QueryParam("token") String token) {
        Afspeellijst afspeellijst = afspeellijstDataMapper.mapToDomain(afspeellijstDTO);
        afspeellijst.setEigenaar(spotitube.getEigenaar(token).getGebruikersnaam());
        eigenaar.maakAfspeellijst(afspeellijst);
        return Response.ok().entity(spotitubeDataMapper.mapToDTO(spotitube.openOverzicht())).build();

    }

    @Path("playlists/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response wijzigAfspeellijstNaam(AfspeellijstDTO afspeellijstDTO, @PathParam("id") int id, @QueryParam("token") String token) {
        spotitube.getEigenaar(token);
        eigenaar.wijzigAfspeellijst(afspeellijstDataMapper.mapToDomain(afspeellijstDTO));
        return Response.ok().entity(spotitubeDataMapper.mapToDTO(spotitube.openOverzicht())).build();
    }

    @Path("tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response trackVoorAfspeellijst(@QueryParam("forPlaylist") int id, @QueryParam("token") String token) {
        spotitube.getEigenaar(token);
        List<TrackDTO> trackDTOs = new ArrayList<TrackDTO>();
        TracksDTO tracksDTO = new TracksDTO();
        if (id != 0) {
            for(Track track : afspeellijst.openTracksAfspeellijst(id, true)){
                trackDTOs.add(trackDataMapper.mapToDTO(track));
            }
        }else {
            for(Track track : spotitube.toonTrackOverzicht()){
                trackDTOs.add(trackDataMapper.mapToDTO(track));
            }
        }
        tracksDTO.setTracks(trackDTOs);
        return Response.ok().entity(tracksDTO).build();
    }

    @Path("playlists/{id}/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response trackVanAfspeellijst(@PathParam("id") int id, @QueryParam("token") String token) {
        spotitube.getEigenaar(token);
        List<TrackDTO> trackDTOs = new ArrayList<TrackDTO>();
        TracksDTO tracksDTO = new TracksDTO();
        List<Track>tracks = afspeellijst.openTracksAfspeellijst(id, false);
        for (Track track : tracks) {
            trackDTOs.add(trackDataMapper.mapToDTO(track));
        }
        tracksDTO.setTracks(trackDTOs);
        return Response.ok().entity(tracksDTO).build();
    }

    @Path("playlists/{afspeellijstId}/tracks/{trackId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response trackVanAfspeellijst(@PathParam("afspeellijstId") int afspeellijstId, @PathParam("trackId") int trackId, @QueryParam("token") String token) {
       spotitube.getEigenaar(token);
        this.afspeellijst.verwijderTrack(afspeellijstId, trackId);
        return Response.ok().entity(spotitubeDataMapper.mapToDTO(spotitube.openOverzicht())).build();
    }

    @Path("playlists/{id}/tracks")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response trackAanPlaylistToevoegen(TrackDTO trackDTO, @PathParam("id") int id, @QueryParam("token") String token) {
        spotitube.getEigenaar(token);
        Afspeellijst afspeellijst = spotitube.openAfspeellijst(id);
        this.afspeellijst.voegTrackToe(trackDataMapper.mapToDomain(trackDTO), afspeellijst);
        return Response.ok().entity(spotitubeDataMapper.mapToDTO(spotitube.openOverzicht())).build();
    }

}
