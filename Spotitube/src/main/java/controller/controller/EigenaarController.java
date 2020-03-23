package controller.controller;

import controller.dtos.EigenaarDTO;
import controller.datamapper.EigenaarDataMapper;
import domain.Eigenaar;
import exceptions.eigenexcepties.OnjuistWachtwoordExceptie;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class EigenaarController {
    EigenaarDataMapper eigenaarDM = new EigenaarDataMapper();
    @Path("login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response Login(EigenaarDTO eigenaarDTO){
        Eigenaar eigenaar = eigenaarDM.mapToDomain(eigenaarDTO);

        try {
            eigenaar.setIngelogd();
        } catch (OnjuistWachtwoordExceptie e) {
            Response.status(401).build();
        }
        return Response.ok(eigenaarDM.mapToDTO(eigenaar)).build();
    }
}
