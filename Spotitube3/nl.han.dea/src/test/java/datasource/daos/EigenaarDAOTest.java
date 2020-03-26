package datasource.daos;

import datasource.connectie.DatabaseProperties;
import domain.Afspeellijst;
import domain.Eigenaar;
import exceptions.eigenexcepties.DatabaseFoutException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EigenaarDAOTest {

    private EigenaarDAO eigenaarDAOUnderTest;
    private DatabaseProperties mockedDatabaseProperties;
    @BeforeEach
    void setUp() {
        eigenaarDAOUnderTest = new EigenaarDAO();
        this.mockedDatabaseProperties = mock(DatabaseProperties.class);
        this.eigenaarDAOUnderTest.setDatabaseProperties(mockedDatabaseProperties);
    }
    @Test
    void testSelectUpdateRoeptSQLExceptionAan() {
        //Arrange
        when(mockedDatabaseProperties.connectionString()).thenReturn("ditIsFout");
        // Act&Assert
        assertThrows(DatabaseFoutException.class, () -> eigenaarDAOUnderTest.select("gebruiker"));
    }
    @Test
    void testSelectRoeptDatabasePropertiesAan() {
        // Arrange
        when(mockedDatabaseProperties.connectionString()).thenReturn("jdbc:mysql://localhost/Spotitube?user=root&password=dbrules&serverTimezone=UTC&autoReconnect=true&useSSL=false");
        // Act
        eigenaarDAOUnderTest.select("gebruiker");

        // Assert
        verify(mockedDatabaseProperties).connectionString();
    }
    @Test
    void testUpdateUpdateRoeptSQLExceptionAan() {
        //Arrange
        var eigenaar = new Eigenaar();
        when(mockedDatabaseProperties.connectionString()).thenReturn("ditIsFout");
        // Act&Assert
        assertThrows(DatabaseFoutException.class, () -> eigenaarDAOUnderTest.update(eigenaar));
    }
    @Test
    void testUpdateRoeptDatabasePropertiesAan() {
        // Arrange
        var eigenaar = new Eigenaar();
        when(mockedDatabaseProperties.connectionString()).thenReturn("jdbc:mysql://localhost/Spotitube?user=root&password=dbrules&serverTimezone=UTC&autoReconnect=true&useSSL=false");
        // Act
        eigenaarDAOUnderTest.update(eigenaar);

        // Assert
        verify(mockedDatabaseProperties).connectionString();
    }
    @Test
    void testGetEigenaarMetTokenUpdateRoeptSQLExceptionAan() {
        //Arrange
        when(mockedDatabaseProperties.connectionString()).thenReturn("ditIsFout");
        // Act&Assert
        assertThrows(DatabaseFoutException.class, () -> eigenaarDAOUnderTest.getEigenaarMetToken("token"));
    }
    @Test
    void testGetEigenaarMetTokenRoeptDatabasePropertiesAan() {
        // Arrange
        when(mockedDatabaseProperties.connectionString()).thenReturn("jdbc:mysql://localhost/Spotitube?user=root&password=dbrules&serverTimezone=UTC&autoReconnect=true&useSSL=false");
        // Act
        eigenaarDAOUnderTest.getEigenaarMetToken("token");

        // Assert
        verify(mockedDatabaseProperties).connectionString();
    }

}
