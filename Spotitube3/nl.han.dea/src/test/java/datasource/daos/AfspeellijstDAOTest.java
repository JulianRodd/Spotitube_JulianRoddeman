package datasource.daos;

import datasource.connectie.DatabaseProperties;
import domain.Afspeellijst;
import exceptions.eigenexcepties.DatabaseFoutException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AfspeellijstDAOTest {
    private static final int ID = 0;
    private AfspeellijstDAO afspeellijstDAOUnderTest;
    private DatabaseProperties mockedDatabaseProperties;

    @BeforeEach
    void setUp() {
        afspeellijstDAOUnderTest = new AfspeellijstDAO();
        this.mockedDatabaseProperties = mock(DatabaseProperties.class);
        this.afspeellijstDAOUnderTest.setDatabaseProperties(mockedDatabaseProperties);
    }

    @Test
    void testUpdateRoeptSQLExceptionAan() {
        //Arrange
        var afspeellijst = new Afspeellijst();
        when(mockedDatabaseProperties.connectionString()).thenReturn("ditIsFout");
        // Act&Assert
        assertThrows(DatabaseFoutException.class, () -> afspeellijstDAOUnderTest.update(afspeellijst));
    }
    @Test
    void testUpdateRoeptDatabasePropertiesAan() {
        //Arrange
        var afspeellijst = new Afspeellijst();
        when(mockedDatabaseProperties.connectionString()).thenReturn("jdbc:mysql://localhost/Spotitube?user=root&password=dbrules&serverTimezone=UTC&autoReconnect=true&useSSL=false");
        //Act
        afspeellijstDAOUnderTest.update(afspeellijst);

        // Assert
        verify(mockedDatabaseProperties).connectionString();
    }
    @Test
    void testSelectAllRoeptSQLExceptionAan() {
        //Arrange
        when(mockedDatabaseProperties.connectionString()).thenReturn("ditIsFout");
        // Act&Assert
        assertThrows(DatabaseFoutException.class, () -> afspeellijstDAOUnderTest.selectAll());
    }
    @Test
    void testSelectAllRoeptDatabasePropertiesAan() {
        // Arrange
        when(mockedDatabaseProperties.connectionString()).thenReturn("jdbc:mysql://localhost/Spotitube?user=root&password=dbrules&serverTimezone=UTC&autoReconnect=true&useSSL=false");
        // Act
        afspeellijstDAOUnderTest.selectAll();

        // Assert
        verify(mockedDatabaseProperties).connectionString();
    }
    @Test
    void testSelectRoeptSQLExceptionAan() {
        //Arrange
        when(mockedDatabaseProperties.connectionString()).thenReturn("ditIsFout");
        // Act&Assert
        assertThrows(DatabaseFoutException.class, () -> afspeellijstDAOUnderTest.select(1));
    }
    @Test
    void testSelectRoeptDatabasePropertiesAan() {
        // Arrange
        when(mockedDatabaseProperties.connectionString()).thenReturn("jdbc:mysql://localhost/Spotitube?user=root&password=dbrules&serverTimezone=UTC&autoReconnect=true&useSSL=false");
        // Act
        afspeellijstDAOUnderTest.select(ID);

        // Assert
        verify(mockedDatabaseProperties).connectionString();
    }
    @Test
    void testDeleteRoeptSQLExceptionAan() {
        //Arrange
        when(mockedDatabaseProperties.connectionString()).thenReturn("ditIsFout");
        // Act&Assert
        assertThrows(DatabaseFoutException.class, () -> afspeellijstDAOUnderTest.delete(ID));
    }
    @Test
    void testDeleteRoeptDatabasePropertiesAan() {
        // Arrange
        when(mockedDatabaseProperties.connectionString()).thenReturn("jdbc:mysql://localhost/Spotitube?user=root&password=dbrules&serverTimezone=UTC&autoReconnect=true&useSSL=false");
        // Act
        afspeellijstDAOUnderTest.delete(ID);

        // Assert
        verify(mockedDatabaseProperties).connectionString();
    }
    @Test
    void testInsertRoeptSQLExceptionAan() {
        //Arrange
        var afspeellijst = new Afspeellijst();
        when(mockedDatabaseProperties.connectionString()).thenReturn("ditIsFout");
        // Act&Assert
        assertThrows(DatabaseFoutException.class, () -> afspeellijstDAOUnderTest.insert(afspeellijst));
    }
    @Test
    void testInsertRoeptDatabasePropertiesAan() {
        // Arrange

        var afspeellijst = new Afspeellijst();
        afspeellijst.setId(1);
        afspeellijst.setNaam("naam");
        when(mockedDatabaseProperties.connectionString()).thenReturn("jdbc:mysql://localhost/Spotitube?user=root&password=dbrules&serverTimezone=UTC&autoReconnect=true&useSSL=false");
        // Act
        afspeellijstDAOUnderTest.delete(afspeellijst.getId());
        afspeellijstDAOUnderTest.insert(afspeellijst);

        // Assert
        verify(mockedDatabaseProperties, times(2)).connectionString();
    }
    @Test
    void testGetMaxIdRoeptSQLExceptionAan() {
        //Arrange
        when(mockedDatabaseProperties.connectionString()).thenReturn("ditIsFout");
        // Act&Assert
        assertThrows(DatabaseFoutException.class, () -> afspeellijstDAOUnderTest.getMaxId());
    }
    @Test
    void testGetMaxIdRoeptDatabasePropertiesAan() {
        // Arrange
        when(mockedDatabaseProperties.connectionString()).thenReturn("jdbc:mysql://localhost/Spotitube?user=root&password=dbrules&serverTimezone=UTC&autoReconnect=true&useSSL=false");
        // Act
        afspeellijstDAOUnderTest.getMaxId();
        // Assert
        verify(mockedDatabaseProperties).connectionString();
    }
}
