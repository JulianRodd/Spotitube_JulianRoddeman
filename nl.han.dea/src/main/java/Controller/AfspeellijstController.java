package Controller;

import Datasource.DTOs.AfspeellijstDTO;
import Datasource.DTOs.EigenaarDTO;
import Datasource.DTOs.LoginResponseDTO;
import Datasource.DTOs.TrackDTO;
import Domain.Afspeellijst;
import Domain.Eigenaar;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class AfspeellijstController {
    private Afspeellijst afspeellijst;
    @Inject
    public void setAfspeellijst(Afspeellijst afspeellijst){
        this.afspeellijst = afspeellijst;
    }
    @Path("{id}/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response openTrackOverzicht(@PathParam("id") int id)  {
        List<TrackDTO> tracks = afspeellijst.getTracks(id);
        return Response.ok(tracks).build();
    }

}

