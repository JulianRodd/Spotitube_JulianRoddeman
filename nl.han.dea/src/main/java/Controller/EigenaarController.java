package Controller;

import Datasource.DTOs.EigenaarDTO;
import Datasource.DTOs.LoginResponseDTO;
import Domain.Eigenaar;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class EigenaarController {
    private Eigenaar eigenaar;
@Inject
public void setEigenaar(Eigenaar eigenaar){
this.eigenaar = eigenaar;
}
    @Path("login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response Login(EigenaarDTO eigenaarDTO)  {
        String token = eigenaar.setIngelogd(eigenaarDTO.getUser(), eigenaarDTO.getPassword());
        if (token != null) {
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO(eigenaarDTO.getUser(), token);

            return Response.ok(loginResponseDTO).build();
        } else {
            return Response.status(401).build();
        }
    }

}

