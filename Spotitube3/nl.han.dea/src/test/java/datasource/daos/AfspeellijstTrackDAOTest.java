package datasource.daos;

import datasource.connectie.DatabaseProperties;
import domain.Afspeellijst;
import exceptions.eigenexcepties.DatabaseFoutException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AfspeellijstTrackDAOTest {
    private static final int ID = 0;
    private AfspeellijstTrackDAO afspeellijstTrackDAOUnderTest;
    private DatabaseProperties mockedDatabaseProperties;

    @BeforeEach
    void setUp() {
        afspeellijstTrackDAOUnderTest = new AfspeellijstTrackDAO();
        this.mockedDatabaseProperties = mock(DatabaseProperties.class);
        this.afspeellijstTrackDAOUnderTest.setDatabaseProperties(mockedDatabaseProperties);
    }

    @Test
    void testSelectRoeptSQLExceptionAan() {
        //Arrange
        when(mockedDatabaseProperties.connectionString()).thenReturn("ditIsFout");
        // Act&Assert
        assertThrows(DatabaseFoutException.class, () ->afspeellijstTrackDAOUnderTest.select(ID,true));
    }
    @Test
    void testSelectRoeptDatabasePropertiesAan() {
        // Arrange
        when(mockedDatabaseProperties.connectionString()).thenReturn("jdbc:mysql://localhost/Spotitube?user=root&password=dbrules&serverTimezone=UTC&autoReconnect=true&useSSL=false");
        // Act
        afspeellijstTrackDAOUnderTest.select(ID,true);

        // Assert
        verify(mockedDatabaseProperties).connectionString();
    }
    @Test
    void testDeleteRoeptSQLExceptionAan() {
        //Arrange
        when(mockedDatabaseProperties.connectionString()).thenReturn("ditIsFout");
        // Act&Assert
        assertThrows(DatabaseFoutException.class, () ->afspeellijstTrackDAOUnderTest.delete(0, 0));
    }
    @Test
    void testDeleteRoeptDatabasePropertiesAan() {
        // Arrange
        when(mockedDatabaseProperties.connectionString()).thenReturn("jdbc:mysql://localhost/Spotitube?user=root&password=dbrules&serverTimezone=UTC&autoReconnect=true&useSSL=false");
        // Act
        afspeellijstTrackDAOUnderTest.delete(ID, ID);

        // Assert
        verify(mockedDatabaseProperties).connectionString();
    }
    @Test
    void testInsertRoeptSQLExceptionAan() {
        //Arrange
        when(mockedDatabaseProperties.connectionString()).thenReturn("ditIsFout");
        // Act&Assert
        assertThrows(DatabaseFoutException.class, () ->afspeellijstTrackDAOUnderTest.insert(5, 1));
    }
    @Test
    void testInsertRoeptDatabasePropertiesAan() {
        // Arrange
        when(mockedDatabaseProperties.connectionString()).thenReturn("jdbc:mysql://localhost/Spotitube?user=root&password=dbrules&serverTimezone=UTC&autoReconnect=true&useSSL=false");
        // Act
        afspeellijstTrackDAOUnderTest.delete(5, 1);
        afspeellijstTrackDAOUnderTest.insert(5, 1);

        // Assert
        verify(mockedDatabaseProperties, times(2)).connectionString();
    }

}
