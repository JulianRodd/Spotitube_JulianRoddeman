package exceptions.exceptionmappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NietGevondenExceptionMapperTest {

    private NietGevondenExceptionMapper nietGevondenExceptionMapperUnderTest;

    @BeforeEach
    void setUp() {
        nietGevondenExceptionMapperUnderTest = new NietGevondenExceptionMapper();
    }

    @Test
    void testToResponse() {
        // Setup
         NotFoundException e = new NotFoundException("message");

        // Run the test
         Response actual = nietGevondenExceptionMapperUnderTest.toResponse(e);
        // Verify the results
        assertEquals(actual.getStatus(), 404);
        assertEquals(actual.getEntity(), e);
    }
}
