package datasource.connectie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatabasePropertiesTest {

    private DatabaseProperties databasePropertiesUnderTest;

    @BeforeEach
    void setUp() {
        databasePropertiesUnderTest = new DatabaseProperties();
    }

    @Test
    void testConnectionString() {
        // Setup

        // Run the test
        final String result = databasePropertiesUnderTest.connectionString();

        // Verify the results
        assertEquals("result", result);
    }
}
