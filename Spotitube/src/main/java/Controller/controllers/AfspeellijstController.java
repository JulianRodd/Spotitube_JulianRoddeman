package Controller.controllers;

import Controller.DTOs.AfspeellijstDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class AfspeellijstController {

    @Path("playlists")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response alleAfspeellijsten(@QueryParam("token") int token){
        return Response.ok().build();
    }
    @Path("playlists/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response verwijderAfspeellijst(@PathParam("id") int id,@QueryParam("token") int token){
        return Response.ok().build();
    }
    @Path("playlists")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response voegAfspeellijstToe(AfspeellijstDTO afspeellijstDTO,@QueryParam("token") int token){
        return Response.ok().build();
    }
    @Path("playlists/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response wijzigAfspeellijstNaam(@PathParam("id") int id,@QueryParam("token") int token){
        return Response.ok().build();
    }
    @Path("tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response trackVoorAfspeellijst(@QueryParam("id") int id,@QueryParam("token") int token){
        return Response.ok().build();
    }
    @Path("playlists/{id}/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response trackVanAfspeellijst(@PathParam("id") int id,@QueryParam("token") int token){
        return Response.ok().build();
    }
    @Path("playlists/{afspeellijstId}/tracks/{trackId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response trackVanAfspeellijst(@PathParam("afspeellijstId") int afspeellijstId,@PathParam("trackId") int trackId,@QueryParam("token") int token){
        return Response.ok().build();
    }
}
