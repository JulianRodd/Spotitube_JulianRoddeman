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
        // Arrange

        // Act
         String result = databasePropertiesUnderTest.connectionString();

        // Assert
        assertEquals("jdbc:mysql://localhost/Spotitube?user=root&password=dbrules&serverTimezone=UTC&autoReconnect=true&useSSL=false", result);
    }
}
