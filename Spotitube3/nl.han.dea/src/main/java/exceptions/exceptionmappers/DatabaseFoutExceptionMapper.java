package exceptions.exceptionmappers;

import exceptions.eigenexcepties.DatabaseFoutException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DatabaseFoutExceptionMapper implements ExceptionMapper<DatabaseFoutException> {
    @Override
    public Response toResponse(DatabaseFoutException e) {
        return Response.status(500).entity("Er is een databasefout opgetreden: " + e).build();
    }
}
