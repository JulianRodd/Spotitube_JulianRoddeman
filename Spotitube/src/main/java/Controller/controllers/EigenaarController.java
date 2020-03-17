package Controller.controllers;

import Controller.DTOs.EigenaarDTO;
import Domain.Eigenaar;
import Exceptions.EigenExcepties.OnjuistWachtwoordExceptie;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class EigenaarController {
    @Path("login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response Login(EigenaarDTO eigenaarDTO){
        Eigenaar eigenaar = new Eigenaar(eigenaarDTO.getUser(), eigenaarDTO.getPassword(), false);
        try {
            eigenaar.setIngelogd();
        } catch (OnjuistWachtwoordExceptie e) {
            Response.status(401).build();
        }
        return Response.ok(eigenaar.mapToDTO()).build();
    }
}
