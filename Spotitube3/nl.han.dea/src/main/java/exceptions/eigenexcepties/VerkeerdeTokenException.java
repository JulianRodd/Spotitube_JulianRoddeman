package exceptions.eigenexcepties;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;

public class VerkeerdeTokenException extends NotAuthorizedException {

    public VerkeerdeTokenException() {
        super(Response.status(401).build());
    }
}
