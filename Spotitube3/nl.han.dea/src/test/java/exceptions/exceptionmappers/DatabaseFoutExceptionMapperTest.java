package exceptions.exceptionmappers;

import exceptions.eigenexcepties.DatabaseFoutException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatabaseFoutExceptionMapperTest {

    private DatabaseFoutExceptionMapper databaseFoutExceptionMapperUnderTest;

    @BeforeEach
    void setUp() {
        databaseFoutExceptionMapperUnderTest = new DatabaseFoutExceptionMapper();
    }

    @Test
    void testToResponse() {
        // Arrange
        var e = new DatabaseFoutException("bericht");

        // Act
        var actual = databaseFoutExceptionMapperUnderTest.toResponse(e);
        // Assert
        assertEquals(actual.getStatus(), 500);
        assertEquals(actual.getEntity(), "Er is een databasefout opgetreden: " + e);
    }
}
