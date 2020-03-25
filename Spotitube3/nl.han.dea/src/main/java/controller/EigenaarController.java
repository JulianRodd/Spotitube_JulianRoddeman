package controller;

import controller.datamapper.EigenaarDataMapper;
import controller.dtos.EigenaarDTO;
import domain.Eigenaar;
import exceptions.eigenexcepties.OnjuistWachtwoordExceptie;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class EigenaarController {
    private EigenaarDataMapper eigenaarDataMapper;
    private Eigenaar eigenaar;
    @Inject
    public void setEigenaar(Eigenaar eigenaar) {
        this.eigenaar = eigenaar;
    }

    @Inject
    public void setEigenaarDataMapper(EigenaarDataMapper eigenaarDataMapper) {
        this.eigenaarDataMapper = eigenaarDataMapper;
    }

    @Path("login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(EigenaarDTO eigenaarDTO){
        Eigenaar eigenaar = eigenaarDataMapper.mapToDomain(eigenaarDTO);
        try {
            this.eigenaar.setIngelogd(eigenaar);
        } catch (OnjuistWachtwoordExceptie e) {
            return Response.status(401).build();
        }
        return Response.ok(eigenaarDataMapper.mapToDTO(eigenaar)).build();
    }
}
