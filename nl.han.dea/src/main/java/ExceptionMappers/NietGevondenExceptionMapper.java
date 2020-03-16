package ExceptionMappers;
import javax.ws.rs.NotFoundException;
        import javax.ws.rs.core.Response;
        import javax.ws.rs.ext.ExceptionMapper;
        import javax.ws.rs.ext.Provider;

    @Provider
    public class NietGevondenExceptionMapper implements ExceptionMapper<NotFoundException> {

        @Override
        public Response toResponse(NotFoundException e) {
            return null;
        }
    }

