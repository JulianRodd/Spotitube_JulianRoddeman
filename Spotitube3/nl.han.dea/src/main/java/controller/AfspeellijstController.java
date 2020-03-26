package controller;
import controller.datamapper.AfspeellijstDTODataMapper;
import controller.datamapper.AfspeellijstenDTODataMapper;
import controller.datamapper.TrackDTODataMapper;
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
    private AfspeellijstenDTODataMapper afspeellijstenDTODataMapper;
    private AfspeellijstDTODataMapper afspeellijstDTODataMapper;
    private TrackDTODataMapper trackDTODataMapper;
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
    public void setAfspeellijstenDTODataMapper(AfspeellijstenDTODataMapper afspeellijstenDTODataMapper) {
        this.afspeellijstenDTODataMapper = afspeellijstenDTODataMapper;
    }
    @Inject
    public void setAfspeellijstDTODataMapper(AfspeellijstDTODataMapper afspeellijstDTODataMapper) {
        this.afspeellijstDTODataMapper = afspeellijstDTODataMapper;
    }
    @Inject
    public void setTrackDTODataMapper(TrackDTODataMapper trackDTODataMapper) {
        this.trackDTODataMapper = trackDTODataMapper;
    }

    @Path("playlists")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response alleAfspeellijsten(@DefaultValue("0") @QueryParam("token") String token) {
        eigenaar.getEigenaar(token);
        return Response.ok().entity(afspeellijstenDTODataMapper.mapToDTO(spotitube.openOverzicht())).build();
    }

    @Path("playlists/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response verwijderAfspeellijst(@PathParam("id") int id, @QueryParam("token") String token) {
        eigenaar.getEigenaar(token);
        spotitube.verwijderAfspeellijst(id);
        return Response.ok().entity(afspeellijstenDTODataMapper.mapToDTO(spotitube.openOverzicht())).build();
    }

    @Path("playlists")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response voegAfspeellijstToe(AfspeellijstDTO afspeellijstDTO, @QueryParam("token") String token) {
        Afspeellijst afspeellijst = afspeellijstDTODataMapper.mapToDomain(afspeellijstDTO);
        afspeellijst.setEigenaar(eigenaar.getEigenaar(token).getGebruikersnaam());
        eigenaar.maakAfspeellijst(afspeellijst);
        return Response.ok().entity(afspeellijstenDTODataMapper.mapToDTO(spotitube.openOverzicht())).build();

    }

    @Path("playlists/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response wijzigAfspeellijstNaam(AfspeellijstDTO afspeellijstDTO, @PathParam("id") int id, @QueryParam("token") String token) {
        eigenaar.getEigenaar(token);
        eigenaar.wijzigAfspeellijst(afspeellijstDTODataMapper.mapToDomain(afspeellijstDTO));
        return Response.ok().entity(afspeellijstenDTODataMapper.mapToDTO(spotitube.openOverzicht())).build();
    }

    @Path("tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response trackVoorAfspeellijst(@QueryParam("forPlaylist") int id, @QueryParam("token") String token) {
        eigenaar.getEigenaar(token);
        List<TrackDTO> trackDTOs = new ArrayList<TrackDTO>();
        TracksDTO tracksDTO = new TracksDTO();
        if (id != 0) {
            for(Track track : afspeellijst.openTracksAfspeellijst(id, true)){
                trackDTOs.add(trackDTODataMapper.mapToDTO(track));
            }
        }else {
            for(Track track : spotitube.toonTrackOverzicht()){
                trackDTOs.add(trackDTODataMapper.mapToDTO(track));
            }
        }
        tracksDTO.setTracks(trackDTOs);
        return Response.ok().entity(tracksDTO).build();
    }

    @Path("playlists/{id}/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response trackVanAfspeellijst(@PathParam("id") int id, @QueryParam("token") String token) {
        eigenaar.getEigenaar(token);
        List<TrackDTO> trackDTOs = new ArrayList<TrackDTO>();
        TracksDTO tracksDTO = new TracksDTO();
        List<Track>tracks = afspeellijst.openTracksAfspeellijst(id, false);
        for (Track track : tracks) {
            trackDTOs.add(trackDTODataMapper.mapToDTO(track));
        }
        tracksDTO.setTracks(trackDTOs);
        return Response.ok().entity(tracksDTO).build();
    }

    @Path("playlists/{afspeellijstId}/tracks/{trackId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response trackVanAfspeellijst(@PathParam("afspeellijstId") int afspeellijstId, @PathParam("trackId") int trackId, @QueryParam("token") String token) {
        eigenaar.getEigenaar(token);
        this.afspeellijst.verwijderTrack(afspeellijstId, trackId);
        return Response.ok().entity(afspeellijstenDTODataMapper.mapToDTO(spotitube.openOverzicht())).build();
    }

    @Path("playlists/{id}/tracks")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response trackAanPlaylistToevoegen(TrackDTO trackDTO, @PathParam("id") int id, @QueryParam("token") String token) {
        eigenaar.getEigenaar(token);
        Afspeellijst afspeellijst = spotitube.openAfspeellijst(id);
        this.afspeellijst.voegTrackToe(trackDTODataMapper.mapToDomain(trackDTO), afspeellijst);
        return Response.ok().entity(afspeellijstenDTODataMapper.mapToDTO(spotitube.openOverzicht())).build();
    }

}
