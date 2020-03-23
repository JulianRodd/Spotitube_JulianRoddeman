package exceptions.eigenexcepties;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;

public class VerkeerdeTokenException extends NotAuthorizedException {

    public VerkeerdeTokenException(Response response) {
        super(response);
    }
}
