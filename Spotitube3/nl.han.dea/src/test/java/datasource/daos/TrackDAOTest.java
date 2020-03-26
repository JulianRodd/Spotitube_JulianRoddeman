package datasource.daos;

import datasource.connectie.DatabaseProperties;
import exceptions.eigenexcepties.DatabaseFoutException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TrackDAOTest {
    private TrackDAO trackDAOUnderTest;
    private DatabaseProperties mockedDatabaseProperties;
    @BeforeEach
    void setUp() {
        trackDAOUnderTest = new TrackDAO();
        this.mockedDatabaseProperties = mock(DatabaseProperties.class);
        this.trackDAOUnderTest.setDatabaseProperties(mockedDatabaseProperties);
    }
    @Test
    void testSelectAllRoeptSQLExceptionAan() {
        //Arrange
        when(mockedDatabaseProperties.connectionString()).thenReturn("ditIsFout");
        // Act&Assert
        assertThrows(DatabaseFoutException.class, () -> trackDAOUnderTest.selectAll());
    }
    @Test
    void testSelectAllRoeptDatabasePropertiesAan() {
        // Arrange
        when(mockedDatabaseProperties.connectionString()).thenReturn("jdbc:mysql://localhost/Spotitube?user=root&password=dbrules&serverTimezone=UTC&autoReconnect=true&useSSL=false");
        // Act
        trackDAOUnderTest.selectAll();

        // Assert
        verify(mockedDatabaseProperties).connectionString();
    }
}
