package domain.datamappers;

import datasource.connectie.DatabaseProperties;
import domain.Afspeellijst;
import domain.Eigenaar;
import exceptions.eigenexcepties.DatabaseFoutException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EigenaarDataMapperTest {

    private EigenaarDataMapper eigenaarDataMapperUnderTest;

    @BeforeEach
    void setUp() {
        eigenaarDataMapperUnderTest = new EigenaarDataMapper();
    }

    @Test
    void testMapResultSetToListDomain() throws SQLException {
        // Arrange
        var databaseProperties = new DatabaseProperties();
        var connection = DriverManager.getConnection(databaseProperties.connectionString());
        var statement = connection.prepareStatement("SELECT * FROM eigenaar WHERE gebruikersnaam = ?");
        statement.setString(1, "gebruiker0");
        var resultSet = statement.executeQuery();
        var eigenaar = new Eigenaar();
        eigenaar.setGebruikersnaam("gebruiker0");
        eigenaar.setWachtwoord("wachtwoord");
        // Act
        var actual = eigenaarDataMapperUnderTest.mapResultSetToDomain(resultSet);

        // Assert
        assertEquals(eigenaar.getGebruikersnaam(), actual.getGebruikersnaam());
        assertEquals(eigenaar.getWachtwoord(), actual.getWachtwoord());
    }
    @Test
    void testMapResultSetToDomainReturnsNull() {
        // Arrange
        ResultSet resultSet = null;

        // Act
        final Eigenaar actual = eigenaarDataMapperUnderTest.mapResultSetToDomain(resultSet);

        // Assert
        assertEquals(null, actual);
    }
    @Test
    void testMapResultSetToDomainThrowsSQLException() throws SQLException {
        // Arrange
        var databaseProperties = new DatabaseProperties();
        var connection = DriverManager.getConnection(databaseProperties.connectionString());
        var statement = connection.prepareStatement("SELECT naam FROM afspeellijst WHERE id = ?");
        statement.setInt(1, 4);
        var resultSet = statement.executeQuery();
        // Act & Assert
        assertThrows(DatabaseFoutException.class, () -> eigenaarDataMapperUnderTest.mapResultSetToDomain(resultSet));
    }
}

